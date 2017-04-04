package com.liuyi.web.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuyi.web.dao.MeituanDao;
import com.liuyi.web.model.HtmlMeituan;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.MeituanService;
import com.liuyi.web.service.SpiderService;
import com.liuyi.web.util.MeiTuanAnalysisUtil;

@Service
public class MeituanServiceImpl implements MeituanService {
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private MeituanDao meituanDao;
	@Override
	public void analysisHtmlMeituan() throws IOException {
		List<String> listUrl=spiderService.selectIsNotAnalysis();
		for(String url:listUrl){
			try {
				HtmlMeituan mtau = new MeiTuanAnalysisUtil().meituanAnalysis(url);
				mtau.setId(UUID.randomUUID().toString());
				mtau.setUrl(url);
				meituanDao.insertSelective(mtau);
				SpiderWaitQueue sq=new SpiderWaitQueue();
				sq.setIsanalysis(1);
				sq.setUrl(url);
				spiderService.updateDownloadUrl(sq);
			} catch (Exception e) {
				spiderService.updateAnalysisError(url);
				System.out.println(url+"解析失败");
				continue;
			}
			
		}		
		analysisHtmlMeituan();
	}

}
