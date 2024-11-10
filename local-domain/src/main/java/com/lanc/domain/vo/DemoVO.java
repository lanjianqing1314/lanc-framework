package com.lanc.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DemoVO
 *
 * @description: 领域对象
 * @author: lanjianqing
 * @create: 2022-11-22 03:59
 */
@Data
@Builder
public class DemoVO implements Serializable {
    private Long id;
    private String name;
}
