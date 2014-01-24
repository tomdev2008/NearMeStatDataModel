package com.nearme.statistics.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHive {
	private static final String sql_showtables = "show tables";

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-datasource.xml", "spring-service.xml",
						"spring-dao.xml" });

		Object bean = context.getBean("hiveSessionFactory");

		try {
			if (bean != null && bean instanceof SqlSessionFactory) {
				SqlSessionFactory hiveSessionFactory = (SqlSessionFactory) bean;
				SqlSession sqlSession = hiveSessionFactory.openSession();
				Connection conn = sqlSession.getConnection();

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql_showtables);
				while (rs.next()) {
					System.out.println(rs.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
