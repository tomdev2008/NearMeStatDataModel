package com.nearme.statistics.dao.hiveapp.coloros.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.hiveapp.coloros.ColorosDao;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.model.coloros.COSAvgstartcntrankEntity;
import com.nearme.statistics.model.coloros.COSInstallrankEntity;
import com.nearme.statistics.model.coloros.COSStartrankEntity;

public class ColorosDaoImpl implements ColorosDao {
	private SqlSessionFactory hiveSessionFactory;
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getHiveSessionFactory() {
		return hiveSessionFactory;
	}

	public void setHiveSessionFactory(SqlSessionFactory hiveSessionFactory) {
		this.hiveSessionFactory = hiveSessionFactory;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<COSStartrankEntity> hiveQueryStartrank(ColorosDTO dto) {
		final String sql = getHqlStartrank(dto);

		List<COSStartrankEntity> list = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<COSStartrankEntity>();
				}

				COSStartrankEntity entity = new COSStartrankEntity();

				String statDatestr = dto.getStartDateStr();
				String statEndDatestr = dto.getEndDateStr();
				String position = rs.getString(1);
				String appname = rs.getString(2);
				long startimei = rs.getLong(3);
				long startcnt = rs.getLong(4);
				long installcnt = rs.getLong(5);

				entity.setStatDatestr(statDatestr);
				entity.setStatEndDatestr(statEndDatestr);
				entity.setPosition(position);
				entity.setAppname(appname);
				entity.setStartimei(startimei);
				entity.setStartcnt(startcnt);
				entity.setInstallcnt(installcnt);

				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}

