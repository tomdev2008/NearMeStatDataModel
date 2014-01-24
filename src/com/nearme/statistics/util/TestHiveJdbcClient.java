package com.nearme.statistics.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class TestHiveJdbcClient {
    private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection("jdbc:hive://121.52.231.200/:10000/client_start_ods", "", "");
        Statement stmt = con.createStatement();
//        String tableName = "testHiveDriverTable";
//        stmt.executeQuery("drop table " + tableName);
//        ResultSet res = stmt.executeQuery("create table " + tableName + " (key int, value string)");
        // show tables
//        String usedb = "use client_start_ods";
//        stmt.execute(usedb);
        String sql = "select tp1.stype , tp1.start_imei , tp1.start_times , tp1.start_days , tp2.download_times , tp2.download_imei from ( select aa.stype , count(distinct aa.imei) start_imei, count(distinct aa.dayno) start_days , count(1) start_times from ( select ceil(datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),from_unixtime(unix_timestamp(t1.dayno,'yyyyMMdd'),'yyyy-MM-dd'))/10) stype, t.imei , t.dayno from ( select dayno,imei from client_start_ods.tjt_app_start where system_id = 2 and channel_id = '1' and dayno >= 20130402 and dayno <= 20130823 )t inner join ( select imei,dayno from client_start_ods.fxt_imei where dayno >='20130301' and dayno <= '20130401' and system_id =2 ) t1 on t.imei=t1.imei ) aa group by aa.stype ) tp1 inner join ( select ceil(datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),from_unixtime(unix_timestamp(t1.dayno,'yyyyMMdd'),'yyyy-MM-dd'))/10) stype , count(distinct t.imei) download_imei, count(1) download_times from ( select dayno,imei from client_start_ods.tjt_download_trace where system_id = 2 and channel_id = '1' and dayno >= 20130402 and dayno <= 20130823 ) t inner join ( select imei,dayno from client_start_ods.fxt_imei where dayno >='20130301' and dayno <= '20130401' and system_id =2 ) t1 on t.imei=t1.imei group by ceil(datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),from_unixtime(unix_timestamp(t1.dayno,'yyyyMMdd'),'yyyy-MM-dd'))/10)) tp2 on tp1.stype = tp2.stype order by tp1.stype";
//        String sql = "show tables";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
//            sql = "describe " + res.getString(1);
//            System.out.println("Running: " + sql);
//            ResultSet res1 = stmt.executeQuery(sql);
//            while (res1.next()) {
//                System.out.println(res1.getString(1) + "\t" + res1.getString(2));
//            }
        }
        // describe table


        // load data into table
        // NOTE: filepath has to be local to the hive server
        // NOTE: /tmp/a.txt is a ctrl-A separated file with two fields per line
//        String filepath = "/tmp/a.txt";
//        sql = "load data local inpath '" + filepath + "' into table " + tableName;
//        System.out.println("Running: " + sql);
//        res = stmt.executeQuery(sql);
//
//        // select * query
//        sql = "select * from " + tableName;
//        System.out.println("Running: " + sql);
//        res = stmt.executeQuery(sql);
//        while (res.next()) {
//            System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
//        }
//
//        // regular hive query
//        sql = "select count(1) from " + tableName;
//        System.out.println("Running: " + sql);
//        res = stmt.executeQuery(sql);
//        while (res.next()) {
//            System.out.println(res.getString(1));
//        }
    }
}