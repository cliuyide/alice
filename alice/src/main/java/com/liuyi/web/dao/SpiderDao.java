package com.liuyi.web.dao;

import java.util.List;

import com.liuyi.web.model.SpiderWaitQueue;

public interface SpiderDao {
	/**
	 * 向待抓取表中插入新增加的url
	 * @param url
	 * @return
	 */
	public int insertNewUrl(String url,String fatherUrl,Integer type);
	/**
	 * 更新已下载url
	 * @param url
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
	 * 通过url判断数据库是否已经有这条数据
	 * @param url
	 * @return
	 */
	public Integer selectByUrl(String url);
}
