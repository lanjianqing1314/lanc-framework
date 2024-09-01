package com.lanc.app.service;

import com.lanc.app.service.plus.validated.DemoValidated;
import com.lanc.business.service.DemoService;
import com.lanc.business.service.MongoDemoService;
import com.lanc.business.service.MybatisDemoService;
import com.lanc.domain.dto.DemoDTO;
import com.lanc.domain.po.Demo;
import com.lanc.domain.vo.DemoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * DemoServiceImpl
 *
 * @author: lanjianqing
 * @create: 2024-09-01 08:50
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoValidated demoValidated;
    @Resource
    private MybatisDemoService mybatisDemoService;
    @Resource
    private MongoDemoService mongoDemoService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DemoVO addTest(DemoDTO demoDTO) {
        demoValidated.checkDemo(demoDTO);
        Demo demo = DemoDTO.toPo(demoDTO);
        mybatisDemoService.addTest(demo);
        mongoDemoService.addTest(demo);
        return demo.toVO(demo);
    }
}
