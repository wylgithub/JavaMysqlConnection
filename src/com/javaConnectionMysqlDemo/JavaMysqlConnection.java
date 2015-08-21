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
		//����������
		String driver = "com.mysql.jdbc.Driver";
		//URlָ��Ҫ���ʵ����ݿ�����
		String url = "jdbc:mysql://127.0.0.1:3306/testmysql";
		//mysql����ʱ���û���
		String user="root";
		//MySQL����ʱ������
		String passwd = "910214";
		
		try{
			//������������
			Class.forName(driver);
			
			//�������ݿ�
			Connection conn = DriverManager.getConnection(url,user,passwd);
			if(!conn.isClosed())
				System.out.println("�ɹ��������ݿ⣡");
			
			//statment����ִ��SQL���
			Statement statment = conn.createStatement();
			
			//Ҫִ�е�mysql���
			String sql = "SELECT * FROM userlogin";
			
			//��ѯ�����
			ResultSet rs = statment.executeQuery(sql);
			System.out.println("---------------------");
			System.out.println("------�����ǲ�ѯ���------");
			System.out.println("����"+"\t\t"+"����");
			System.out.println("----------------------");
		
			String name = null;
			while(rs.next()){
				//ѡ��username��������
				name = rs.getString("username");
				// ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
	             // Ȼ��ʹ��GB2312�ַ�������ָ�����ֽ�����
				name = new String(name.getBytes("ISO-8859-1"),"GB2312");
				// ������
                System.out.println(rs.getString("username") + "\t" + name);
			}
			//�ر�����
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
