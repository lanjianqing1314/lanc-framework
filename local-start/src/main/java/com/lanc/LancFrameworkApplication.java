package com.lanc;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 项目启动类
 *
 * @author: lanjianqing
 * @create: 2024-08-02 11:10
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnableGracefulResponse
public class LancFrameworkApplication {

    /**
     * description(描述): 项目执行入口
     * <p>
     * author(作者): lan_jianqing <br/>
     * create(创建时间): 2023/8/21 16:46 <br/>
     *
     * @param args defaultParamDescription
     */
    public static void main(String[] args) {
        SpringApplication.run(LancFrameworkApplication.class, args);
        log.info("lanc服务启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
