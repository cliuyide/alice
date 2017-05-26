package com.liuyi.web.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyi.web.dao.AgencyDao;
import com.liuyi.web.mapping.AgencyIpMapperInterface;
import com.liuyi.web.model.AgencyIp;

@Repository
public class AgencyDaoImpl implements AgencyDao {
	@Autowired
	private AgencyIpMapperInterface agencyIpMapperInterface;


	@Override
	public int add(AgencyIp record) {

		return agencyIpMapperInterface.insert(record);
	}


}
