package com.lanc.mongodb.converter;

import com.lanc.domain.po.ArticlePO;
import com.lanc.mongodb.po.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MongoArticleConverter {
    MongoArticleConverter INSTANCE = Mappers.getMapper(MongoArticleConverter.class);

    Article po2po(ArticlePO demo);
}
