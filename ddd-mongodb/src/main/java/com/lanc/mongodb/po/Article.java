package com.lanc.mongodb.po;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DemoMongo
 *
 * @author: lanjianqing
 * @create: 2024-09-01 09:03
 */
@Document(collection = "article")
@Data
@Builder
public class Article {
    @Id
    private String id;
    private String title;
}
