package com.lanc.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lanc.domain.po.Demo;
import lombok.Data;

import java.io.Serializable;

/**
 * DemoMongo
 *
 * @author: lanjianqing
 * @create: 2024-09-01 09:03
 */
@TableName("test")
@Data
public class DemoIbatis implements Serializable {

    private Long id;
    private String name;

    public static DemoIbatis to(Demo demo) {
        DemoIbatis demoIbatis = new DemoIbatis();
        demoIbatis.setId(demo.getId());
        demoIbatis.setName(demo.getName());
        return demoIbatis;
    }

    public static void toDemo(DemoIbatis demoIbatis, Demo demo) {
        demo.setId(demoIbatis.getId());
        demo.setName(demoIbatis.getName());
    }
}
