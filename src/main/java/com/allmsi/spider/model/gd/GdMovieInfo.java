package com.allmsi.spider.model.gd;

import java.util.Date;

import com.allmsi.spider.util.StrUtil;

public class GdMovieInfo {
	private String id;

	private String showId;

	private String showName;

	private String coverUrl;

	private String coverImage;

	private String category;

	private String releaseDate;

	private String days;

	private int duration;

	private String intro;

	private Date cTime;

	public GdMovieInfo() {

	}

	public GdMovieInfo(GdMovieInfoJson movieInfoJson,String id,String coverImage) {
		if (movieInfoJson != null) {
			this.id =id;
			this.showId = movieInfoJson.getShowId();
			this.showName = movieInfoJson.getShow_name();
			this.coverUrl = movieInfoJson.getCover();
			this.coverImage=coverImage;
			this.category = movieInfoJson.getCategory();
			this.releaseDate = movieInfoJson.getRelease_date();
			this.days = movieInfoJson.getDays();
			if (StrUtil.notEmpty(movieInfoJson.getDuration()) && StrUtil.isNumeric(movieInfoJson.getDuration())) {
				this.duration = Integer.parseInt(movieInfoJson.getDuration());
			} else {
				this.duration = 0;
			}
			this.intro = movieInfoJson.getIntro();
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

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName == null ? null : showName.trim();
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate == null ? null : releaseDate.trim();
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days == null ? null : days.trim();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
}