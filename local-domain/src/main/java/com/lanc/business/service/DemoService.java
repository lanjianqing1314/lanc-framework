package com.lanc.business.service;

import com.lanc.domain.dto.DemoDTO;
import com.lanc.domain.vo.DemoVO;

/**
 * IDemoService
 *
 * @author: lanjianqing
 * @create: 2024-09-01 08:47
 */
public interface DemoService {
    /**
     * description(描述): addTest
     * <p>
     * author(作者): lan_jianqing <br/>
     * create(创建时间): 2024/9/1 11:14 <br/>
     *
     * @param demoDTO demoDTO
     * @return DemoVO
     */
    DemoVO addTest(DemoDTO demoDTO);
}
