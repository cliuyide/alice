package com.liuyi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liuyi.web.service.LoginService;
import com.liuyi.web.service.SpiderService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private SpiderService spiderService;
	@RequestMapping(value = "login/login.do")
	public ModelAndView Login() {
		System.out.println("登录成功");
		ModelAndView result = new ModelAndView();
		List<String> waitUrlList=spiderService.selectWaitUrl();
		spiderService.crawling(waitUrlList);
		result.setViewName("/alice/login");
		return result;
	}
}
