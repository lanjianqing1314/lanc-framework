package com.lanc.mongodb.repository;

import com.lanc.mongodb.po.DemoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * DemoRepo
 *
 * @author: lanjianqing
 * @create: 2024-09-01 09:01
 */
public interface DemoRepo extends MongoRepository<DemoMongo, String> {
}
