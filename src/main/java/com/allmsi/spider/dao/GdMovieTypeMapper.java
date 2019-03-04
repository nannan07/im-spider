package com.allmsi.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.spider.model.gd.GdMovieType;
@Mapper
public interface GdMovieTypeMapper {

	int insertBatch(List<GdMovieType> gdMovieTypeList);

	
    
}