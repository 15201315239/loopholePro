package com.me.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
	private JDBCConnection() {
	};

	private static JDBCConnection JDBCConnection = null;

	public static JDBCConnection getJDBCConnection() {
		if (JDBCConnection == null) {
			JDBCConnection = new JDBCConnection();
		}
		return JDBCConnection;
	}

	public Connection getConnection() {
		Connection conn = null;
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		String url = "jdbc:mysql://localhost:3306/spider?"
				+ "user=root&password=&useUnicode=true&characterEncoding=UTF8";
		// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
		// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 动态加载mysql驱动
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	

}