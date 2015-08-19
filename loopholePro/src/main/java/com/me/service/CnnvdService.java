package com.me.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import com.me.entity.Excel;
import com.me.entity.LoopholeCnvd;
import com.me.util.DownloadUtil;
import com.me.util.ExportExcel;
import com.me.util.ExportExcelUtil;
import com.me.util.ExportExcelUtil.ExportSetInfo;
import com.me.util.JDBCConnection;
import com.mysql.jdbc.PreparedStatement;

/** 
 * @author liujun E-mail: 
 * @version 创建时间：2015年8月16日 下午4:05:09 
 */
public class CnnvdService implements PageProcessor{
	private Site site=Site.me().setRetryTimes(3).setSleepTime(1000);
	private static Set<String> set=Collections.synchronizedSet(new HashSet<String>(10000));
	private static AtomicInteger flag=new AtomicInteger(0);
	private String regex="(http://www.cnnvd.org.cn/vulnerability/show/cv_id/\\w+)";
	private static Connection connection=JDBCConnection.getJDBCConnection().getConnection();
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.processor.PageProcessor#process(us.codecraft.webmagic.Page)
	 */
	public void process(Page page) {
		// TODO Auto-generated method stub
		page.addTargetRequests(page.getHtml().links().regex(regex).all());
		if(page.getUrl().toString().matches(regex)){
			boolean insert=set.add(page.getUrl().toString());				
			//连续重复次数
			if(insert){
				flag=new AtomicInteger(0);
				String loophole_name=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[1]/td[2]/html()").toString();
				String cnnvd_no=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[2]/td[2]/html()").toString();
				String publish_time_string=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[3]/td[2]/a/text()").toString();
				String update_time_string=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[4]/td[2]/a/text()").toString();
				String harm_level=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[5]/td[2]/img/@src").toString();
				String harm_type=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[7]/td[2]/a/text()").toString();
				String cve_no=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[8]/td[2]/a/text()").toString();
				String source=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table/tbody/tr[9]/td[2]/text()").toString();
				String description=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[2]/td/div/p[1]/text()").toString()+page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[2]/td/div/p[2]/text()").toString();
				String introduction=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[3]/td/div/p/text()").toString()+page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[3]/td/div/p/a/text()").toString();
				String refer_websit=page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[4]/td/div[1]/table/tbody/tr/td/p/text()").toString()+page.getHtml().xpath("/html/body/div/div[2]/div[1]/table/tbody/tr/td/table/tbody/tr[4]/td/div[1]/table/tbody/tr/td/p/a/text()").toString();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				Date publish_time=null;
				try {
					publish_time=format.parse(publish_time_string);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date update_time=null;
				try {
					update_time=format.parse(update_time_string);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PreparedStatement ps=null;
				try {
					//保存
					ps = (PreparedStatement) connection
							.prepareStatement("insert into loophole_cnvd(loophole_name,cnnvd_no,publish_time,update_time,harm_level,harm_type,source,cve_no,description,introduction,refer_website) values(?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, loophole_name);
					ps.setString(2, cnnvd_no);
					ps.setDate(3, new java.sql.Date(publish_time.getTime()));
					ps.setDate(4, new java.sql.Date(update_time.getTime()));
					ps.setString(5, harm_level);
					ps.setString(6, harm_type);
					ps.setString(7, source);
					ps.setString(8, cve_no);
					ps.setString(9, description);
					ps.setString(10, introduction);
					ps.setString(11, refer_websit);
					ps.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
                
			}else {
				flag.incrementAndGet();
			}

		}
	}
	public static void spideAll(){
		set=Collections.synchronizedSet(new HashSet<String>(10000));
		int pageIndex=1;
		while(true){
			Spider.create(new CnnvdService()).thread(10).addUrl("http://www.cnnvd.org.cn/vulnerability/index/p/"+pageIndex+"/").run();;
			if(pageIndex>4000&flag.intValue()>60){
				break;
			}
			pageIndex++;
		}
	}
	public static void main(String[] args) throws SQLException {
//		spideAll();
		connection.close();
	}

	/**
	 * 生成xls文件，返回其路径
	 * @param t1
	 * @param t2
	 * @return
	 * @throws ParseException
	 * @throws SQLException
	 */
	public static String download(String t1, String t2) throws ParseException, SQLException {
		// TODO Auto-generated method stub

		String condition="1=1";
		//获取t2的下一天
		if(t1!=null&&t2!=null){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			Date date=format.parse(t2);
			long nextDateTime=date.getTime()+1000*3600*24;
			Date nextDate=new Date(nextDateTime);
			t2=format.format(nextDate);
			condition=" publish_time>="+t1+" and publish_time<="+t2;
		}
		PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("select * from loophole_cnvd where "+condition);
		ResultSet set=preparedStatement.executeQuery();
		List<LoopholeCnvd> loopholeCnvds=new ArrayList<LoopholeCnvd>();
		while(set.next()){
			LoopholeCnvd cnvd=new LoopholeCnvd();
			cnvd.setId(set.getLong(1));
			cnvd.setLoopholeName(set.getString(2));
			cnvd.setCnnvdNo(set.getString(3));
			cnvd.setPublishTime(set.getTimestamp(4));
			cnvd.setUpdateTime(set.getTimestamp(5));
			cnvd.setHarmLevel(set.getString(6));
			cnvd.setHarmType(set.getString(7));
			cnvd.setSource(set.getString(8));
			cnvd.setDescription(set.getString(9));
			cnvd.setIntroduction(set.getString(10));
			cnvd.setReferWebSite(set.getString(11));
			loopholeCnvds.add(cnvd);
		}
		System.out.println(loopholeCnvds.size());
		//生成excel文件在ExcelFile中
		String fileName="cnvd_"+System.currentTimeMillis()+".xls";
		String pathString=System.getProperty("rootPath")+"\\ExcelFile\\"+fileName;
		ExportExcel<LoopholeCnvd> excel=new ExportExcel<LoopholeCnvd>();
		File file=new File(pathString);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ExportSetInfo info=new ExportSetInfo();
		List<String[]> list=new ArrayList<String[]>();
		info.setTitles(new String[]{"漏洞列表"}) ;
		list.add(new String[]{"id","loopholeName","cnnvdNo","publishTime","updateTime","harmLevel","harmType","source","description","introduction","referWebSite"});
		info.setFieldNames(list);
		List<String[]> headNames=new ArrayList<String[]>();
		headNames.add(new String[]{"id","漏洞名称","cnnvd_no","发布时间","更新时间","危害等级","危害类型","来源","描述","简介","参考地址"});
		info.setHeadNames(headNames);
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(file);
			info.setOut(fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkedHashMap<String, List> hashMap=new LinkedHashMap<String, List>();
		hashMap.put("cnnvd漏洞",loopholeCnvds);
		info.setObjsMap(hashMap);
		try {
			new ExportExcelUtil().export2Excel(info);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileName;
	}
}