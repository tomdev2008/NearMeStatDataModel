package com.nearme.statistics.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Http请求工具类
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-16
 */
public class HttpUtil {
	private static final String GC_GAMENAME_URL = "http://m.gc.nearme.com.cn/search_game_for_statistic";

	/**
	 * POST请求
	 * 
	 * @param urlstr
	 *            请求的路径
	 * @param headers
	 *            请求的头信息
	 * @param bodys
	 *            请求的body
	 * @return
	 * @throws Exception
	 */
	public static byte[] getResponseData(String urlstr,
			HashMap<String, String> headers,byte[] bodys) throws Exception {
		URL url = new URL(urlstr);
		HttpURLConnection conn = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 设置请求头
			if (null != headers && headers.size() > 0) {
				for (String key : headers.keySet()) {
					conn.setRequestProperty(key, headers.get(key));
				}
			}
			
			// 设置请求body
			if(null != bodys){
				conn.setDoOutput(true);
				OutputStream os = conn.getOutputStream();
				os.write(bodys,0,bodys.length);
				os.flush();
			}

			// 接收数据
			is = conn.getInputStream();
			baos = new ByteArrayOutputStream();
			int bufferLen = 4096;
			byte[] data = new byte[bufferLen];
			int readLen;
			while ((readLen = is.read(data, 0, bufferLen)) != -1) {
				baos.write(data, 0, readLen);
			}

			return baos.toByteArray();
		} finally {
			// 关闭连接
			if (null != conn) {
				conn.disconnect();
			}
		}
	}

	public static void main(String[] args) {
		String bodys = "gameName=暗黑君王";
		try {
			byte[] respdata = getResponseData(GC_GAMENAME_URL, null,bodys.getBytes());
			String jsonStr = new String(respdata);
			System.out.println(jsonStr);

			JsonElement element = new JsonParser().parse(jsonStr);
			JsonArray array = element.getAsJsonArray();

			for (JsonElement e : array) {
				int gameid =  e.getAsJsonObject().get("gameId").getAsInt();
				String gameName = e.getAsJsonObject().get("gameName").getAsString();
				System.out.println(gameid + " : " + gameName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
