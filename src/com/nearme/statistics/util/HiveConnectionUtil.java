package com.nearme.statistics.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveConnectionUtil {
    private static final String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    
    /**
     * 开发地址
     */
    private static final String url = "jdbc:hive://121.52.231.200/:10000/client_start_ods";
    
    /**
     * 正式地址
     */
//    private static final String url = "jdbc:hive://192.168.10.14/:10000/client_start_ods";
   
    /**
     * 获取连接
     */
    public static Connection getConnection(){
    	try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        
		Connection con;
		try {
			con = DriverManager.getConnection(url, "", "");
		} catch (SQLException e) {
			return null;
		}
		return con;
    }
    
    /**
     * 执行查询
     * @param con
     * @param sql
     * @return
     * @throws Exception
     */
    public static ResultSet executeQuery(Connection con,String sql) throws Exception{
		if (null != con) {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			return res;
		} else {
			return null;
		}
    }
    
    /**
     * 关闭连接
     * @param con
     */
    public static void close(Connection con){
    	if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
}