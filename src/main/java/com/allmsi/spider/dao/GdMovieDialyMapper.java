package com.allmsi.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.spider.model.gd.GdMovieDialy;
@Mapper
public interface GdMovieDialyMapper {

	List<GdMovieDialy> selectMovieDialyList();

	int insetBatch(List<GdMovieDialy> gdMovieDialyList);
}