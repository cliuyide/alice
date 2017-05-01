package com.liuyi.web.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuyi.web.contanst.RegularContanst;
import com.liuyi.web.dao.MeituanDao;
import com.liuyi.web.model.HtmlMeituan;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.MeituanService;
import com.liuyi.web.service.SpiderService;
import com.liuyi.web.thread.AnalysisThread;
import com.liuyi.web.thread.GetUrlThread;
import com.liuyi.web.util.MeiTuanAnalysisUtil;

@Service
public class MeituanServiceImpl implements MeituanService {
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private MeituanDao meituanDao;
    int userAgentNumber=0;
	@Override
	public void analysisHtmlMeituan() throws IOException {
		System.setProperty("proxySet", "true");
		System.setProperty("http.proxyHost", "61.191.173.31");   
		System.setProperty("http.proxyPort", "808");
		List<String> listUrl = spiderService.selectIsNotAnalysis();
		String[] userAgent=RegularContanst.userAgent;
		for (int i = 0; i < 5; i++) {
			if(i*10<listUrl.size()){
				AnalysisThread at = new AnalysisThread(listUrl, i, meituanDao,
						spiderService,userAgent[userAgentNumber]);
				Thread thread = new Thread(at);
				try {
					Thread.sleep(5000);
					thread.start();
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
		if (listUrl != null && listUrl.size() > 0) {
			userAgentNumber=userAgentNumber<=userAgent.length?userAgentNumber:0;
			userAgentNumber++;
			this.analysisHtmlMeituan();
		}
	}

}
