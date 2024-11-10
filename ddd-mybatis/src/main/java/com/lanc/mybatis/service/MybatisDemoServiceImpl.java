package com.lanc.mybatis.service;

import com.lanc.business.service.MybatisDemoService;
import com.lanc.domain.po.Demo;
import com.lanc.mybatis.po.DemoIbatis;
import com.lanc.mybatis.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MybatisDemoService
 *
 * @author: lanjianqing
 * @create: 2024-09-0 10:58
 */
@Service
public class MybatisDemoServiceImpl implements MybatisDemoService {
    @Resource
    private DemoMapper demoMapper;
    @Override
    public void addTest(Demo demo) {
        DemoIbatis demoIbatis = DemoIbatis.to(demo);
        demoMapper.insert(demoIbatis);
        DemoIbatis.toDemo(demoIbatis, demo);
    }
}
