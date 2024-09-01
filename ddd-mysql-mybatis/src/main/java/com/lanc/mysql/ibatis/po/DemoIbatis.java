package com.lanc.mysql.ibatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lanc.domain.po.Demo;
import lombok.Builder;
import lombok.Data;

/**
 * DemoMongo
 *
 * @author: lanjianqing
 * @create: 2024-09-01 09:03
 */
@Data
@TableName("demo")
@Builder
public class DemoIbatis {
    private Long id;
    private String msg;

    public static DemoIbatis to(Demo demo) {
        return DemoIbatis.builder().id(demo.getId()).msg(demo.getMsg()).build();
    }

    public static void toDemo(DemoIbatis demoIbatis, Demo demo) {
        demo.setId(demoIbatis.getId());
        demo.setMsg(demoIbatis.getMsg());
    }
}
