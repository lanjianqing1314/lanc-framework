package com.lanc.mongodb.service;

import com.lanc.business.service.MongoDemoService;
import com.lanc.domain.po.Demo;
import com.lanc.mongodb.po.DemoMongo;
import com.lanc.mongodb.repository.DemoRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MongoDemoServiceImpl
 *
 * @author: lanjianqing
 * @create: 2024-09-01 11:03
 */
@Service
public class MongoDemoServiceImpl implements MongoDemoService {
    @Resource
    private DemoRepo demoRepo;

    @Override
    public void addTest(Demo demo) {
        DemoMongo demoMongo = DemoMongo.to(demo);
        demoRepo.save(demoMongo);
    }
}
