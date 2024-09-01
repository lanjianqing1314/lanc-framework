package com.lanc;

import com.lanc.business.service.DemoService;
import com.lanc.domain.dto.DemoDTO;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;

/**
 * 操作日志组件测试
 * <p>
 *
 * @author:lanjianqing
 * @create:2024-08-30 13:15
 **/
public class DemoServiceTest extends BaseTest {
    @Resource
    private DemoService demoService;

    @Test
    @Sql(scripts = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void addTest() {
        DemoDTO order = DemoDTO.builder()
                .msg("测试订单")
                .build();
        demoService.addTest(order);
    }
}
