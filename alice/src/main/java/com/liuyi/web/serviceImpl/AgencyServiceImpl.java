package com.liuyi.web.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuyi.web.dao.AgencyDao;
import com.liuyi.web.model.AgencyIp;
import com.liuyi.web.service.AgencyService;
@Service
public class AgencyServiceImpl implements AgencyService {
	@Autowired
	private AgencyDao agencyDao;


	@Override
	public int insertSelective(AgencyIp record) {
		try {
			String url="http://www.xicidaili.com/nn/";
			for(int i=1;i<1950;i++){
				System.out.println("我在跑==========================");
				Document doc = Jsoup
						.connect(url+i)
						.header("User-Agent",
								"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5")
						.header("Accept",
								"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
						.header("Accept-Language",
								"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
						.header("Accept-Encoding", "gzip, deflate").timeout(10000).get();
				analysis(doc);
			}
			
		} catch (Exception e) {
		}
		return 0;
	}


	public void analysis(Document doc) {
		Elements trElements = doc.getElementsByTag("tr");
		for (Element element : trElements) {
			try {
				Elements tdElements = element.getElementsByTag("td");
				createIPAddress(tdElements.get(1).html(),
						Integer.valueOf(tdElements.get(2).html()));
			} catch (Exception e) {
				System.out.println("error:" + e.getMessage());
				continue;
			}

		}
	}

	public  void createIPAddress(String ip, int port) {
		AgencyIp agency=new AgencyIp();
		agency.setPort(port);
		agency.setId(UUID.randomUUID().toString());
		agency.setHost(ip);
		agency.setIsUsed(2);
		agency.setUpdateTime(new Date());
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			System.out.println("url invalidate");
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip, port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http proxy
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(1000);
			in = conn.getInputStream();
		} catch (Exception e) {
			agency.setIsTrue(2);
		}
		String s = convertStreamToString(in);
		System.out.println(s);
		if (s.indexOf("baidu") > 0) {// 有效IP
			agency.setIsTrue(1);
			System.out.println(ip + ":" + port + " is ok");
		}
		agencyDao.add(agency);
	}

	public  String convertStreamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}


	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public AgencyIp selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int updateByPrimaryKeySelective(AgencyIp record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateByPrimaryKey(AgencyIp record) {
		// TODO Auto-generated method stub
		return 0;
	}
}
