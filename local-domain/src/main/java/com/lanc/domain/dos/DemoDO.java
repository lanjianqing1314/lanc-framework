package com.lanc.domain.dos;

import lombok.Builder;
import lombok.Data;

/**
 * DemoDO
 *
 * @description: 领域对象
 * @author: lanjianqing
 * @create: 2022-11-22 03:59
 */
@Data
@Builder
public class DemoDO {
    private Long id;
    private String msg;
}
