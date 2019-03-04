package com.allmsi.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.spider.model.gd.GdMovieCompany;
@Mapper
public interface GdMovieCompanyMapper {

	int insertBatch(List<GdMovieCompany> gdMovieCompanyList);
}