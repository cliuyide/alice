package com.liuyi.web.service;

import com.liuyi.web.model.AgencyIp;
import com.liuyi.web.model.HtmlMeituan;

public interface AgencyService {
	int deleteByPrimaryKey(String id);

    int insertSelective(AgencyIp record);

    AgencyIp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AgencyIp record);

    int updateByPrimaryKey(AgencyIp record);
}
