package com.liuyi.web.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyi.web.dao.MeituanDao;
import com.liuyi.web.mapping.HtmlMeituanMapperInterface;
import com.liuyi.web.model.HtmlMeituan;

@Repository
public class MeiTuanDaoImpl implements MeituanDao {
	@Autowired
	private HtmlMeituanMapperInterface htmlMeituanMapperInterface;

	@Override
	public int insertSelective(HtmlMeituan record) {
		
		return htmlMeituanMapperInterface.insertSelective(record);
	}

}
