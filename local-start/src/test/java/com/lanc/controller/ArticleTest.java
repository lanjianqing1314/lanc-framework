package com.lanc.controller;

import lombok.extern.slf4j.Slf4j;
import com.lanc.BaseTest;
import com.lanc.domain.dto.ArticleDTO;
import com.lanc.app.controller.ArticleController;
import com.lanc.domain.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.junit.Test;

@Slf4j
public class ArticleTest extends BaseTest{

    @Resource
    private ArticleController controller;

    @Test
    public void testAdd() {
        ArticleDTO dto = new ArticleDTO();
        ArticleVO vo = controller.add(dto);
        log.info("vo:{}", vo);
    }

    @Test
    public void testUpdate() {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(1857814221165096962L);
        dto.setTitle("hollow work!");
        ArticleVO vo = controller.updateById(dto);
        log.info("vo:{}", vo);
    }

    @Test
    public void testSelectPage() {
        ArticleDTO dto = new ArticleDTO();
        PageInfo<ArticleVO> vo = controller.selectPage(dto, 1, 10);
        log.info("vo:{}", vo);
    }

    @Test
    public void testSelectOne() {
        ArticleVO vo = controller.selectOne(1857814221165096962L);
        log.info("vo:{}", vo);
    }
}
