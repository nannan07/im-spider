package com.allmsi.spider.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.allmsi.spider.service.GdMailService;
import com.allmsi.spider.service.GdMovieService;
@Component
public class GdMovieJob {

	@Autowired
	private GdMovieService guduoMovieDialyService;

	@Autowired
	private GdMailService mailService;

	@Scheduled(cron = "0 8 * * * *")
	public String gdMovieHourlyJob() {
		int total = guduoMovieDialyService.getGdMovieHourlyList();
		String msgs = "";
		if (total > 0) {
			msgs = "date :" + new Date() + " movieDialys: " + total + "\n\n";
		}
		return msgs;
	}

	@Scheduled(cron = "0 15 *  * * ? ")
	public String gdMovieDialyJob() {
		int count = guduoMovieDialyService.getMovieDialyPlayCountList();
		String msg = "";
		if (count > 0) {
			msg = "抓取日播放量数据" + count + "条";
		}
		return msg;
	}
	@Scheduled(cron = "0 20 * * * ? ")
	public String gdMovieInfoJob() {
		int count = guduoMovieDialyService.getGdMovieInfoList();
		String msg = "";
		if (count > 0) {
			msg = "抓取到影片详情" + count + "条";
		}
		return msg;
	}

	class EmailThread implements Runnable {

		private String subject;

		private String content;

		public EmailThread(String subject, String content) {
			this.subject = subject;
			this.content = content;
		}

		@Override
		public void run() {
			mailService.sendSimpleMail(subject, content);
		}
	}
}
