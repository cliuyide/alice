package com.liuyi.web.serviceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyi.web.contanst.RegularContanst;
import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.DownTool;
import com.liuyi.web.service.HtmlParserTool;
import com.liuyi.web.service.LinkFilter;
import com.liuyi.web.service.SpiderService;
import com.liuyi.web.util.ArticleUtil;

@Service
public class SpiderServiceImpl implements SpiderService {
    @Autowired
    private SpiderDao spiderDao;
    
    @Override
    public int crawling(List<String> urlList) {      
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (StringUtils.isNotBlank(url) && url.contains("meituan")
                        &&url.contains("deal")&& spiderDao.selectByUrl(url) == 0) {
                    return true;
                } else
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
                // DownTool downLoader = new DownTool();
                SpiderWaitQueue sq = new SpiderWaitQueue();
                // String path = "";
                // // 下载网页
                // try {
                // path = downLoader.downloadFile(url);
                //
                // } catch (Exception e) {
                // sq.setIsDownload(2);// 设置下载失败
                // sq.setUrl(url);
                // spiderDao.updateDownloadUrl(sq);
                // System.out.println("下载失败" + url);
                // continue;
                // }
                // ArticleUtil articleUtil = new ArticleUtil();
                // try {
                // spiderDao.insertHtmlMessage(articleUtil.createArticleMessage(url));
                // } catch (IOException e) {
                // e.printStackTrace();
                // continue;
                // } catch (ParseException e) {
                // e.printStackTrace();
                // continue;
                // } catch (Exception e){
                //
                // }
//                sq.setUrl(url);
                // sq.setDownpath(path);
//                Date now = new Date();
//                sq.setUpdateTime(now);
//                sq.setIsDownload(1);// 设置已下载
//                spiderDao.updateDownloadUrl(sq);
                // 提取出下载网页中的 URL
                Set<String> links;
                try {
                    links = HtmlParserTool.extracLinks(url, filter);
                    // 新的未访问的 URL 入队
                    for (String link : links) {
                        Integer type = 0;// 网页类型默认普通
                        String pattern = RegularContanst.MEITUAN_DEAL;
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(url);
                        if (m.matches()) {
                            type = 2;
                        }
                        spiderDao.insertNewUrl(link, url, type);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    
                }
            }
            urlList = spiderDao.selectWaitUrl();
            iter = urlList.iterator();
            crawling(urlList);
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
