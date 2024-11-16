package com.lanc.business.service;

import com.lanc.domain.po.*;
import com.lanc.domain.dto.ArticleDTO;
import com.lanc.domain.vo.ArticleVO;
import java.io.Serializable;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的数据库操作Service
* @createDate 2024-11-16 23:52:21
*/
public interface MybatisArticleService {

    /**
     * description(描述): add
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param po ArticlePO
     */
    ArticlePO add(ArticlePO po);

    /**
     * description(描述): update
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param po ArticlePO
     */
    ArticlePO updateById(ArticlePO po);

    /**
     * description(描述): select
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param dto ArticleDTO
     * @return List<ArticleVO>
     */
    List<ArticleVO> select(ArticleDTO dto);

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
