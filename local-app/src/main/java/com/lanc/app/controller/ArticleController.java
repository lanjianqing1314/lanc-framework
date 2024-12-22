package com.lanc.app.controller;

import com.lanc.domain.vo.*;
import com.lanc.domain.dto.*;
import com.lanc.domain.po.*;
import com.lanc.business.service.ArticleService;
import com.lanc.mybatis.mapper.ArticleMapper;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的Controller
* @createDate 2024-11-16 23:52:21
*/
@RestController
public class ArticleController {

    @Resource
    private ArticleService service;

    /**
     * description(描述): add
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param dto ArticleDTO
     * @return ArticleVO
     */
    @PostMapping("/article")
    public ArticleVO add(@RequestBody ArticleDTO dto) {
        return service.add(dto);
    }

    /**
     * description(描述): update
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param dto ArticleDTO
     * @return ArticleVO
     */
    @DeleteMapping("/article")
    public ArticleVO updateById(@RequestBody ArticleDTO dto) {
        return service.updateById(dto);
    }

    /**
     * description(描述): selectPage
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param dto ArticleDTO
     * @param pageNum  页码
     * @param pageSize 分页大小
     * @return PageInfo<ArticleVO>
     */
    @PostMapping("/article/selectPage/{pageNum}/{pageSize}")
    public PageInfo<ArticleVO> selectPage(@RequestBody ArticleDTO dto, @PathVariable int pageNum, @PathVariable int pageSize) {
        return service.selectPage(dto, pageNum, pageSize);
    }

    /**
     * description(描述): selectOne
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param id id
     * @return ArticleVO
     */
        @GetMapping("/article/selectOne/{id}")
    public ArticleVO selectOne(@PathVariable Serializable id) {
        return service.selectOne(id);
    }
}
