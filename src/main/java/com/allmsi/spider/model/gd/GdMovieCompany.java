package com.allmsi.spider.model.gd;

import com.allmsi.spider.util.UUIDUtil;

public class GdMovieCompany {
    private String id;

    private String showId;

    private String companyName;

    private String companyType;

    private Integer sequence;
    
    public GdMovieCompany(){
    	
    }

    public GdMovieCompany(String showId, String companyName, String companyType, int sequence) {
		this.id=UUIDUtil.getUUID();
		this.showId=showId;
		this.companyName=companyName;
		this.companyType=companyType;
		this.sequence=sequence;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}