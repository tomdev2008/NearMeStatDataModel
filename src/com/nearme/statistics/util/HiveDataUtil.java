package com.nearme.statistics.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 同步数据到hive上
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class HiveDataUtil {
	/**
	 * hive上建表分隔符统一,跟hive建表定义的分割符保持一致
	 */
	public static final String CHARACTER_BETWEEN = "\t";
	public static final String CHARACTER_END = "\n";
	
	/**
	 * HDFS服务器
	 */
	private static final String PATH_HDFS_SERVER = "hdfs://192.168.10.14:7001";//正式环境
//	private static final String PATH_HDFS_SERVER = "hdfs://192.168.5.175:9000";//测试环境
	
	/**
	 * HDFS文件根路径
	 */
	private static final String PATH_FILE_BASE = "/tmp/nearme-stat/";

	/**
	 * 将拼写成hdfs格式的字符串写入hive上临时文件<br>
	 * 返回hive上要执行的覆盖数据的sql命令<br>
	 * 记得执行完覆盖命令后，调用deleteTmpHDFSfile
	 * 
	 * @param hdfsStr
	 * @param filename
	 * @param hivetable
	 * @throws Exception
	 */
	public static String writeHDFS(String hdfsStr, String filename,
			String hivetable) {
		String hivesql = null;
		FileSystem fs = null;
		FSDataOutputStream writer = null;
		try {
			Configuration config = new Configuration();
			config.set("fs.default.name", PATH_HDFS_SERVER);
			
			fs = FileSystem.get(config);
			Path path = new Path(PATH_FILE_BASE + filename);
			writer = fs.create(path);
			writer.write(hdfsStr.getBytes("UTF-8"));

			hivesql = "load data inpath '" + PATH_FILE_BASE + filename
					+ "' overwrite into table " + hivetable;
		} catch (Exception e) {
			e.printStackTrace();
			hivesql = null;
		} finally {
			try {
				if (null != writer) {
					writer.close();
				}
				if (null != fs) {
					fs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return hivesql;
	}

	/**
	 * 删除临时hdfs文件
	 * 
	 * @param filename
	 */
	public static void deleteTmpHDFSfile(String filename) {
		FileSystem fs = null;
		try {
			Configuration config = new Configuration();
			config.set("fs.default.name", PATH_HDFS_SERVER);
			fs = FileSystem.get(config);
			Path path = new Path(PATH_FILE_BASE + filename);
			fs.deleteOnExit(path);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fs) {
					fs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
