package com.liuyi.web.thread;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liuyi.web.contanst.RegularContanst;
import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.util.HtmlParserTool;
import com.liuyi.web.util.LinkFilter;

public class GetUrlThread implements Runnable{
private List<String> urlList;
private int origin;//遍历的起点
private SpiderDao spiderDao;
private LinkFilter filter;
	public GetUrlThread(List<String> urlList,int origin,SpiderDao spiderDao,LinkFilter filter){
		this.urlList=urlList;
		this.origin=origin;
		this.spiderDao=spiderDao;
		this.filter=filter;
	}
	@Override
	public void run() {
		for(int i=origin*10;i<origin*10+10;i++){
			String url = urlList.get(i).toString();			
			SpiderWaitQueue sq = new SpiderWaitQueue();
			String path = "";			
			sq.setUrl(url);
			sq.setDownpath(path);
			Date now = new Date();
			sq.setUpdateTime(now);
			sq.setIsDownload(1);// 设置已下载
			spiderDao.updateDownloadUrl(sq);
			// 提取出下载网页中的 URL
			Set<String> links;
			try {
				links = HtmlParserTool.extracLinks(url, filter);
				// 新的未访问的 URL 入队
				for (String link : links) {
					Integer type = 0;// 网页类型默认普通
					Pattern r = Pattern.compile(RegularContanst.MEITUAN_DEAL);
					Matcher matcher = r.matcher(link);
					if (link.contains("deal")) {
						if (matcher.find()) {
							String link1 = matcher.group(0);
							type = 2;
							if (spiderDao.selectByUrl(link1) == 0) {
								System.out.println(link1 + "添加成功");
								spiderDao.insertNewUrl(link1, url, type,0);
							}
						} else {
							System.out.println(link + "添加失败");
						}

					} else {
						Pattern category = Pattern.compile(RegularContanst.CATEGORY_DEAL);
						Matcher mcategory = category.matcher(link);
						Pattern shop = Pattern.compile(RegularContanst.SHOP_DEAL);
                        Matcher mshop = shop.matcher(link);
						Integer extractRank=0;
						if(mcategory.matches()){
							extractRank=2;
						}else if(mshop.matches()){
						    extractRank=3;
						}
						spiderDao.insertNewUrl(link, url, type,extractRank);
					}

				}
			} catch (IOException e) {
				sq.setIsDownload(2);// 设置下载失败
				sq.setUrl(url);
				spiderDao.updateDownloadUrl(sq);
				e.printStackTrace();
				continue;
			} catch (Exception e) {
				sq.setIsDownload(2);// 设置下载失败
				sq.setUrl(url);
				spiderDao.updateDownloadUrl(sq);
				continue;
			}
		}
	}

}
