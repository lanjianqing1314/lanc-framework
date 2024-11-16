package com.lanc.business.service;

import com.lanc.domain.vo.*;
import com.lanc.domain.dto.*;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的数据库操作Service
* @createDate 2024-11-16 23:52:21
*/
public interface ArticleService {

    /**
     * description(描述): add
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param dto ArticleDTO
     * @return ArticleVO
     */
    ArticleVO add(ArticleDTO dto);

    /**
     * description(描述): update
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param dto ArticleDTO
     * @return ArticleVO
     */
    ArticleVO updateById(ArticleDTO dto);

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
    PageInfo<ArticleVO> selectPage(ArticleDTO dto, int pageNum, int pageSize);

    /**
     * description(描述): selectOne
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param id id
     * @return ArticleVO
     */
    ArticleVO selectOne(Serializable id);
}
