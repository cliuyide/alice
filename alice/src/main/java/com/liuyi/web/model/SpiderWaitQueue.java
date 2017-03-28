package com.liuyi.web.model;

import java.util.Date;

public class SpiderWaitQueue {
    private String    id; //uuid
    private String    url; //网页地址
    private String    downpath; //下载地址
    private Date createTime; //创建时间
    private Date updateTime;  //更新时间
    private Integer   isDownload;  //是否已下载
    private Integer   isanalysis;  //是否已解析
    private Integer   type;   //网页种类
    private String fatherUrl;//父url
    public String getFatherUrl() {
        return fatherUrl;
    }

    public void setFatherUrl(String fatherUrl) {
        this.fatherUrl = fatherUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date now) {
        this.createTime = now;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsanalysis() {
        return isanalysis;
    }

    public void setIsanalysis(Integer isanalysis) {
        this.isanalysis = isanalysis;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDownpath() {
        return downpath;
    }
    
    public void setDownpath(String downpath) {
        this.downpath = downpath;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Integer getIsDownload() {
        return isDownload;
    }
    
    public void setIsDownload(Integer isDownload) {
        this.isDownload = isDownload;
    }
    
}
