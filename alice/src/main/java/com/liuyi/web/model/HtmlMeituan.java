package com.liuyi.web.model;

import java.util.Date;

public class HtmlMeituan {
    private String id;

    private String url;

    private String keyword;//关键词

    private String city;//城市

    private String gradeType;//商品分类

    private String componentDescription;//描述

    private Double defaultPrice;//默认价格

    private Double discountPrice;//美团折扣价格

    private Integer outQuantities;//已售出

    private Double rating;//评分

    private Integer collectCount;//收藏

    private Integer ratingPersons;//评分人数

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private Date endTime;//截止时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription == null ? null : componentDescription.trim();
    }

    public Double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getOutQuantities() {
        return outQuantities;
    }

    public void setOutQuantities(Integer outQuantities) {
        this.outQuantities = outQuantities;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getRatingPersons() {
        return ratingPersons;
    }

    public void setRatingPersons(Integer ratingPersons) {
        this.ratingPersons = ratingPersons;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}