		return list;
	}

	public String getHqlStartrank(ColorosDTO dto) {
		String startDate = dto.getStartDateStr();
		String endDate = dto.getEndDateStr();
		String model = dto.getModel();
		int systemID = dto.getSystemID();

		// 拼接Hive查询HQL
		StringBuilder sb = new StringBuilder(256);
		// 机型全部
		if (systemID == 265671){//内销
			if (null == model || "".equals(model) || "all".equals(model)) {
				sb.append(" select 0 position, t.appname app_name, t.imei_cnt launch_imei, t.launch_count launch_cnt, t1.install_count install_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(distinct imei)  imei_cnt, count(1) launch_count ");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");
				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by imei_cnt desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) install_count  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=0  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by launch_imei desc limit 1000 ");
			} else {
				sb.append(" select 0 position, t.appname app_name, t.imei_cnt launch_imei, t.launch_count launch_cnt, t1.install_count install_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(distinct imei)  imei_cnt, count(1) launch_count ");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");
				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by imei_cnt desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) install_count  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=0  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by launch_imei desc limit 1000 ");
			}
		}else if(systemID == 265672){//外销
			if (null == model || "".equals(model) || "all".equals(model)) {
				sb.append(" select 0 position, t.appname app_name, t.imei_cnt launch_imei, t.launch_count launch_cnt, t1.install_count install_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(distinct imei)  imei_cnt, count(1) launch_count ");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");
				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by imei_cnt desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) install_count  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=0  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by launch_imei desc limit 1000 ");
			} else {
				sb.append(" select 0 position, t.appname app_name, t.imei_cnt launch_imei, t.launch_count launch_cnt, t1.install_count install_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(distinct imei)  imei_cnt, count(1) launch_count ");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");
				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by imei_cnt desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) install_count  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=0  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by launch_imei desc limit 1000 ");
			}
		}else{
			//impossible
		}

		return sb.toString();
	}

	public int[] insertStartrank(List<COSStartrankEntity> list, String jobID) {
		SqlSession sqlSession = null;
		if (list != null) {
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (int i = 0; i < list.size(); i++) {
					COSStartrankEntity entity = list.get(i);
					String statDatestr = entity.getStatDatestr();
					String statEndDatestr = entity.getStatEndDatestr();
					String position = String.valueOf(i + 1);
					String appname = entity.getAppname();
					long startimei = entity.getStartimei();
					long startcnt = entity.getStartcnt();
					long installcnt = entity.getInstallcnt();

					appname = appname.replace("'", "’");//避免对sql有影响

					// oracle建表，字段自己定
					String sql_insert = "insert into dm_hive_cos_start_rank"
							+ "(job_id, statdate, statenddate, position, app_name, launch_imei, launch_cnt, install_cnt) "
							+ "values ('" + jobID + "','" + statDatestr + "','" + statEndDatestr + "','"
							+ position + "','" + appname + "','" + startimei
							+ "','" + startcnt + "','" + installcnt + "')";

					stmt.addBatch(sql_insert);

					if (i%500 == 0) {
						stmt.executeBatch();
					}
				}

				stmt.executeBatch();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (null != sqlSession) {
					sqlSession.close();
				}
			}
		} else {
			return null;
		}
	}

	public List<COSStartrankEntity> getStartrank(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "getStartrank", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSAvgstartcntrankEntity> hiveQueryAvgstartcntrank(
			ColorosDTO dto) {
		final String sql = getHqlAvgstartcntrank(dto);

		List<COSAvgstartcntrankEntity> list = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<COSAvgstartcntrankEntity>();
				}

				COSAvgstartcntrankEntity entity = new COSAvgstartcntrankEntity();

				String statDatestr = dto.getStartDateStr();
				String statEndDatestr = dto.getEndDateStr();
				String position = rs.getString(1);
				String appname = rs.getString(2);
				long startimei = rs.getLong(3);
				long startcnt = rs.getLong(4);
				double avgstartcnt = rs.getDouble(5);


				entity.setStatDatestr(statDatestr);
				entity.setStatEndDatestr(statEndDatestr);
				entity.setPosition(position);
				entity.setAppname(appname);
				entity.setStartimei(startimei);
				entity.setStartcnt(startcnt);
				entity.setAvgstartcnt(avgstartcnt);

				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}

		return list;
	}

	public String getHqlAvgstartcntrank(ColorosDTO dto) {
		String startDate = dto.getStartDateStr();
		String endDate = dto.getEndDateStr();
		String model = dto.getModel();
		int systemID = dto.getSystemID();
		// 拼接Hive查询HQL
		StringBuilder sb = new StringBuilder(256);

		if (systemID == 265671){//内销
			if (null == model || "".equals(model) || "all".equals(model)) {
				sb.append(" select t.position, t.app_name app_name, t.launch_imei launch_imei, t.launch_cnt launch_cnt, t.launch_rate launch_rate ");
				sb.append(" from ( ");
				sb.append("select 0 position, appname app_name, count(distinct imei)  launch_imei, count(1) launch_cnt, count(1) / count(distinct imei) launch_rate");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");

				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by launch_rate desc ");
				sb.append(" ) t ");
				sb.append(" where launch_imei > 10 ");
				sb.append(" limit 1000 ");
			} else {
				sb.append(" select t.position, t.app_name app_name, t.launch_imei launch_imei, t.launch_cnt launch_cnt, t.launch_rate launch_rate ");
				sb.append(" from ( ");
				sb.append(" select 0 position, appname app_name, count(distinct imei) launch_imei, count(1) launch_cnt, count(1) / count(distinct imei) launch_rate");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");
				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and  model = '").append(model);
				sb.append("' and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by launch_rate desc ");
				sb.append(" ) t ");
				sb.append(" where launch_imei > 10 ");
				sb.append(" limit 1000 ");
			}
		}else if(systemID == 265672){//外销
			if (null == model || "".equals(model) || "all".equals(model)) {
				sb.append(" select t.position, t.app_name app_name, t.launch_imei launch_imei, t.launch_cnt launch_cnt, t.launch_rate launch_rate ");
				sb.append(" from ( ");
				sb.append("select 0 position, appname app_name, count(distinct imei)  launch_imei, count(1) launch_cnt, count(1) / count(distinct imei) launch_rate");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");

				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by launch_rate desc ");
				sb.append(" ) t ");
				sb.append(" where launch_imei > 10 ");
				sb.append(" limit 1000 ");
			} else {
				sb.append(" select t.position, t.app_name app_name, t.launch_imei launch_imei, t.launch_cnt launch_cnt, t.launch_rate launch_rate ");
				sb.append(" from ( ");
				sb.append(" select 0 position, appname app_name, count(distinct imei) launch_imei, count(1) launch_cnt, count(1) / count(distinct imei) launch_rate");
				sb.append(" from stat_rom_ods.ds_apps_launched_stat ");
				sb.append(" where dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and  model = '").append(model);
				sb.append("' and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by launch_rate desc ");
				sb.append(" ) t ");
				sb.append(" where launch_imei > 10 ");
				sb.append(" limit 1000 ");
			}
		}else{
			//impossible
		}

		return sb.toString();
	}

	public int[] insertAvgstartcntrank(List<COSAvgstartcntrankEntity> list,
			String jobID) {
		SqlSession sqlSession = null;
		if (list != null) {
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (int i = 0; i < list.size(); i++) {
					COSAvgstartcntrankEntity entity = list.get(i);
					String statDatestr = entity.getStatDatestr();
					String statEndDatestr = entity.getStatEndDatestr();
					String position = String.valueOf(i + 1);
					String appname = entity.getAppname();
					long startimei = entity.getStartimei();
					long startcnt = entity.getStartcnt();
					double avgstartcnt = entity.getAvgstartcnt();

					appname = appname.replace("'", "’");//避免对sql有影响

					// oracle建表，字段自己定
					String sql_insert = "insert into dm_hive_cos_avgstart_rank"
							+ "(job_id, statdate, statenddate, position, app_name, launch_imei, launch_cnt, launch_rate) "
							+ "values ('" + jobID + "','" + statDatestr + "','" + statEndDatestr + "','"
							+ position + "','" + appname + "','" + startimei
							+ "','" + startcnt + "','" + avgstartcnt + "')";

					stmt.addBatch(sql_insert);
				}

				return stmt.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (null != sqlSession) {
					sqlSession.close();
				}
			}
		} else {
			return null;
		}
	}

	public List<COSAvgstartcntrankEntity> getAvgstartcntrank(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "getAvgstartcntrank", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSInstallrankEntity> hiveQueryInstallrank(ColorosDTO dto) {
		final String sql = getHqlInstallrank(dto);

		List<COSInstallrankEntity> list = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<COSInstallrankEntity>();
				}

				COSInstallrankEntity entity = new COSInstallrankEntity();

				String statDatestr = dto.getStartDateStr();
				String statEndDatestr = dto.getEndDateStr();
				String position = rs.getString(1);
				String appname = rs.getString(2);
				long installcnt = rs.getLong(3);
				long upgradecnt = rs.getLong(4);

				entity.setStatDatestr(statDatestr);
				entity.setStatEndDatestr(statEndDatestr);
				entity.setPosition(position);
				entity.setAppname(appname);
				entity.setInstallcnt(installcnt);
				entity.setUpgradecnt(upgradecnt);

				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}

		return list;
	}

	public String getHqlInstallrank(ColorosDTO dto) {
		String startDate = dto.getStartDateStr();
		String endDate = dto.getEndDateStr();
		String model = dto.getModel();
		int systemID = dto.getSystemID();
		// 拼接Hive查询HQL
		StringBuilder sb = new StringBuilder(256);

		if (systemID == 265671){//内销
			if (null == model || "".equals(model) || "all".equals(model)) {
				sb.append(" select 0 position, t.appname app_name, t.ins_times install_cnt, t1.upg_times upgrade_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(1)  ins_times ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat ");
				sb.append(" where actiontyp=0 ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by ins_times desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) upg_times  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=1  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by install_cnt desc limit 1000 ");
			} else {
				sb.append(" select 0 position, t.appname app_name, t.ins_times install_cnt, t1.upg_times upgrade_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(1)  ins_times ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat ");
				sb.append(" where actiontyp=0 ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by ins_times desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) upg_times  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=1  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and not(osversion like '%i%') and not(romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by install_cnt desc limit 1000 ");
			}
		}else if(systemID == 265672){//外销
			if (null == model || "".equals(model) || "all".equals(model)) {
				sb.append(" select 0 position, t.appname app_name, t.ins_times install_cnt, t1.upg_times upgrade_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(1)  ins_times ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat ");
				sb.append(" where actiontyp=0 ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by ins_times desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) upg_times  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=1  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model != 'R821T' ");
				sb.append(" and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by install_cnt desc limit 1000 ");
			} else {
				sb.append(" select 0 position, t.appname app_name, t.ins_times install_cnt, t1.upg_times upgrade_cnt ");
				sb.append(" from ( ");
				sb.append(" select appname, count(1)  ins_times ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat ");
				sb.append(" where actiontyp=0 ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" order by ins_times desc ");
				sb.append(" ) t ");
				sb.append(" left outer join  ");
				sb.append(" ( ");
				sb.append(" select appname , count(1) upg_times  ");
				sb.append(" from stat_rom_ods.ds_apps_installed_stat  ");
				sb.append(" where actiontyp=1  ");
				sb.append(" and dayno <= ").append(endDate);
				sb.append(" and dayno >= ").append(startDate);
				sb.append(" and model = '").append(model);
				sb.append("' and (osversion like '%i%' or romversion like '%EROM%') ");
				sb.append(" group by appname");
				sb.append(" ) t1 ");
				sb.append(" on t.appname=t1.appname order by install_cnt desc limit 1000 ");
			}
		}else{
			//impossible
		}

		return sb.toString();
	}

	public int[] insertInstallrank(List<COSInstallrankEntity> list, String jobID) {
		SqlSession sqlSession = null;
		if (list != null) {
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (int i = 0; i < list.size(); i++) {
					COSInstallrankEntity entity = list.get(i);
					String statDatestr = entity.getStatDatestr();
					String statEndDatestr = entity.getStatEndDatestr();
					String position = String.valueOf(i + 1);
					String appname = entity.getAppname();
					long installcnt = entity.getInstallcnt();
					long upgradecnt = entity.getUpgradecnt();

					appname = appname.replace("'", "’");//避免对sql有影响

					// oracle建表，字段自己定
					String sql_insert = "insert into dm_hive_cos_install_rank"
							+ "(job_id, statdate, statenddate,  position, app_name, install_cnt, upgrade_cnt) "
							+ "values ('" + jobID + "','" + statDatestr + "','" + statEndDatestr + "','"
							+ position + "','" + appname + "','" + installcnt
							+ "','" + upgradecnt + "')";

					stmt.addBatch(sql_insert);

					if (i%500 == 0) {
						stmt.executeBatch();
					}
				}

			    stmt.executeBatch();
			    return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (null != sqlSession) {
					sqlSession.close();
				}
			}
		} else {
			return null;
		}
	}

	public List<COSInstallrankEntity> getInstallrank(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "getInstallrank", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public void deleteAvgstartcntrank(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "deleteAvgstartcntrank", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void deleteInstallrank(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "deleteInstallrank", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void deleteStartrank(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "deleteStartrank", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 启动排行周数据
	 */
	public List<COSStartrankEntity> getStartrankweekList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "getStartrankweekList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 平均启动排行周数据
	 */
	public List<COSAvgstartcntrankEntity> getAvgstartrankweekList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "getAvgstartrankweekList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 安装排行周数据
	 */
	public List<COSInstallrankEntity> getInstallrankweekList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.coloros.ColorosDao."
							+ "getInstallrankweekList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
