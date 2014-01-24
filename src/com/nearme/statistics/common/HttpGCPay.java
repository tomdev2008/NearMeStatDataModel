package com.nearme.statistics.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.model.game.GameFeeEntity;
import com.nearme.statistics.model.game.GameFeeUserEntity;
import com.nearme.statistics.model.game.JointgameEntity;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.HttpUtil;

public class HttpGCPay {
	public static final String GC_PAY_URL = "http://i2.gc.nearme.com.cn/";
	//public static final String GC_PAY_URL = "http://115.236.185.210:61616/";
	public static final String METHOD_GET_SIMPLE_STAT_DATA = "getSimpleStatData";
	public static final String METHOD_GET_WHALE_STAT_DATA = "getWhaleStatData";
	public static final String METHOD_GET_WHALE_USERS = "getWhaleUsers";
	
	public static final int MAX_SIZE = 20;//接口一次返回数据最大条数
	
	/**
	 * 游戏堂 一个游戏 的 每天付费人数、付费总额
	 * @param bodys
	 * @return
	 * @throws Exception
	 */
	public static List<JointgameEntity> getSimpleStatData(GameDTO dto) throws Exception{
		if(dto==null || !(dto.getModel()==null || "all".equals(dto.getModel()) ||"".equals(dto.getModel().trim()))){
			return null;
		}
		StringBuilder bodys = new StringBuilder(256);
		bodys.append("gameId=");
		bodys.append(dto.getProductid().replaceAll("'", "").substring(4));
		bodys.append("&startDate=");
		bodys.append(DateUtil.formatDate(dto.getStartDate(),"yyyy-MM-dd"));
		bodys.append("&endDate=");
		bodys.append(DateUtil.formatDate(dto.getEndDate(),"yyyy-MM-dd"));
		if(!(dto.getQudao()==null || "all".equals(dto.getQudao()) ||"".equals(dto.getQudao().trim()))){
			bodys.append("&channel=");
			bodys.append(dto.getQudao());
		}
		
		//发送请求
		byte[] respdata = HttpUtil.getResponseData(GC_PAY_URL + METHOD_GET_SIMPLE_STAT_DATA, null,bodys.toString().getBytes());
		String jsonStr = new String(respdata);
		
		JsonElement element = new JsonParser().parse(jsonStr);
		JsonObject jsonObject = element.getAsJsonObject();
		
		if(0==jsonObject.get("resultCode").getAsInt() || jsonObject.get("total").getAsInt()<=0){
			return null;
		}
		
		//有结果 -> 解析
		List<JointgameEntity> list = new ArrayList<JointgameEntity>();
		JointgameEntity entity = null;
		JsonElement tmpElement = null;
		for (JsonElement e : jsonObject.get("stats").getAsJsonArray()) {
			tmpElement = e.getAsJsonObject().get("statDate");
			Date statDate =  DateUtil.parseDate(tmpElement.isJsonNull() ? "" : tmpElement.getAsString(),"yyyy-MM-dd");
			int totalPayUser = e.getAsJsonObject().get("totalPayUser").getAsInt();
			float totalPayMoney = e.getAsJsonObject().get("totalPayMoney").getAsFloat();
			float arppu = e.getAsJsonObject().get("arppu").getAsFloat();
			
			entity = new JointgameEntity();
			entity.setStatDate(statDate);
			entity.setFeeuser(totalPayUser);
			entity.setFeetotal(totalPayMoney);
			entity.setArppu(arppu);
			list.add(entity);
		}
		
		return list;
	}
	
	/**
	 * 游戏堂 一个游戏 的 每天 新增鲸鱼、总鲸鱼数
	 * @param bodys
	 * @return
	 * @throws Exception
	 */
	public static List<GameFeeEntity> getWhaleStatData(GameDTO dto) throws Exception{
		if(dto==null || !(dto.getModel()==null || "all".equals(dto.getModel()) ||"".equals(dto.getModel().trim()))){
			return null;
		}
		StringBuilder bodys = new StringBuilder(256);
		bodys.append("gameId=");
		bodys.append(dto.getProductid().replaceAll("'", "").substring(4));
		bodys.append("&startDate=");
		bodys.append(DateUtil.formatDate(dto.getStartDate(),"yyyy-MM-dd"));
		bodys.append("&endDate=");
		bodys.append(DateUtil.formatDate(dto.getEndDate(),"yyyy-MM-dd"));
		if(!(dto.getQudao()==null || "all".equals(dto.getQudao()) ||"".equals(dto.getQudao().trim()))){
			bodys.append("&channel=");
			bodys.append(dto.getQudao());
		}
		
		//发送请求
		byte[] respdata = HttpUtil.getResponseData(GC_PAY_URL + METHOD_GET_WHALE_STAT_DATA, null,bodys.toString().getBytes());
		String jsonStr = new String(respdata);
		
		JsonElement element = new JsonParser().parse(jsonStr);
		JsonObject jsonObject = element.getAsJsonObject();
		
		if(0==jsonObject.get("resultCode").getAsInt() || jsonObject.get("total").getAsInt()<=0){
			return null;
		}
		
		//有结果 -> 解析
		List<GameFeeEntity> list = new ArrayList<GameFeeEntity>();
		GameFeeEntity entity = null;
		JsonElement tmpElement = null;
		for (JsonElement e : jsonObject.get("stats").getAsJsonArray()) {
			tmpElement = e.getAsJsonObject().get("statDate");
			String statDate =  tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			int newWhale = e.getAsJsonObject().get("newWhaleCount").getAsInt();
			int totalWhale = e.getAsJsonObject().get("totalWhaleCount").getAsInt();
			int whaleLost7 = e.getAsJsonObject().get("whaleAwayLogin7").getAsInt();
			int whalePayLost7 = e.getAsJsonObject().get("whaleAwayPay7").getAsInt();
			int whalePayLost30 = e.getAsJsonObject().get("whaleAwayPay30").getAsInt();
			
			entity = new GameFeeEntity();
			entity.setStatDate(statDate);
			entity.setNewWhale(newWhale);
			entity.setTotalWhale(totalWhale);
			entity.setWhaleLost7(whaleLost7);
			entity.setWhalePayLost7(whalePayLost7);
			entity.setWhalePayLost30(whalePayLost30);
			list.add(entity);
		}
		
		return list;
	}
	
