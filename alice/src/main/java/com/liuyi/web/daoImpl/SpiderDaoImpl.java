package com.liuyi.web.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.mapping.SpiderMapperInterface;
import com.liuyi.web.model.HtmlMessage;
import com.liuyi.web.model.SpiderWaitQueue;

@Repository
public class SpiderDaoImpl implements SpiderDao {
    @Autowired
    private SpiderMapperInterface spiderMapperInterface;
    
    @Override
    public int insertNewUrl(String url, String fatherUrl, Integer type) {
        SpiderWaitQueue sq = new SpiderWaitQueue();
        sq.setId(UUID.randomUUID().toString());
        sq.setIsDownload(0);// 设置未下载
        sq.setUrl(url);
        sq.setCreateTime(new Date());
        sq.setType(type);
        sq.setIsanalysis(0);// 设置未解析
        sq.setFatherUrl(fatherUrl);
        return spiderMapperInterface.insertNewUrl(sq);
    }
    
    @Override
    public int updateDownloadUrl(SpiderWaitQueue sq) {
        sq.setUpdateTime(new Date());
        return spiderMapperInterface.updateDownloadUrl(sq);
    }
    
    @Override
    public String insertDownloadUrl(String url) {
        HtmlMessage hm = new HtmlMessage();
        hm.setId(UUID.randomUUID().toString());
        hm.setUrl(url);
        return spiderMapperInterface.insertDownloadUrl(hm);
    }
    
    @Override
    public List<String> selectWaitUrl() {
        return spiderMapperInterface.selectWaitUrl();
    }
    
    @Override
    public Integer selectByUrl(String url) {
        return spiderMapperInterface.selectByUrl(url);
    }
    
    @Override
    public Integer insertHtmlMessage(HtmlMessage htmlMessage) {
        
        return spiderMapperInterface.insertHtmlMessage(htmlMessage);
    }
    
}
