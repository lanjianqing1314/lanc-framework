package com.lanc.mybatis.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lanc.business.service.MybatisArticleService;
import com.lanc.business.service.ArticleService;
import com.lanc.mybatis.mapper.ArticleMapper;
import com.lanc.mybatis.po.Article;
import com.lanc.mybatis.po.*;
import com.lanc.domain.po.ArticlePO;
import com.lanc.mybatis.converter.MybatisArticleConverter;
import com.lanc.domain.vo.ArticleVO;
import com.lanc.domain.dto.ArticleDTO;

import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的数据库操作Service实现
* @createDate 2024-11-16 23:52:21
*/
@Service
public class MybatisArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements MybatisArticleService {

    @Override
    public ArticlePO add(ArticlePO po) {
        Article myPo = MybatisArticleConverter.INSTANCE.po2po(po);
        baseMapper.insert(myPo);
        return MybatisArticleConverter.INSTANCE.po2po(myPo);
    }

    @Override
    public ArticlePO updateById(ArticlePO po) {
        Article myPo = MybatisArticleConverter.INSTANCE.po2po(po);
        baseMapper.updateById(myPo);
        return MybatisArticleConverter.INSTANCE.po2po(myPo);
    }

    @Override
    public List<ArticleVO> select(ArticleDTO dto) {
        LambdaQueryWrapper<Article> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(dto.getId() != null, Article::getId, dto.getId());
        wrapper.eq(dto.getTitle() != null, Article::getTitle, dto.getTitle());
        wrapper.eq(dto.getTitleImage() != null, Article::getTitleImage, dto.getTitleImage());
        wrapper.eq(dto.getDescription() != null, Article::getDescription, dto.getDescription());
        wrapper.eq(dto.getCreateTime() != null, Article::getCreateTime, dto.getCreateTime());
        wrapper.eq(dto.getUpdateTime() != null, Article::getUpdateTime, dto.getUpdateTime());
        wrapper.eq(dto.getIsDeleted() != null, Article::getIsDeleted, dto.getIsDeleted());
        wrapper.eq(dto.getUserId() != null, Article::getUserId, dto.getUserId());
        wrapper.eq(dto.getState() != null, Article::getState, dto.getState());
        return MybatisArticleConverter.INSTANCE.po2voList(baseMapper.selectList(wrapper));
    }

    @Override
    public ArticleVO selectOne(Serializable id) {
        return MybatisArticleConverter.INSTANCE.po2vo(baseMapper.selectById(id));
    }
}
