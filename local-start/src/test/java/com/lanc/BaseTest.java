package com.lanc;

import org.apache.ibatis.annotations.Mapper;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试启动类
 * <p>
 *
 * @author:lanjianqing
 * @create:2024-08-30 13:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BaseTest.Application.class)
public abstract class BaseTest {

    @MapperScan(value = "com.lanc.mybatis.mapper.**", annotationClass = Mapper.class, lazyInitialization = "true")
    @ComponentScan(value = "com.lanc")
    public static class Application {

    }
}
