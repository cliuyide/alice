package com.liuyi.web.model;

import java.util.Date;

public class HtmlMessage {
    private String id;
    private String url;               //网页
    private int    type;             //文章种类
    private String keywords;         // 关键字
    private String editor;          //作者
    private String title;//标题
    private String articleSource;//文章来源
    private String articleContent;//正文
    private Date   articleCreateTime;//文章创建时间
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public String getEditor() {
        return editor;
    }
    
    public void setEditor(String editor) {
        this.editor = editor;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getArticleSource() {
        return articleSource;
    }
    
    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }
    
    public String getArticleContent() {
        return articleContent;
    }
    
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
    
    public Date getArticleCreateTime() {
        return articleCreateTime;
    }
    
    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    private Date createTime;
    
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
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
