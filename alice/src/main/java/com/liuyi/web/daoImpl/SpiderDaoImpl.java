package com.liuyi.web.daoImpl;

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
	public int insertNewUrl(String url) {
		SpiderWaitQueue sq = new SpiderWaitQueue();
		sq.setId(UUID.randomUUID().toString());
		sq.setIsDownload(0);
		sq.setUrl(url);
		return spiderMapperInterface.insertNewUrl(sq);
	}

	@Override
	public int updateDownloadUrl(SpiderWaitQueue sq) {
		sq.setIsDownload(1);
		return spiderMapperInterface.updateDownloadUrl(sq);
	}

	@Override
	public String insertDownloadUrl(String url) {
		HtmlMessage hm=new HtmlMessage();
		hm.setId(UUID.randomUUID().toString());
		hm.setUrl(url);
		return spiderMapperInterface.insertDownloadUrl(hm);
	}

	@Override
	public List<String> selectWaitUrl() {
		return spiderMapperInterface.selectWaitUrl();
	}

}
