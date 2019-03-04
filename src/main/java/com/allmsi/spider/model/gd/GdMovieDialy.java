package com.allmsi.spider.model.gd;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.allmsi.spider.util.DateUtil;
import com.allmsi.spider.util.UUIDUtil;

public class GdMovieDialy {
	private String id;

	private String showId;

	private String todayPlayCount;

	private String dailyPlayCount;

	private String releaseDays;

	private String releaseDate;

	private String totalPlayCount;

	private Integer palyCountLastDate;

	private String playCountRankByRealeaseYear;

	private Date cTime;

	private String date;

	private String hour;

	public GdMovieDialy() {

	}

	public GdMovieDialy(GdMovieDialyJson movieDialyJson, String showId) {
		if (movieDialyJson != null) {
			this.id = UUIDUtil.getUUID();
			this.showId = showId;
			this.todayPlayCount = movieDialyJson.getToday_play_count();
			this.dailyPlayCount = movieDialyJson.getDaily_play_count();
			this.releaseDays = movieDialyJson.getRelease_days();
			this.releaseDate = movieDialyJson.getRelease_date();
			this.totalPlayCount = movieDialyJson.getTotal_play_count();
			this.palyCountLastDate = movieDialyJson.getPlay_count_last_date();
			this.playCountRankByRealeaseYear = movieDialyJson.getPlay_count_rank_by_rlease_year();
			this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			this.hour = String.valueOf(DateUtil.getHour(new Date()));
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId == null ? null : showId.trim();
	}

	public String getTodayPlayCount() {
		return todayPlayCount;
	}

	public void setTodayPlayCount(String todayPlayCount) {
		this.todayPlayCount = todayPlayCount == null ? null : todayPlayCount.trim();
	}

	public String getDailyPlayCount() {
		return dailyPlayCount;
	}

	public void setDailyPlayCount(String dailyPlayCount) {
		this.dailyPlayCount = dailyPlayCount == null ? null : dailyPlayCount.trim();
	}

	public String getReleaseDays() {
		return releaseDays;
	}

	public void setReleaseDays(String releaseDays) {
		this.releaseDays = releaseDays == null ? null : releaseDays.trim();
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate == null ? null : releaseDate.trim();
	}

	public String getTotalPlayCount() {
		return totalPlayCount;
	}

	public void setTotalPlayCount(String totalPlayCount) {
		this.totalPlayCount = totalPlayCount == null ? null : totalPlayCount.trim();
	}

	public Integer getPalyCountLastDate() {
		return palyCountLastDate;
	}

	public void setPalyCountLastDate(Integer palyCountLastDate) {
		this.palyCountLastDate = palyCountLastDate;
	}

	public String getPlayCountRankByRealeaseYear() {
		return playCountRankByRealeaseYear;
	}

	public void setPlayCountRankByRealeaseYear(String playCountRankByRealeaseYear) {
		this.playCountRankByRealeaseYear = playCountRankByRealeaseYear == null ? null
				: playCountRankByRealeaseYear.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
}