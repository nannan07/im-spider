package com.allmsi.spider.model.gd;

public class GdMovieHourlyJson {

	private String showId;// 影片Id

	private String name;// 影片名

	private String days;// 上映天数

	private String rank;// 排名

	private String[] platformImgs;// 平台标识图片

	private String playCount;// 当日播放量原始数据

	private String gdi;// 全网热度原始数据

	private String hotCount;// 平台热度原始数据

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String[] getPlatformImgs() {
		return platformImgs;
	}

	public void setPlatformImgs(String[] platformImgs) {
		this.platformImgs = platformImgs;
	}

	public String getPlayCount() {
		return playCount;
	}

	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}

	public String getGdi() {
		return gdi;
	}

	public void setGdi(String gdi) {
		this.gdi = gdi;
	}

	public String getHotCount() {
		return hotCount;
	}

	public void setHotCount(String hotCount) {
		this.hotCount = hotCount;
	}

}
