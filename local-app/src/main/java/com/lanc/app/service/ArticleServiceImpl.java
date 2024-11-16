package com.lanc.app.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanc.business.service.ArticleService;
import com.lanc.business.service.MongoArticleService;
import com.lanc.mybatis.mapper.ArticleMapper;
import com.lanc.business.service.MybatisArticleService;
import com.lanc.domain.vo.*;
import com.lanc.domain.dto.*;
import com.lanc.domain.po.*;
import com.lanc.domain.converter.ArticleConverter;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的数据库操作Service实现
* @createDate 2024-11-16 23:52:21
*/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private MybatisArticleService service;
    @Resource
    private MongoArticleService mongoArticleService;

    @Override
    public ArticleVO add(ArticleDTO dto) {
        ArticlePO po = service.add(ArticleConverter.INSTANCE.dto2po(dto));
        mongoArticleService.add(po);
        return ArticleConverter.INSTANCE.po2vo(po);
    }

    @Override
    public ArticleVO updateById(ArticleDTO dto) {
        ArticlePO po = service.updateById(ArticleConverter.INSTANCE.dto2po(dto));
        return ArticleConverter.INSTANCE.po2vo(po);
    }

    @Override
    public PageInfo<ArticleVO> selectPage(ArticleDTO dto, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVO> voList = service.select(dto);
        return new PageInfo<>(voList);
    }

    @Override
    public ArticleVO selectOne(Serializable id) {
        return service.selectOne(id);
    }
}
