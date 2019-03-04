package com.allmsi.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.spider.model.gd.GdMovieHourlyRealTime;


@Mapper
public interface GdMovieHourlyRealTimeMapper {

	int insertBatch(List<GdMovieHourlyRealTime> list);

	int deleteByStatus();

	int updateStatus();

	List<String> selectMovieHourlyRealTimeList();
}