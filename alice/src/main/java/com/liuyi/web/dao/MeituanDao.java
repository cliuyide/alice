package com.liuyi.web.dao;

import com.liuyi.web.model.HtmlMeituan;

public interface MeituanDao {
	/**
	 * 插入数据
	 * @param record
	 * @return
	 */
	int insertSelective(HtmlMeituan record);
}
