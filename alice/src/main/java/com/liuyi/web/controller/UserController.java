package com.liuyi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@RequestMapping(value="user/createUser.do")
	public ModelAndView createUser() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/alice/createUser");
		return mv;
	}
}
