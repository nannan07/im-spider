package com.allmsi.spider.model.gd;

import java.util.Date;

public class GdMovieHourlyRealTime {
	private String id;

	private String showId;

	private String name;

	private String date;

	private Integer rank;

	private Integer rise;

	private String days;

	private String platformImgs;

	private String platformId;

	private Integer playCount;

	private String playCountShow;

	private Integer totalPlayCount;

	private String totalPlayCountShow;

	private String gdi;

	private String gdiNum;

	private String gdiShow;

	private String hotCount;

	private String hotCountShow;

	private Date cTime;

	private Integer num;

	private String hour;

	private Byte state;

	public GdMovieHourlyRealTime() {
	}

	public GdMovieHourlyRealTime(GdMovieHourly guduoMovieHourly) {
		if (guduoMovieHourly != null) {
			this.id = guduoMovieHourly.getId();
			this.showId = guduoMovieHourly.getShowId();
			this.name = guduoMovieHourly.getName();
			this.date = guduoMovieHourly.getDate();
			this.rank = guduoMovieHourly.getRank();
			this.rise = guduoMovieHourly.getRise();
			this.days = guduoMovieHourly.getDays();
			this.platformImgs = guduoMovieHourly.getPlatformImgs();
			this.platformId = guduoMovieHourly.getPlatformId();
			this.playCount = guduoMovieHourly.getPlayCount();
			this.playCountShow = guduoMovieHourly.getPlayCountShow();
			this.totalPlayCount = guduoMovieHourly.getTotalPlayCount();
			this.totalPlayCountShow=guduoMovieHourly.getTotalPlayCountShow();
			this.gdi = guduoMovieHourly.getGdi();
			this.gdiNum = guduoMovieHourly.getGdiNum();
			this.gdiShow = guduoMovieHourly.getGdiShow();
			this.hotCount = guduoMovieHourly.getHotCount();
			this.num = guduoMovieHourly.getNum();
			this.hotCountShow = guduoMovieHourly.getHotCountShow();
			this.hour = guduoMovieHourly.getHour();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date == null ? null : date.trim();
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getRise() {
		return rise;
	}

	public void setRise(Integer rise) {
		this.rise = rise;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days == null ? null : days.trim();
	}

	public String getPlatformImgs() {
		return platformImgs;
	}

	public void setPlatformImgs(String platformImgs) {
		this.platformImgs = platformImgs == null ? null : platformImgs.trim();
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId == null ? null : platformId.trim();
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public String getPlayCountShow() {
		return playCountShow;
	}

	public void setPlayCountShow(String playCountShow) {
		this.playCountShow = playCountShow;
	}

	public Integer getTotalPlayCount() {
		return totalPlayCount;
	}

	public void setTotalPlayCount(Integer totalPlayCount) {
		this.totalPlayCount = totalPlayCount;
	}

	public String getTotalPlayCountShow() {
		return totalPlayCountShow;
	}

	public void setTotalPlayCountShow(String totalPlayCountShow) {
		this.totalPlayCountShow = totalPlayCountShow;
	}

	public String getGdi() {
		return gdi;
	}

	public void setGdi(String gdi) {
		this.gdi = gdi;
	}

	public String getGdiNum() {
		return gdiNum;
	}

	public void setGdiNum(String gdiNum) {
		this.gdiNum = gdiNum;
	}

	public String getGdiShow() {
		return gdiShow;
	}

	public void setGdiShow(String gdiShow) {
		this.gdiShow = gdiShow;
	}

	public String getHotCount() {
		return hotCount;
	}

	public void setHotCount(String hotCount) {
		this.hotCount = hotCount;
	}

	public String getHotCountShow() {
		return hotCountShow;
	}

	public void setHotCountShow(String hotCountShow) {
		this.hotCountShow = hotCountShow;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}
}