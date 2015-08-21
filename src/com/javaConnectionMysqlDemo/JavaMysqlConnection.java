package com.javaConnectionMysqlDemo;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JavaMysqlConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		//URl指向要访问的数据库名称
		String url = "jdbc:mysql://127.0.0.1:3306/testmysql";
		//mysql配置时的用户名
		String user="root";
		//MySQL配置时的密码
		String passwd = "910214";
		
		try{
			//加载驱动程序
			Class.forName(driver);
			
			//连接数据库
			Connection conn = DriverManager.getConnection(url,user,passwd);
			if(!conn.isClosed())
				System.out.println("成功连接数据库！");
			
			//statment用来执行SQL语句
			Statement statment = conn.createStatement();
			
			//要执行的mysql语句
			String sql = "SELECT * FROM userlogin";
			
			//查询结果集
			ResultSet rs = statment.executeQuery(sql);
			System.out.println("---------------------");
			System.out.println("------以下是查询结果------");
			System.out.println("姓名"+"\t\t"+"密码");
			System.out.println("----------------------");
		
			String name = null;
			while(rs.next()){
				//选择username这列数据
				name = rs.getString("username");
				// 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
	             // 然后使用GB2312字符集解码指定的字节数组
				name = new String(name.getBytes("ISO-8859-1"),"GB2312");
				// 输出结果
                System.out.println(rs.getString("username") + "\t" + name);
			}
			//关闭连接
			rs.close();
			conn.close();
		}
		catch(SQLException e) {
            e.printStackTrace();
           } 
		catch(Exception e) {
            e.printStackTrace();
           } 
	}
}
