package com.nearme.statistics.action.app.game;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.GameinfoEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.HttpUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 游戏名
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class GamenameAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm formgamename;
	private List<GameinfoEntity> gameinfoList;
	private GameService service;

	// 游戏堂提供的查询接口
	private final String GC_GAMENAME_URL = "http://m.gc.nearme.com.cn/search_game_for_statistic";

	/**
	 * 查询游戏名
	 * 
	 * @return
	 */
	public String queryGamename() {
		String gamename = formgamename.getGamename();

		if (gamename == null || gamename.equals("")) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setGamename(gamename);
		// 内部从商店查询结果(gameId + 1亿)
		gameinfoList = service.getGameinfoList(dto);

		// 游戏堂提供接口查询结果
		List<GameinfoEntity> gcquerylist = queryGameinfoFromHttpRequest(gamename);

		// 合并查询结果
		if (null != gcquerylist) {
			if (null != gameinfoList) {
				for (GameinfoEntity entity : gcquerylist) {
					gameinfoList.add(entity);
				}
			} else {
				gameinfoList = gcquerylist;
			}
		}

		LogUtil.log(dto, "游戏名");

		return Action.SUCCESS;
	}

	/**
	 * 根据游戏名查询游戏 - 游戏堂提供的接口
	 * 
	 * @param gamename
	 * @return
	 */
	private List<GameinfoEntity> queryGameinfoFromHttpRequest(String gamename) {
		List<GameinfoEntity> result = new ArrayList<GameinfoEntity>();

		String bodys = "gameName=" + gamename;

		try {
			byte[] respdata = HttpUtil.getResponseData(GC_GAMENAME_URL, null,
					bodys.getBytes("UTF-8"));
			String jsonStr = new String(respdata, "UTF-8");

			JsonElement element = new JsonParser().parse(jsonStr);
			JsonArray array = element.getAsJsonArray();

			for (JsonElement elem : array) {
				JsonObject jsonobj = elem.getAsJsonObject();
				int productID = jsonobj.get("gameId").getAsInt();
				String productName = jsonobj.get("gameName").getAsString();
				String fenlei = jsonobj.get("defaultTypeName").getAsString();// 所属分类
				String productVersion = jsonobj.get("gameVerName")
						.getAsString();// 产品版本
				String uptime = jsonobj.get("createTime").getAsString();// 上架时间

				GameinfoEntity entity = new GameinfoEntity();
				entity.setProductID(productID);
				entity.setProductName(productName);
				entity.setFenlei(fenlei);
				entity.setProductVersion(productVersion);
				entity.setUptime(uptime);

				result.add(entity);
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	public GameForm getFormgamename() {
		return formgamename;
	}

	public void setFormgamename(GameForm formgamename) {
		this.formgamename = formgamename;
	}

	public List<GameinfoEntity> getGameinfoList() {
		return gameinfoList;
	}

	public void setGameinfoList(List<GameinfoEntity> gameinfoList) {
		this.gameinfoList = gameinfoList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
