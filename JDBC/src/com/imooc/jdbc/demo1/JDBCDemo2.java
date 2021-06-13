package com.imooc.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCDemo2 {
	@Test
	/**
	 * 查询一条记录
	 */
	public void demo5(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获得连接
			conn = DriverManager.getConnection("jdbc:mysql:///jdbctest", "root", "abc");
			// 创建执行SQL语句的对象
			stmt = conn.createStatement();
			// 编写SQL
			String sql = "select * from user where uid = 1";
			// 执行SQL
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println(rs.getInt("uid")+"   "+rs.getString("username")+"   "+rs.getString("password")+"   "+rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}			
		}
	}
	
	@Test
	/**
	 * 查询所有记录
	 */
	public void demo4(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			// 注册驱动:
			Class.forName("com.mysql.jdbc.Driver");
			// 获得连接:
			conn = DriverManager.getConnection("jdbc:mysql:///jdbctest", "root", "abc");
			// 创建执行SQL语句的对象:
			stmt = conn.createStatement();
			// 编写SQL:
			String sql = "select * from user";
			// 执行SQL:
			rs = stmt.executeQuery(sql);
			// 遍历结果集:
			while(rs.next()){
				System.out.println(rs.getInt("uid")+"   "+rs.getString("username")+"   "+rs.getString("password")+"    "+rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	@Test
	/**
	 * 删除操作
	 */
	public void demo3(){
		Connection conn = null;
		Statement stmt = null;
		try{
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获得连接
			conn = DriverManager.getConnection("jdbc:mysql:///jdbctest", "root", "abc");
			// 获得执行SQL语句的对象:
			stmt = conn.createStatement();
			// 编写SQL:
			String sql = "delete from user where uid = 4";
			// 执行SQL:
			int i = stmt.executeUpdate(sql);
			if(i > 0){
				System.out.println("删除成功!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	@Test
	/**
	 * 修改操作
	 */
	public void demo2(){
		Connection conn = null;
		Statement stmt = null;
		try{
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获得连接
			conn = DriverManager.getConnection("jdbc:mysql:///jdbctest", "root", "abc");
			// 创建执行SQL语句的对象:
			stmt = conn.createStatement();
			// 编写SQL:
			String sql = "update user set username = 'qqq',password='456' , name='赵六' where uid = 4";
			// 执行SQL:
			int i = stmt.executeUpdate(sql);
			if(i>0){
				System.out.println("修改成功!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}

	@Test
	/**
	 * 保存操作
	 */
	public void demo1(){
		Connection conn = null;
		Statement stmt = null;
		try{
			// 注册驱动:
			Class.forName("com.mysql.jdbc.Driver");
			// 获得连接:
			conn = DriverManager.getConnection("jdbc:mysql:///jdbctest", "root", "abc");
			// 获得执行SQL语句的对象:
			stmt = conn.createStatement();
			// 编写SQL：
			String sql = "insert into user values (null,'eee','123','张三')";
			// 执行SQL:
			int i = stmt.executeUpdate(sql);
			if(i > 0){
				System.out.println("保存成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源:
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
}
