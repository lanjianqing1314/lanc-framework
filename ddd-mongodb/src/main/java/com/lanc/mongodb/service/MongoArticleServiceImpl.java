package com.lanc.mongodb.service;

import com.lanc.business.service.MongoArticleService;
import com.lanc.domain.po.ArticlePO;
import com.lanc.mongodb.po.Article;
import com.lanc.mongodb.repository.ArticleRepo;
import org.springframework.stereotype.Service;
import com.lanc.mongodb.converter.MongoArticleConverter;

import javax.annotation.Resource;

/**
 * MongoDemoServiceImpl
 *
 * @author: lanjianqing
 * @create: 2024-09-01 11:03
 */
@Service
public class MongoArticleServiceImpl implements MongoArticleService {
    @Resource
    private ArticleRepo articleRepo;

    @Override
    public void add(ArticlePO demo) {
        Article demoMongo = MongoArticleConverter.INSTANCE.po2po(demo);
        articleRepo.save(demoMongo);
    }
}
