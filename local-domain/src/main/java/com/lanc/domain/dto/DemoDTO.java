package com.lanc.domain.dto;

import com.lanc.domain.po.Demo;
import lombok.Builder;
import lombok.Data;

/**
 * DemoDTO
 *
 * @description: 领域对象
 * @author: lanjianqing
 * @create: 2022-11-22 03:59
 */
@Data
@Builder
public class DemoDTO {
    private Long id;
    private String msg;

    public static Demo toPo(DemoDTO demoDTO) {
        return Demo.builder()
                .id(demoDTO.getId())
                .msg(demoDTO.getMsg())
                .build();
    }
}
