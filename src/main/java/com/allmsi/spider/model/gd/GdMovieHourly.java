package com.allmsi.spider.model.gd;

import java.util.Date;

import com.allmsi.spider.util.StrUtil;

public class GdMovieHourly {
	
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
	
	private final String TENGXUN_FLAG = "tengxun";
	
	private final String AIQIYI_FLAG = "aiqiyi";
	
	private final String YOUKU_FLAG = "youku";
	
	private final String SOUHU_FLAG = "souhu";

	public GdMovieHourly() {

	}

	public GdMovieHourly(GdMovieHourlyJson movieJson) {
		if (movieJson != null) {
			this.showId = movieJson.getShowId();
			this.name = movieJson.getName();
			if (StrUtil.isEmpty(movieJson.getRank())) {
				this.rank = 0;
			} else {
				this.rank = Integer.parseInt(movieJson.getRank());
			}
			this.days = movieJson.getDays();
			if (movieJson.getPlatformImgs() != null && movieJson.getPlatformImgs().length > 0) {
				this.platformImgs = movieJson.getPlatformImgs()[0];
			}
			if (StrUtil.notEmpty(movieJson.getPlayCount())) {
				this.playCount = Integer.valueOf(movieJson.getPlayCount());
			}
			this.gdi = movieJson.getGdi();
			this.hotCount = movieJson.getHotCount();
			if (this.platformImgs.indexOf(TENGXUN_FLAG) > -1) {
				this.platformId = "3";
			} else if (this.platformImgs.indexOf(AIQIYI_FLAG) > -1) {
				this.platformId = "1";
			} else if (this.platformImgs.indexOf(YOUKU_FLAG) > -1) {
				this.platformId = "2";
			} else if (this.platformImgs.indexOf(SOUHU_FLAG) > -1) {
				this.platformId = "5";
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		this.days = days;
	}

	public String getPlatformImgs() {
		return platformImgs;
	}

	public void setPlatformImgs(String platformImgs) {
		this.platformImgs = platformImgs;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
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
}