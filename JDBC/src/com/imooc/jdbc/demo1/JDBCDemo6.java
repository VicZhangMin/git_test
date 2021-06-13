package com.imooc.jdbc.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.imooc.jdbc.utils.JDBCUtils;

/**
 * PreparedStatement的使用
 * @author jt
 *
 */
public class JDBCDemo6 {
	@Test
	/**
	 * 查询一条记录
	 */
	public void demo5(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL：
			String sql = "select * from user where uid = ?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setObject(1, 3);
			// 执行SQL:
			rs = pstmt.executeQuery();
			// 判断结果集:
			if(rs.next()){
				System.out.println(rs.getInt("uid")+"  "+rs.getString("username")+"  "+rs.getString("password")+"  "+rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
	
	@Test
	/**
	 * 查询所有数据
	 */
	public void demo4(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "select * from user";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			// 执行SQL:
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("uid")+"  "+rs.getString("username")+"  "+rs.getString("password")+"  "+rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
	
	@Test
	/**
	 * 删除数据
	 */
	public void demo3(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL：
			String sql = "delete from user where uid = ?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setInt(1, 6);
			// 执行SQL:
			int num = pstmt.executeUpdate();
			if(num > 0){
				System.out.println("删除成功！测试d");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	@Test
	/**
	 * 修改数据
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "update user set username = ?,password = ?,name = ? where uid = ?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setString(1, "www");
			pstmt.setString(2, "123456");
			pstmt.setString(3, "张六");
			pstmt.setInt(4, 6);
			// 执行SQL:
			int num = pstmt.executeUpdate();
			if(num > 0){
				System.out.println("修改成功！version4");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Test
	/**
	 * 保存数据
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "insert into user values (null,?,?,?)";
			// 预处理SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数的值:
			pstmt.setString(1, "qqq");
			pstmt.setString(2, "123");
			pstmt.setString(3, "张武");
			// 执行SQL:
			int num = pstmt.executeUpdate();
			if(num > 0){
				System.out.println("保存成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源
			JDBCUtils.release(pstmt, conn);
		}
	}
}
