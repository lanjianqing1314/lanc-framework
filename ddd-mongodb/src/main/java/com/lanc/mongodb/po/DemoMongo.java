package com.lanc.mongodb.po;

import com.lanc.domain.po.Demo;
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
@Document(collection = "demo")
@Data
@Builder
public class DemoMongo {
    @Id
    private String id;
    private String msg;

    public static DemoMongo to(Demo demo) {
        return DemoMongo.builder()
                .id(demo.getId() + "")
                .msg(demo.getMsg())
                .build();
    }
}
