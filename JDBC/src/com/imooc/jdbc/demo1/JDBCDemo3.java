package com.imooc.jdbc.demo1;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

import com.imooc.jdbc.utils.JDBCUtils;

public class JDBCDemo3 {

	@Test
	// 保存记录
	public void demo1(){
		Connection conn = null;
		Statement stmt  = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 创建执行SQL语句的对象
			stmt = conn.createStatement();
			// 编写SQL:
			String sql = "insert into user values (null,'ggg','123','小六')";
			// 执行SQL:
			int num = stmt.executeUpdate(sql);
			if(num > 0){
				System.out.println("保存成功!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 释放资源:
			JDBCUtils.release(stmt, conn);
		}
	}
}
