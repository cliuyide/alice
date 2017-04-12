package com.liuyi.web.serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuyi.web.dao.SpiderDao;
import com.liuyi.web.model.SpiderWaitQueue;
import com.liuyi.web.service.SpiderService;
import com.liuyi.web.thread.GetUrlThread;
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
		if (urlList != null && urlList.size() > 0) {
			for (int i = 0; i < 5; i++) {
				GetUrlThread gt = new GetUrlThread(urlList, i, spiderDao,
						filter);
				Thread thread = new Thread(gt);
				try {
					Thread.sleep(500);
					thread.start();
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			List<String> urlList1 = spiderDao.selectWaitUrl();
			this.crawling(urlList1);
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
	// DownTool downLoader = new DownTool();
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
}
