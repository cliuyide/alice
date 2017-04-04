package com.liuyi.web.service;

import java.util.List;

import com.liuyi.web.model.SpiderWaitQueue;

public interface SpiderService {
	/**
	 * 调用处理url方法
	 * 
	 * @param urlList
	 * @return
	 */
	public int crawling(List<String> urlList);
	/**
	 * 向待抓取表中插入新增加的url
	 * @param url
	 * @return
	 */
	public int insertNewUrl(String url);
	/**
	 * 更新已下载url
	 * @param SpiderWaitQueue
	 * @return
	 */
	public int updateDownloadUrl(SpiderWaitQueue sq);
	/**
	 * 插入已下载url信息
	 * @param url
	 * @return
	 */
	public String insertDownloadUrl(String url);
	/**
	 * 查询未被下载的url
	 * @return
	 */
	public List<String> selectWaitUrl();
	 /**
     * @return
     */
    public List<String> selectIsNotAnalysis();
}
