package com.lanc.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lanc.domain.po.ArticlePO;
import com.lanc.domain.vo.ArticleVO;
import com.lanc.domain.dto.ArticleDTO;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的对象转换
* @createDate 2024-11-16 23:52:21
*/
@Mapper
public interface ArticleConverter {

    ArticleConverter INSTANCE = Mappers.getMapper(ArticleConverter.class);

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
     * @param articlePO ArticlePO
     * @return ArticleVO
     */
    ArticleVO po2vo(ArticlePO articlePO);
}
