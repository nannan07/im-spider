package com.allmsi.spider.model.gd;

import com.allmsi.spider.util.UUIDUtil;

public class GdMoviePersonal {
	private String id;

	private String showId;

	private String personName;

	private String avatar;

	private String category;

	private String roles;

	private String personType;

	private Integer sequence;

	public GdMoviePersonal(String showId, String personName, String personType, int sequence) {
		this.id = UUIDUtil.getUUID();
		this.showId = showId;
		this.personName = personName;
		this.personType = personType;
		this.sequence = sequence;
	}

	public GdMoviePersonal(GdActorsJson actorsJson,String showId,String personType) {
		if (actorsJson != null) {
			this.id = UUIDUtil.getUUID();
			this.showId = showId;
			this.personName = actorsJson.getActorName();
			this.personType = personType;
			this.personName = actorsJson.getActorName();
			this.personType = personType;
			this.avatar = actorsJson.getAvatar();
			this.category = actorsJson.getCategory();
			this.roles = actorsJson.getRoles();
			this.sequence = actorsJson.getSequence();
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

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName == null ? null : personName.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles == null ? null : roles.trim();
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType == null ? null : personType.trim();
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
}