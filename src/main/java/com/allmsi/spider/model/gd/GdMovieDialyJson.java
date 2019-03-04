package com.allmsi.spider.model.gd;

public class GdMovieDialyJson {
	private String today_play_count;// 今日播放量

	private String daily_play_count;// 昨日播放量

	private String release_days;// 上映天数

	private String release_date;// 上映日期

	private String total_play_count;// 累计播放量（/万）

	private Integer play_count_last_date;// 累计播放量

	private String play_count_rank_by_rlease_year;// 当年累计播放量排行

	public String getToday_play_count() {
		return today_play_count;
	}

	public void setToday_play_count(String today_play_count) {
		this.today_play_count = today_play_count;
	}

	public String getDaily_play_count() {
		return daily_play_count;
	}

	public void setDaily_play_count(String daily_play_count) {
		this.daily_play_count = daily_play_count;
	}

	public String getRelease_days() {
		return release_days;
	}

	public void setRelease_days(String release_days) {
		this.release_days = release_days;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getTotal_play_count() {
		return total_play_count;
	}

	public void setTotal_play_count(String total_play_count) {
		this.total_play_count = total_play_count;
	}

	public Integer getPlay_count_last_date() {
		return play_count_last_date;
	}

	public void setPlay_count_last_date(Integer play_count_last_date) {
		this.play_count_last_date = play_count_last_date;
	}

	public String getPlay_count_rank_by_rlease_year() {
		return play_count_rank_by_rlease_year;
	}

	public void setPlay_count_rank_by_rlease_year(String play_count_rank_by_rlease_year) {
		this.play_count_rank_by_rlease_year = play_count_rank_by_rlease_year;
	}

}
