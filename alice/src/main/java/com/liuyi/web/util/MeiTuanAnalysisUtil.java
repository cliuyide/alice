package com.liuyi.web.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.liuyi.web.contanst.RegularContanst;
import com.liuyi.web.model.HtmlMeituan;

public class MeiTuanAnalysisUtil {
	public HtmlMeituan meituanAnalysis(String url,String userAgent) throws IOException {		
		HtmlMeituan hm = new HtmlMeituan();
//		String ip = "202.121.96.33";
//		String port = "8086";
//		System.getProperties().setProperty("http.proxyHost", ip);
//		System.getProperties().setProperty("http.proxyPort", port);
		Document html = Jsoup
				.connect(url)
				.header("User-Agent",userAgent)
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Language",
						"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Accept-Encoding", "gzip, deflate").timeout(5000).get();
		// 获取页面关键字
		Elements keywords = this.replaceEmpty(html.getElementsByAttributeValue(
				"name", "keywords"));
		String keyword = keywords.get(0).attr("content");
		hm.setKeyword(keyword);
		// 获取所在城市
		Elements citys = this.replaceEmpty(html.getElementsByAttributeValue(
				"gaevent", "header/cityname"));
		String city = citys.get(0).html();
		hm.setCity(city);
		// 获取商品分类用,号隔开
		Elements gradeType = this.replaceEmpty(html
				.getElementsByAttributeValue("class", "bread-nav"));
		Elements gradeType_a = this.replaceEmpty(gradeType.get(0)
				.getElementsByTag("a"));
		String categorys = (gradeType_a.html() + "\n" + gradeType.get(0)
				.ownText()).replaceAll("\n", ",");
		hm.setGradeType(categorys);
		// 获取商品描述
		Elements descriptions = this.replaceEmpty(html
				.getElementsByClass("deal-component-description"));
		String component_description = descriptions.get(0).html();
		hm.setComponentDescription(component_description);
		// 获取默认价格
		String priceSpanEl = this
				.replaceEmpty(html.getElementsByTag("textarea")).first()
				.toString();
		Element priceTextEl = this.textareaToElement(priceSpanEl);
		String defaultPrice = priceTextEl.getElementsByClass("item sans-serif")
				.html().substring(1);
		Double dfprice = StringUtils.isNotBlank(defaultPrice) ? Double
				.parseDouble(defaultPrice) : 0;
		hm.setDefaultPrice(dfprice);
		// 获取美团价格
		try {
			Elements meiTuanPriceEl = this.replaceEmpty(html
					.getElementsByClass("J-buy btn-hot buy"));
			String meiTuanPriceElHref = meiTuanPriceEl.first().attr("href");
			String regexp = "\\b/deal\\b.*";
			String priceElHref = this.replace(url, regexp, meiTuanPriceElHref);
			Document pricehtml = Jsoup
					.connect(priceElHref)
					.header("User-Agent",userAgent)
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("Accept-Language",
							"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header("Accept-Encoding", "gzip, deflate")
					.get();
			String price = pricehtml.getElementById("deal-buy-price").html();
			Double meituanprice = StringUtils.isNotBlank(price) ? Double
					.parseDouble(price) : 0;
			hm.setDiscountPrice(meituanprice);
		} catch (Exception e) {
			hm.setDiscountPrice(dfprice);
		}
		// 已售出
		String outCount = this
				.replaceEmpty(
						html.getElementsByClass("deal-component-rating-sold-count"))
				.first().html();
		Integer oc = StringUtils.isNotBlank(outCount) ? Integer
				.valueOf(outCount) : 0;
		hm.setOutQuantities(oc);
		// 平均分
		String averageScore = this
				.replaceEmpty(
						html.getElementsByClass("deal-component-rating-stars"))
				.first().html();
		Double as = StringUtils.isNotBlank(averageScore) ? Double
				.parseDouble(averageScore) : 0;
		hm.setRating(as);
		// 评论人数
		String commentPerson = this
				.replaceEmpty(
						html.getElementsByClass("deal-component-rating-comment-count"))
				.first().html();
		Integer cp = StringUtils.isNotBlank(commentPerson) ? Integer
				.valueOf(commentPerson) : 0;
		hm.setRatingPersons(cp);
		// 收藏数
		String collectPerson = this
				.replaceEmpty(html.getElementsByClass("J-fav-count")).first()
				.html();
		Integer clp = StringUtils.isNotBlank(collectPerson) ? Integer
				.valueOf(collectPerson) : 0;
		hm.setCollectCount(clp);
		hm.setCreateTime(new Date());
		// 截止日期
		String endtimeText = html
				.getElementsByClass("deal-component-expiry-valid-through")
				.first().html();
		String endTime = endtimeText.trim().substring(3);
		endTime = endTime.replace(".", "-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date endDate = sdf.parse(endTime);
			hm.setEndTime(endDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return hm;
	}

	// 将textarea中的文本转换成元素,用div包起来
	public Element textareaToElement(String priceSpanEl) {
		priceSpanEl = priceSpanEl.replaceAll("&gt;", ">").replaceAll("&lt;",
				"<");
		priceSpanEl = priceSpanEl.substring(priceSpanEl.indexOf(">") + 1,
				priceSpanEl.lastIndexOf("<") - 1);
		Element els = new Element("div");
		return els.append(priceSpanEl);
	}

	public String replace(String str, String regexp, String replaceStr) {
		str = str.replaceAll(regexp, replaceStr);
		return str + ".html";
	}

	public Elements replaceEmpty(Elements elements) {
		if (elements.size() == 0) {
			elements.add(new Element("div"));
			return elements;
		}
		return elements;
	}

	public Date getEndTime(String url,String userAgent) throws IOException {
		Document html = Jsoup
				.connect(url)
				.header("User-Agent",userAgent)
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Language",
						"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Accept-Encoding", "gzip, deflate").get();
		// 截止日期
		String endtimeText = html
				.getElementsByClass("deal-component-expiry-valid-through")
				.first().html();
		String endTime = endtimeText.trim().substring(3);
		endTime = endTime.replace(".", "-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = null;
		try {
			endDate = sdf.parse(endTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return endDate;
	}
}
