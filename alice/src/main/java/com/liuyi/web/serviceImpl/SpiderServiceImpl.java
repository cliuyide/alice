package com.liuyi.web.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		final String baseurl=urlList.get(0);
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
				if (url.startsWith(baseurl))
					return true;
				else
					return false;
			}
		};
		if (urlList != null && urlList.size() > 0) {
			Iterator<String> iter = urlList.iterator();
			while (iter.hasNext()) {
				String url=iter.next();
				if (urlList == null){
					continue;
				}
				DownTool downLoader = new DownTool();
				// 下载网页
				String path=downLoader.downloadFile(url);
				SpiderWaitQueue sq=new SpiderWaitQueue();
				sq.setUrl(url);
				sq.setDownpath(path);
				spiderDao.updateDownloadUrl(sq);
				// 提取出下载网页中的 URL
				Set<String> links = HtmlParserTool.extracLinks(url, filter);
				// 新的未访问的 URL 入队
				for (String link : links) {
					spiderDao.insertNewUrl(link);
				}
				urlList=spiderDao.selectWaitUrl();
				iter=urlList.iterator();
			}
		}
		return 0;
	}

	@Override
	public int insertNewUrl(String url) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDownloadUrl(String url) {
		// TODO Auto-generated method stub
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
