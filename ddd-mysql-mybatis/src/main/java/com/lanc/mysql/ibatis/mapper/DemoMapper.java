package com.lanc.mysql.ibatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanc.mysql.ibatis.po.DemoIbatis;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @description 针对表【file】的数据库操作Mapper
 * @createDate 2022-10-30 20:35:02
 * @Entity com.dakewe.fastdfs.domain.File
 */
@Mapper
public interface DemoMapper extends BaseMapper<DemoIbatis> {
}
