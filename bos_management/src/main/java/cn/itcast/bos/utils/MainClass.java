package cn.itcast.bos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {
	public static void main(String[] args) throws Exception {
		// 1.注册驱动器
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2.得到Connection
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.26.55:1521:ORCL","bos","bos");
		ResultSet rs = null;
		Statement stmt = null;
		try {
			// 3.创建Statement
			stmt = conn.createStatement();

			// 4.得到结果集
			rs = stmt.executeQuery("select * from t_area t");

			// 5.对结果集进行处理(例如遍历t_student表中的sname字段)
			while (rs.next()) {
				System.out.println(rs.getString("c_city"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 6.资源释放
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
