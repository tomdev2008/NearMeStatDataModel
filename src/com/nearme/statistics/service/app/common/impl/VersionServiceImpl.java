package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.VersionDao;
import com.nearme.statistics.dto.app.common.VersionDTO;
import com.nearme.statistics.model.commonsetting.VersionEntity;
import com.nearme.statistics.service.app.common.VersionService;

public class VersionServiceImpl implements VersionService {
	private VersionDao dao;

	public VersionDao getDao() {
		return dao;
	}

	public void setDao(VersionDao dao) {
		this.dao = dao;
	}

	public List<VersionEntity> getVersionList(VersionDTO dto) {
		List<VersionEntity> list = dao.getVersionList(dto);

		for (VersionEntity entity : list) {
			String versionName = entity.getVersionName();
			String appVersion = entity.getAppVersion();

			// 没有版本别名的指定版本名为版本号
			if (null == versionName || "".equals(versionName)) {
				entity.setVersionName(appVersion);
			}
		}

		return list;
	}

	public void update(VersionDTO dto) {
		dao.update(dto);
	}
}
