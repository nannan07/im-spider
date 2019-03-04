package com.allmsi.spider.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.allmsi.spider.model.gd.GdMovieHourly;
import com.allmsi.spider.model.gd.GdMovieHourlyRealTime;


@Mapper
public interface GdMovieHourlyMapper {

	int insertGuduoMovieList(List<GdMovieHourly> list);
	
	List<GdMovieHourly> selectMovieHourlyList();

	List<GdMovieHourlyRealTime> selectMovieHourlyRealTimeList();
}