package com.javaConnectionMysqlDemo;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMysql {

	public static void main(String[] args, PreparedStatement PreparedStatement) throws UnsupportedEncodingException {
		//参照文章:http://www.cnblogs.com/hongten/archive/2011/03/29/1998311.html
		
		
		// TODO Auto-generated method stub
		/**
		 * DBC连接数据库   
			•创建一个以JDBC连接数据库的程序，包含7个步骤：   
		 */
		/*
		 *1.  加载JDBC驱动程序：
		 *在连接数据库之前，需要先加载想要连接的数据库驱动到JVM(java虚拟机)
		 *这通过java.lang.Class类的静态方法forName(String className)实现
		 *例如:
		 *
		 */
		//加载MySQl的驱动类
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			System.out.println("找不到Mysql的驱动程序！");
			e.printStackTrace();
		//成功加载后会将Driver类的实例注册到DriverManager类中
		}
		//2. 提供觉得不错的连接url
		/*
		 * 连接数据库URL提供了连接数据库时的协议，子协议，数据源标识。
		 * 书写形式: 协议：子协议：数据源
		 * 
		 * 协议：在JDBC中总是以JDBC开始
		 * 子协议：是桥连接的驱动 程序或或是数据库管理系统名称
		 * 数据源标识：标记找到数据库来源的地址与连接端口。
		 * 例如(Mysql的连接URL如下)
		 */
		String url = "jdbc:mysql:127.0.0.1:3306/imooc";
		//useUnicode=true：表示使用Unicode字符集。如果characterEncoding设置为   
	    //gb2312或GBK，本参数必须设置为true 。characterEncoding=gbk：字符编码方式。
		
		//3. 创建数据库的连接:
		/*
		 * 要连接数据库，要想java.sql.DriverManager请求并获得Connection对象。该对象
		 * 就代表一个数据库的连接。
		 * 使用DriverManager的getConnectin(String url , String username ,    
		    String password )方法传入指定的欲连接的数据库的路径、数据库的用户名和   
		       密码来获得。   
		       例如：   
		 */
		//连接MYSQL数据库，输入用户名和密码
		String username = "root";
		String passwd = "910214";
		try{
			Connection conn = DriverManager.getConnection(url, username, passwd);
			if(!conn.isClosed())
				System.out.println("数据库连接成功！");
			/*
			 * 4、创建一个Statement   
			    •要执行SQL语句，必须获得java.sql.Statement实例，Statement实例分为以下3  
			     种类型：   
			      1、执行静态SQL语句。通常通过Statement实例实现。   
			      2、执行动态SQL语句。通常通过PreparedStatement实例实现。   
			      3、执行数据库存储过程。通常通过CallableStatement实例实现。   
			    具体的实现方式：   
			 */
			Statement statement = conn.createStatement();
			//创建查询字符串
			String sql = "SELECT * FROM people";
			//执行动态SQL语句。通常通过PreparedStatement实例实现。
			PreparedStatement = conn.prepareStatement(sql);
			//执行数据库存储过程。通常通过CallableStatement实例实现。   
			//	CallableStatement cstmt = conn.prepareCall("{CALL demoSp(? , ?)}");
			
			/*
			 * 5、执行SQL语句   
			    Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate   
			   和execute   
			    1、ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   
			        ，返回一个结果集（ResultSet）对象。   
			     2、int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或   
			        DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等   
			     3、execute(sqlString):用于执行返回多个结果集、多个更新计数或二者组合的   
			        语句。   
			   具体实现的代码：   
			 */
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("---------------------");
			System.out.println("------以下是查询结果------");
			System.out.println("姓名"+"\t\t"+"年龄");
			System.out.println("----------------------");
//			int rows = stmt.executeUpdate("INSERT INTO ...") ;   
//		    boolean flag = stmt.execute(String sql) ; 
			
			/*
			 * 6、处理结果   
			    两种情况：   
			     1、执行更新返回的是本次操作影响到的记录数。   
			     2、执行查询返回的结果是一个ResultSet对象。   
			    • ResultSet包含符合SQL语句中条件的所有行，并且它通过一套get方法提供了对这些   
			      行中数据的访问。   
			    • 使用结果集（ResultSet）对象的访问方法获取数据：   
			 */
			String name = null;
			int age = 0;
			while(rs.next()){
				//选择username这列数据
				name = rs.getString("p_name");
				// 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
	             // 然后使用GB2312字符集解码指定的字节数组
				name = new String(name.getBytes("ISO-8859-1"),"GB2312");
				// 输出结果
               System.out.println(rs.getString("p_name") + "\t" + name);
			
               //获取年龄
               age = rs.getInt(age);
               System.out.println(rs.getInt(age));
			}
			
			/*
			 * 7、关闭JDBC对象    
			     操作完成以后要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声   
			     明顺序相反：   
			     1、关闭记录集   
			     2、关闭声明   
			     3、关闭连接对象   
			 */
				if(rs !=null){
					try{
						rs.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
				if(statement != null){
					try{
						statement.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
		}catch(SQLException se){
			System.out.println("数据库连接失败!");
			se.printStackTrace();
		}
	}

}
