package com.lanc.app.controller;

import com.lanc.business.service.DemoService;
import com.lanc.domain.dto.DemoDTO;
import com.lanc.domain.vo.DemoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * DemoController
 *
 * @author: lanjianqing
 * @create: 2024-09-01 11:08
 */
@RestController
public class DemoController {
    @Resource
    private DemoService demoService;

    /**
     * description(描述): addTest
     * <p>
     * author(作者): lan_jianqing <br/>
     * create(创建时间): 2024/9/1 11:13 <br/>
     *
     * @param demoDTO demoDTO
     * @return DemoVO
     */
    @PostMapping("/demo/addTest")
    public DemoVO addTest(@RequestBody DemoDTO demoDTO) {
        return demoService.addTest(demoDTO);
    }
}
