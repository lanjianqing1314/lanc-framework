package com.lanc.domain.po;

import com.lanc.domain.vo.DemoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Demo
 *
 * @description: 领域对象
 * @author: lanjianqing
 * @create: 2022-11-22 03:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Demo implements Serializable {
    private Long id;
    private String msg;

    public DemoVO toVO(Demo demo) {
        return DemoVO.builder()
                .id(demo.getId())
                .msg(demo.getMsg())
                .build();
    }
}
