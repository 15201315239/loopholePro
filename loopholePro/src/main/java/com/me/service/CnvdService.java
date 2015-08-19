package com.me.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class CnvdService  implements PageProcessor{
	private Site site=Site.me().setRetryTimes(3).setSleepTime(1000);
	private static Connection connection=null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spider", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	public static void main(String[] args) {
//		new Spider(new CnvdService()).setDownloader().addUrl("").run();
		Request request=new Request("http://www.cnvd.org.cn/flaw/listResult");
		request.setMethod("POST");
		NameValuePair pair1=new BasicNameValuePair("endDate", "");
		NameValuePair pair2=new BasicNameValuePair("startDate", "");
		NameValuePair pair3=new BasicNameValuePair("max", "100");
		NameValuePair pair4=new BasicNameValuePair("offset", "80");
		NameValuePair []nameValuePairs=new NameValuePair[]{pair1,pair2,pair3,pair4};
		Map<String, Object> extras=new HashMap<String, Object>();
		extras.put("nameValuePair", nameValuePairs);
		request.setExtras(extras);
		Task task=new Site().setCharset("UTF-8").setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0").addHeader("Cookie", "__jsluid=6da9f89435c65cd487270494cfb207ea").addHeader("Referer", "http://www.cnvd.org.cn/flaw/list.htm").setSleepTime(100).setTimeOut(100000).toTask();
//		new Spider().setDownloader(new HttpClientDownloader().)
		String string=new HttpClientDownloader().download(request,task).getHtml().toString();
		System.out.println(string);
		System.out.println("ok");
	}

}
