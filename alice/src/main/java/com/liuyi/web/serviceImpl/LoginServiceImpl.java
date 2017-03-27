package com.liuyi.web.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuyi.web.dao.LoginDao;
import com.liuyi.web.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
@Autowired
private LoginDao loginDao;
	public List<Map<String, Object>> find() {
		return loginDao.find();
	}

}
