package com.lanc.mybatis.mapper;

import com.lanc.mybatis.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的数据库操作Mapper
* @createDate 2024-11-16 23:52:21
* @Entity com.lanc.domain.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
