package com.lanc.controller;

import com.github.pagehelper.PageInfo;
import com.lanc.BaseTest;
import com.lanc.app.controller.ArticleController;
import com.lanc.domain.dto.ArticleDTO;
import com.lanc.domain.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
public class ArticleTest extends BaseTest {

    @Resource
    private ArticleController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAdd() {
        ArticleDTO dto = new ArticleDTO();
        ArticleVO vo = controller.add(dto);
        log.info("vo:{}", vo);
    }

    @Test
    public void testUpdate() {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(1857972277115052034L);
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
        ArticleVO vo = controller.selectOne(1857972277115052034L);
        log.info("vo:{}", vo);
    }

    @Test
    public void test1() throws Exception {
        // Perform GET request to /test/string and capture the result
        MvcResult result = mockMvc.perform(get("/article/selectOne/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn(); // 获取请求结果

        // 获取响应体
        String responseBody = result.getResponse().getContentAsString();

        // 打印响应体
        System.out.println("Response Body: " + responseBody);
    }
}
