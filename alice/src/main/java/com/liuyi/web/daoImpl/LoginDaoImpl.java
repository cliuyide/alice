package com.liuyi.web.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyi.web.dao.LoginDao;
import com.liuyi.web.mapping.UserMapperInterface;

@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private UserMapperInterface userMapperInterface;

	public List<Map<String, Object>> find() {

		return userMapperInterface.find();
	}

}
