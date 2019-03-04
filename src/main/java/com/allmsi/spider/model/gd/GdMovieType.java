package com.allmsi.spider.model.gd;

import com.allmsi.spider.util.UUIDUtil;

public class GdMovieType {

	private String id;

	private String showId;

	private String typeName;

	private int typeOrder;

	public GdMovieType() {
	}

	public GdMovieType( String showId, String typeName, int typeOrder) {
		this.id =UUIDUtil.getUUID();
		this.showId = showId;
		this.typeName = typeName;
		this.typeOrder = typeOrder;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}

	public int getTypeOrder() {
		return typeOrder;
	}

	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}
}