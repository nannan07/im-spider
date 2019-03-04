package com.allmsi.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.spider.model.gd.GdMoviePersonal;
@Mapper
public interface GdMoviePersonalMapper {

	int insertBatch(List<GdMoviePersonal> gdMoviePersonList);
}