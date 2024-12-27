package com.lanc.mongodb.listener;

import com.lanc.business.sse.SseManager;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 监听myDatabase数据库article的变化
 *
 * @author Administrator
 */
@Slf4j
@Component
public class MyDatabaseArticleChangeListener {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SseManager sseManager;

    @Autowired
    private RedissonClient redisson;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void init() {
        executorService.submit(this::listenToChanges);
    }

    private void listenToChanges() {
        try {
            // 注意，此处不能用admin数据库，否则会报错
            mongoTemplate.getMongoDatabaseFactory().getMongoDatabase("myDatabase")
                    .getCollection("article")
                    .watch()
                    .forEach(this::handle);
        } catch (Exception e) {
            log.error("Error while listening to changes in MongoDB: ", e);
        }
    }

    private void handle(ChangeStreamDocument<Document> change) {
        OperationType operationType = change.getOperationType();
        Document fullDocument = change.getFullDocument();
        switch (operationType) {
            case INSERT:
                log.info("Document inserted: {}", fullDocument.toJson());
                insertHandle(fullDocument);
                break;
            case UPDATE:
                log.info("Document updated: {}", fullDocument.toJson());
                break;
            case DELETE:
                log.info("Document deleted: {}", change.getDocumentKey().toJson());
                break;
            default:
                log.warn("Other operation detected: {}", operationType);
        }
    }

    /**
     * 通过分布式锁，保证多个节点同时监听时候，只发送一次SSE消息
     *
     * @param fullDocument 新增的文档
     */
    private void insertHandle(Document fullDocument) {
        String lockKey = "lock:sse-processed:" + fullDocument.get("_id").toString();
        RLock lock = redisson.getLock(lockKey);

        try {
            // 尝试获取分布式锁，设置锁的过期时间为 5 秒
            boolean isLocked = lock.tryLock(1, 5, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Another node is processing the message for document: {}", fullDocument.get("_id"));
                return;
            }

            // 再次检查 Redis 是否已处理
            String processedKey = "sse-processed:" + fullDocument.get("_id").toString();
            if (redisson.getBucket(processedKey).isExists()) {
                log.info("Message already processed for document: {}", fullDocument.get("_id"));
                return;
            }

            // 标记消息已处理，并发送 SSE 消息
            redisson.getBucket(processedKey).set("1", 10, TimeUnit.SECONDS);
            log.info("send SSE：{}", fullDocument.toJson());
            sseManager.sendMessage(fullDocument.toJson());
        } catch (InterruptedException e) {
            log.error("Failed to acquire lock for document: {}", fullDocument.get("_id"), e);
        } finally {
            // 释放锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
