package com.imooc.jdbc.demo3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.imooc.jdbc.utils.JDBCUtils;
import com.imooc.jdbc.utils.JDBCUtils2;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接池的测试类
 * @author jt
 *
 */
public class DataSourceDemo1 {
	@Test
	/**
	 * 使用配置文件的方式
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			/*// 获得连接:
			ComboPooledDataSource dataSource = new ComboPooledDataSource();*/
			// 获得连接:
			// conn = dataSource.getConnection();
			conn = JDBCUtils2.getConnection();
			// 编写Sql:
			String sql = "select * from user";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			// 执行SQL:
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("uid")+"   "+rs.getString("username")+"   "+rs.getString("password")+"   "+rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils2.release(rs, pstmt, conn);
		}
	}

	@Test
	/**
	 * 手动设置连接池
	 */
	public void demo1(){

		// 获得连接:
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// 创建连接池:
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			// 设置连接池的参数:
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql:///jdbctest");
			dataSource.setUser("root");
			dataSource.setPassword("abc");
			dataSource.setMaxPoolSize(20);
			dataSource.setInitialPoolSize(3);
			
			// 获得连接:
			conn = dataSource.getConnection();
			// 编写Sql:
			String sql = "select * from user";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			// 执行SQL:
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("uid")+"   "+rs.getString("username")+"   "+rs.getString("password")+"   "+rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
}
