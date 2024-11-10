package com.lanc.domain.dto;

import com.lanc.domain.po.Demo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DemoDTO
 *
 * @description: 领域对象
 * @author: lanjianqing
 * @create: 2022-11-22 03:59
 */
@Data
@Builder
public class DemoDTO implements Serializable {
    private Long id;
    private String name;

    public static Demo toPo(DemoDTO demoDTO) {
        return Demo.builder()
                .id(demoDTO.getId())
                .name(demoDTO.getName())
                .build();
    }
}