	/**
	 * 游戏堂 一个游戏 的 鲸鱼用户
	 * @param bodys
	 * @return
	 * @throws Exception
	 */
	public static List<GameFeeUserEntity> getWhaleUsers(GameDTO dto) throws Exception{
		if(dto==null || !(dto.getModel()==null || "all".equals(dto.getModel()) ||"".equals(dto.getModel().trim()))){
			return null;
		}
		StringBuilder bodys = new StringBuilder(256);
		bodys.append("gameId=");
		bodys.append(dto.getProductid().replaceAll("'", "").substring(4));
		bodys.append("&date=");
		bodys.append(dto.getStatdate());
		bodys.append("&type=");
		bodys.append(dto.getType());
		bodys.append("&start=");
		bodys.append(dto.getStart());
		bodys.append("&size=");
		bodys.append(dto.getSize());
		if(!(dto.getQudao()==null || "all".equals(dto.getQudao()) ||"".equals(dto.getQudao().trim()))){
			bodys.append("&channel=");
			bodys.append(dto.getQudao());
		}
		
		//发送请求
		byte[] respdata = HttpUtil.getResponseData(GC_PAY_URL + METHOD_GET_WHALE_USERS, null,bodys.toString().getBytes());
		String jsonStr = new String(respdata);
		
		JsonElement element = new JsonParser().parse(jsonStr);
		JsonObject jsonObject = element.getAsJsonObject();
		
		if(0==jsonObject.get("resultCode").getAsInt() || jsonObject.get("total").getAsInt()<=0){
			return null;
		}
		
		//有结果 -> 解析
		List<GameFeeUserEntity> list = new ArrayList<GameFeeUserEntity>();
		GameFeeUserEntity entity = null;
		JsonElement tmpElement = null;
		
		for (JsonElement e : jsonObject.get("users").getAsJsonArray()) {
			int ssoid =  e.getAsJsonObject().get("ssoid").getAsInt();
			tmpElement = e.getAsJsonObject().get("userName");
			String userName = tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			tmpElement = e.getAsJsonObject().get("keke");
			String keke = tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			tmpElement = e.getAsJsonObject().get("channelId");
			String channelId = tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			tmpElement = e.getAsJsonObject().get("newWhaleDate");
			String newWhaleDate = tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			tmpElement = e.getAsJsonObject().get("lastLoginDate");
			String lastLoginDate = tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			tmpElement = e.getAsJsonObject().get("lastPayDate");
			String lastPayDate = tmpElement.isJsonNull() ? "" : tmpElement.getAsString();
			int payCount = e.getAsJsonObject().get("payCount").getAsInt();
			int paySum = e.getAsJsonObject().get("paySum").getAsInt();
			int kekeBalance = e.getAsJsonObject().get("kekeBalance").getAsInt();
			int rechargeCount = e.getAsJsonObject().get("rechargeCount").getAsInt();
			int rechargeSum = e.getAsJsonObject().get("rechargeSum").getAsInt();
			
			entity = new GameFeeUserEntity();
			entity.setSsoid(ssoid);
			entity.setUserName(userName);
			entity.setKeke(keke);
			entity.setChannelId(channelId);
			entity.setNewWhaleDate(newWhaleDate);
			entity.setLastLoginDate(lastLoginDate);
			entity.setLastPayDate(lastPayDate);
			entity.setPayCount(payCount);
			entity.setPaySum(paySum);
			entity.setKekeBalance(kekeBalance);
			entity.setRechargeCount(rechargeCount);
			entity.setRechargeSum(rechargeSum);
			list.add(entity);
		}
		
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameDTO dto = new GameDTO();
		dto.setProductid("10011080");
		dto.setStartDate(DateUtil.parseDate("2013-10-30", "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate("2013-11-15", "yyyy-MM-dd"));
		try {
			HttpGCPay.getSimpleStatData(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
