package com.liuyi.web.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserTool {
	private static int i=0;
	// 获取一个网站上的链接，filter 用来过滤链接
	public static Set<String> extracLinks(String url, LinkFilter filter) throws IOException {	
			Set<String> result=new HashSet<String>();
	        Document doc = Jsoup.connect(url).get();  
	        Elements links = doc.select("a[href]");  
//	        Elements media = doc.select("[src]");  
//	        Elements imports = doc.select("link[href]");  
//	        for (Element src : media) {  
//	            if (src.tagName().equals("img"))  
//	               System.out.println("图片"+src.attr("abs:src"));
//	            else  
//	            	System.out.println("src"+src.attr("abs:src")); 
//	        }   
//	        for (Element link : imports) {  
//	            System.out.println("importantLink"+link.attr("abs:href"));  
//	        }  
	      
	        for (Element link : links) {  
	        	String addUrl=link.attr("abs:href");
	        	if(StringUtils.isNotBlank(addUrl)&&filter.accept(addUrl)){
	        		result.add(addUrl);
	        		i++;
	        		System.out.println("link"+link.attr("abs:href"));
	        	}
	        	
	        }  
		return result;
	}
}
