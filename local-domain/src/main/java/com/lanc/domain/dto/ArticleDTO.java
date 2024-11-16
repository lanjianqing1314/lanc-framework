package com.lanc.domain.dto;


import java.util.Date;
import lombok.Data;
import com.lanc.domain.po.ArticlePO;

/**
* @author Administrator
* @description 针对表【t_article(文章表)】的dto
* @createDate 2024-11-16 23:52:21
*/
@Data
public class ArticleDTO {

    /**
    * 文章id
    */
    private Long id;

    /**
    * 文章标题
    */
    private String title;

    /**
    * 文章题图
    */
    private String titleImage;

    /**
    * 文章描述
    */
    private String description;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最后一次更新时间
    */
    private Date updateTime;

    /**
    * 删除标志位：0：未删除 1：已删除
    */
    private Integer isDeleted;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 状态（1:保存，2:发布）
    */
    private Integer state;

}
