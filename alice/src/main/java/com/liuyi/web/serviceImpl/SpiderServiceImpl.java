package com.liuyi.web.serviceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuyi.web.contanst.RegularContanst;
import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.SpiderService;
import com.liuyi.web.util.ArticleUtil;
import com.liuyi.web.util.DownTool;
import com.liuyi.web.util.HtmlParserTool;
import com.liuyi.web.util.LinkFilter;

@Service
public class SpiderServiceImpl implements SpiderService {
	@Autowired
	private SpiderDao spiderDao;

	@Override
	public int crawling(List<String> urlList) {
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
				if (StringUtils.isNotBlank(url) && url.contains("meituan")
						&& spiderDao.selectByUrl(url) == 0) {
					return true;
				} else
					return false;
			}
		};
		String pattern = RegularContanst.MEITUAN_DEAL;
		if (urlList != null && urlList.size() > 0) {
			Iterator<String> iter = urlList.iterator();
			while (iter.hasNext()) {
				String url = iter.next();
				if (urlList == null) {
					continue;
				}
				// DownTool downLoader = new DownTool();
				SpiderWaitQueue sq = new SpiderWaitQueue();
				String path = "";
				// // 下载网页
				// try {
				// path = downLoader.downloadFile(url);
				//
				// } catch (Exception e) {
				// sq.setIsDownload(2);// 设置下载失败
				// sq.setUrl(url);
				// spiderDao.updateDownloadUrl(sq);
				// System.out.println("下载失败" + url);
				// continue;
				// }
				// ArticleUtil articleUtil = new ArticleUtil();
				// try {
				// spiderDao.insertHtmlMessage(articleUtil
				// .createArticleMessage(url));
				// } catch (IOException e) {
				// e.printStackTrace();
				// continue;
				// } catch (ParseException e) {
				// e.printStackTrace();
				// continue;
				// } catch (Exception e) {
				//
				// }
				sq.setUrl(url);
				sq.setDownpath(path);
				Date now = new Date();
				sq.setUpdateTime(now);
				sq.setIsDownload(1);// 设置已下载
				spiderDao.updateDownloadUrl(sq);
				// 提取出下载网页中的 URL
				Set<String> links;
				try {
					links = HtmlParserTool.extracLinks(url, filter);
					// 新的未访问的 URL 入队
					for (String link : links) {
						Integer type = 0;// 网页类型默认普通
						Pattern r = Pattern.compile(pattern);
						Matcher matcher = r.matcher(link);

						if (link.contains("deal")) {
							if (matcher.find()) {
								String link1 = matcher.group(0);
								type = 2;
								if (spiderDao.selectByUrl(link1) == 0) {
									System.out.println(link1 + "添加成功");
									spiderDao.insertNewUrl(link1, url, type);
								}
							} else {
								System.out.println(link + "添加失败");
							}

						} else {
							spiderDao.insertNewUrl(link, url, type);
						}

					}
				} catch (IOException e) {
					sq.setIsDownload(2);// 设置下载失败
					sq.setUrl(url);
					spiderDao.updateDownloadUrl(sq);
					e.printStackTrace();
					continue;
				} catch (Exception e) {
					sq.setIsDownload(2);// 设置下载失败
					sq.setUrl(url);
					spiderDao.updateDownloadUrl(sq);
					continue;
				}
			}
			urlList = spiderDao.selectWaitUrl();
			this.crawling(urlList);
		}
		return 0;
	}

	@Override
	public int insertNewUrl(String url) {

		return 0;
	}

	@Override
	public int updateDownloadUrl(SpiderWaitQueue sq) {

		return spiderDao.updateDownloadUrl(sq);
	}

	@Override
	public String insertDownloadUrl(String url) {

		return null;
	}

	@Override
	public List<String> selectWaitUrl() {

		return spiderDao.selectWaitUrl();
	}

	@Override
	public List<String> selectIsNotAnalysis() {
		return spiderDao.selectIsNotAnalysis();
	}

	@Override
	public int updateAnalysisError(String url) {

		return spiderDao.updateAnalysisError(url);
	}

}
