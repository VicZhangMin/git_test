package com.imooc.jdbc.demo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.imooc.jdbc.utils.JDBCUtils;

/**
 * 演示JDBC的注入的漏洞
 * @author jt
 *
 */
public class JDBCDemo4 {
	@Test
	/**
	 * 测试SQL注入漏洞的方法
	 */
	public void demo1(){
		boolean flag = JDBCDemo4.login2("aaa' or '1=1", "1fsdsdfsdf");
		if(flag == true){
			System.out.println("登录成功！");
		}else{
			System.out.println("登录失败！");
		}
	}
	
	/**
	 * 避免SQL注入漏洞的方法
	 */
	public static boolean login2(String username,String password){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "select * from user where username = ? and password = ?";
			// 预处理SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			// 执行SQL：
			rs = pstmt.executeQuery();
			// 判断结果街
			if(rs.next()){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
		return flag;
	}
	
	/**
	 * 产生SQL注入漏洞的方法
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username,String password){
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			conn = JDBCUtils.getConnection();
			// 创建执行SQL语句的对象:
			stmt = conn.createStatement();
			// 编写SQL:
			String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
			// 执行SQL:
			rs = stmt.executeQuery(sql);
			// 判断结果集中是否有数据。
			if(rs.next()){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, stmt, conn);
		}
		return flag;
	}
}
