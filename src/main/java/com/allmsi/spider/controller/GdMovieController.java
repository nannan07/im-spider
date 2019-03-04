package com.allmsi.spider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allmsi.spider.job.GdMovieJob;

@RestController
@RequestMapping("/gd")
public class GdMovieController {

	@Autowired
	private GdMovieJob gdMovieJob;

	@RequestMapping(value = "/hourly", method = RequestMethod.GET)
	public void movieHourly() {
		gdMovieJob.gdMovieHourlyJob();
	}

	@RequestMapping(value = "/dialy/count", method = RequestMethod.GET)
	public void movieDialyJob() {
		gdMovieJob.gdMovieDialyJob();
	}
	@RequestMapping(value = "/movie/info", method = RequestMethod.GET)
	public void movieInfo() {
		gdMovieJob.gdMovieInfoJob();
	}
}
