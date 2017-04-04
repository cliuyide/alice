package com.liuyi.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liuyi.web.service.MeituanService;
@Controller
public class AnalysisController {
	@Autowired
	private MeituanService meituanService;
	@RequestMapping(value = "analysis/meituan.do")
	public ModelAndView meituan() throws IOException {
		ModelAndView result=new ModelAndView();
		meituanService.analysisHtmlMeituan();
		return result;
	}
}
