package com.liuyi.web.thread;

import java.util.List;
import java.util.UUID;

import com.liuyi.web.dao.MeituanDao;
import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.model.HtmlMeituan;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.SpiderService;
import com.liuyi.web.util.MeiTuanAnalysisUtil;

public class AnalysisThread implements Runnable {
	private List<String> urlList;
	private int origin;// 遍历的起点
	private MeituanDao meituanDao;
	private SpiderService spiderService;
	private String userAgent;
		public AnalysisThread(List<String> urlList, int origin,
			MeituanDao meituanDao, SpiderService spiderService,String userAgent) {
		this.urlList = urlList;
		this.origin = origin;
		this.meituanDao = meituanDao;
		this.spiderService = spiderService;
		this.userAgent=userAgent;
	}

	@Override
	public void run() {
		for (int i=origin*10;i<origin*10+10;i++) {
			String url=urlList.get(i).toString();
			System.out.println(url+"开始解析");
			try {				
				HtmlMeituan mtau = new MeiTuanAnalysisUtil()
						.meituanAnalysis(url,userAgent);
				mtau.setId(UUID.randomUUID().toString());
				mtau.setUrl(url);
				meituanDao.insertSelective(mtau);
				SpiderWaitQueue sq = new SpiderWaitQueue();
				sq.setIsanalysis(1);
				sq.setUrl(url);
				spiderService.updateDownloadUrl(sq);
				System.out.println(url+"解析完成");
			} catch (Exception e) {
				spiderService.updateAnalysisError(url);
				System.out.println(url + "解析失败");
				continue;
			}
		}

	}
}
