package com.liuyi.web.serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.DownTool;
import com.liuyi.web.service.HtmlParserTool;
import com.liuyi.web.service.LinkFilter;
import com.liuyi.web.service.SpiderService;

@Service
public class SpiderServiceImpl implements SpiderService {
    @Autowired
    private SpiderDao spiderDao;
    
    @Override
    public int crawling(List<String> urlList) {
        final String baseurl = urlList.get(0);
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                String pattern ="[a-zA-z]+://[^\\s]+/[^\\D+]+/[^\\D+]+/[^\\D+]+/.*";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(url);
                if (m.matches()) 
                    return true;
                else
                    return false;
            }
        };
        if (urlList != null && urlList.size() > 0) {
            Iterator<String> iter = urlList.iterator();
            while (iter.hasNext()) {
                String url = iter.next();
                if (urlList == null) {
                    continue;
                }
                DownTool downLoader = new DownTool();
                SpiderWaitQueue sq = new SpiderWaitQueue();
                String path = "";
                // 下载网页
                try {
                    path = downLoader.downloadFile(url);
                } catch (Exception e) {
                    sq.setIsDownload(2);//设置下载失败
                    sq.setUrl(url);
                    spiderDao.updateDownloadUrl(sq);
                    urlList = spiderDao.selectWaitUrl();
                    iter = urlList.iterator();
                    System.out.println("下载失败"+url);
                    continue;
                }
                
                sq.setUrl(url);
                sq.setDownpath(path);
                Date now = new Date();
                sq.setUpdateTime(now);
                sq.setIsDownload(1);//设置已下载
                spiderDao.updateDownloadUrl(sq);
                // 提取出下载网页中的 URL
                Set<String> links = HtmlParserTool.extracLinks(url, filter);
                // 新的未访问的 URL 入队
                for (String link : links) {
                    if (spiderDao.selectByUrl(link) == 0) {
                          spiderDao.insertNewUrl(link, url,1);
                    }
                }
                urlList = spiderDao.selectWaitUrl();
                iter = urlList.iterator();
            }
        }
        return 0;
    }
    
    @Override
    public int insertNewUrl(String url) {
        
        return 0;
    }
    
    @Override
    public int updateDownloadUrl(String url) {
        
        return 0;
    }
    
    @Override
    public String insertDownloadUrl(String url) {
        
        return null;
    }
    
    @Override
    public List<String> selectWaitUrl() {
        
        return spiderDao.selectWaitUrl();
    }
    
}
