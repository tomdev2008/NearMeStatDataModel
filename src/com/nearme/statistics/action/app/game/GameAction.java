package com.nearme.statistics.action.app.game;

import java.util.List;

import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.GameinfoEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 游戏名2(游戏堂提供)
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class GameAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm formgamename;
	private List<GameinfoEntity> gameinfoList;
	private GameService service;
	
	/**
	 * 查询游戏名2
	 * 
	 * @return
	 */
	public String queryGame() {
		String gamename = formgamename.getGamename();

		if (gamename == null || gamename.equals("")) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setGamename(gamename);
		gameinfoList = service.getGameList(dto);
		
		LogUtil.log(dto, "游戏名2");
		
		return Action.SUCCESS;
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
