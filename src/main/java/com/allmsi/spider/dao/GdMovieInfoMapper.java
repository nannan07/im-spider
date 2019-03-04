package com.allmsi.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.spider.model.gd.GdMovieInfo;
@Mapper
public interface GdMovieInfoMapper {
   
	List<String> selectShowIdList();

	int insertBatch(List<GdMovieInfo> gdMovieInfoList);

	

	
}