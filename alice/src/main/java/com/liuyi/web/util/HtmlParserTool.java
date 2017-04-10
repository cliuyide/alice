package com.liuyi.web.util;

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
	// 获取一个网站上的链接，filter 用来过滤链接
	public static Set<String> extracLinks(String url, LinkFilter filter)
			throws IOException {
		Set<String> result = new HashSet<String>();
		Document doc = Jsoup
				.connect(url)
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
				.header("Cookie",
						"rvct=258; mtcdn=K; iuuid=13FFE02E13A3D1098FB7E815B4BE06BDD0699C1E14CD4A22DD89F6A2759984BE; _lx_utm=; rvd=44274690%2C44274638%2C28576125%2C41133841%2C44149664; abt=1491434780.0%7CBDE; ci=99; __mta=141892877.1490711847258.1491436956596.1491437165926.104; uuid=5b6ed087d10a27e61227.1490711847.0.0.0; oc=Fs5Mg20K5uwkml2_GtyinNJDohyzWfUZtwzUiCVJeCHur6qTk6wLMn8nh7vZ--sFyFltSC1AAcmnommLo_6OUPUeRK4eY501fP906hIQsarV_4VUJiUN1cl7m0vhhuQxKUhlXpFkOJ0E1Jr8-XPHQKT8q4gZSsIw9G2_TDZ-dG8; __utma=211559370.1371189777.1491269839.1491401817.1491434768.11; __utmz=211559370.1491394680.8.2.utmcsr=baidu|utmccn=baidu|utmcmd=organic|utmcct=homepage; __utmv=211559370.|1=city=nn=1^3=dealtype=237=1^5=cate=new=1")
				.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").get();
		Elements links = doc.select("a[href]");
		// Elements media = doc.select("[src]");
		// Elements imports = doc.select("link[href]");
		// for (Element src : media) {
		// if (src.tagName().equals("img"))
		// System.out.println("图片"+src.attr("abs:src"));
		// else
		// System.out.println("src"+src.attr("abs:src"));
		// }
		// for (Element link : imports) {
		// System.out.println("importantLink"+link.attr("abs:href"));
		// }

		for (Element link : links) {
			String addUrl = link.attr("abs:href");
			if (StringUtils.isNotBlank(addUrl) && filter.accept(addUrl)) {
				result.add(addUrl);
			}

		}
		return result;
	}
}
