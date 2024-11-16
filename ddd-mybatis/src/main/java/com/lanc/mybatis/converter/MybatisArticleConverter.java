package com.lanc.mybatis.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lanc.domain.po.ArticlePO;
import com.lanc.domain.vo.ArticleVO;
import com.lanc.domain.dto.ArticleDTO;
import com.lanc.mybatis.po.Article;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的对象转换
* @createDate 2024-11-16 23:52:21
*/
@Mapper
public interface MybatisArticleConverter {

    MybatisArticleConverter INSTANCE = Mappers.getMapper(MybatisArticleConverter.class);

    /**
     * description(描述): dto2po
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param articleDTO ArticleDTO
     * @return ArticlePO
     */
    ArticlePO dto2po(ArticleDTO articleDTO);

    /**
     * description(描述): po2vo
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param article Article
     * @return ArticleVO
     */
    ArticleVO po2vo(Article article);

    /**
     * description(描述): po2po
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param articlePO ArticlePO
     * @return Article
     */
    Article po2po(ArticlePO articlePO);

    /**
     * description(描述): po2po
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param article Article
     * @return ArticlePO
     */
    ArticlePO po2po(Article article);

    /**
     * description(描述): po2voList
     * <p>
     * author(作者): Administrator <br/>
     * create(创建时间): 2024-11-16 23:52:21 <br/>
     *
     * @param article List<Article>
     * @return List<ArticleVO>
     */
    List<ArticleVO> po2voList(List<Article> article);
}
