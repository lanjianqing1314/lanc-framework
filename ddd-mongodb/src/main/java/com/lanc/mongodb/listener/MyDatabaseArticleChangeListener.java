package com.lanc.mongodb.listener;

import com.lanc.business.sse.SseManager;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 监听myDatabase数据库article的变化
 */
@Slf4j
@Component
public class MyDatabaseArticleChangeListener {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SseManager sseManager;

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
                    .forEach((ChangeStreamDocument<Document> change) -> {
                        OperationType operationType = change.getOperationType();
                        Document fullDocument = change.getFullDocument();

                        switch (operationType) {
                            case INSERT:
                                log.info("Document inserted: {}", fullDocument.toJson());
                                sseManager.sendMessage(fullDocument.toJson());
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
                    });
        } catch (Exception e) {
            log.error("Error while listening to changes in MongoDB: ", e);
        }
    }
}
