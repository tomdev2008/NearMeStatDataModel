package com.nearme.statistics.dao.hiveapp.common.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.hiveapp.common.CommonDao;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.model.common.ActivedaysdistributeEntity;
import com.nearme.statistics.model.common.FSNaturemodleChildEntity;
import com.nearme.statistics.model.common.FSOperatepointEntity;
import com.nearme.statistics.model.common.FSResourcetypeChildEntity;
import com.nearme.statistics.model.common.FSTopresourceEntity;
import com.nearme.statistics.model.common.FSUpdatenumEntity;
import com.nearme.statistics.model.common.HiveFSTopresourceEntity;
import com.nearme.statistics.model.common.ModuledownChildEntity;
import com.nearme.statistics.model.common.ModulepositionChildEntity;
import com.nearme.statistics.model.common.NewuseractiveEntity;
import com.nearme.statistics.model.common.ProductinfoEntity;
import com.nearme.statistics.model.common.ProductrunEntity;
import com.nearme.statistics.model.common.WholeuserlifecycleEntity;
import com.nearme.statistics.util.DateUtil;

public class CommonDaoImpl implements CommonDao {
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

	/**
	 * 整体用户生命周期<br>
	 * 存储查询结果
	 * 
	 * @param list
	 */
	public int[] insertWholeuserlifecycle(List<WholeuserlifecycleEntity> list,
			String jobID) {
		if (null != list) {
			SqlSession sqlSession = null;
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (WholeuserlifecycleEntity entity : list) {
					Date statDate = entity.getStatDate();
					Date statEndDate = entity.getStatEndDate();
					String statDatestr = DateUtil.formatDate(statDate, "yyyyMMdd");
					String statEndDatestr = DateUtil.formatDate(statEndDate,
							"yyyyMMdd");
					long startimei = entity.getStartimei();
					long startcnt = entity.getStartcnt();
					long downcnt = entity.getDowncnt();
					long startdays = entity.getStartdays();

					String sql_insert = "insert into dm_hive_whole_userlife_cycle"
							+ "(job_id, statdate, statenddate, startimei, startcnt, downcnt, startdays) "
							+ "values ('" + jobID + "','" + statDatestr + "','"
							+ statEndDatestr + "','" + startimei + "','" + startcnt
							+ "','" + downcnt + "','" + startdays + "')";

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

	/**
	 * 整体用户生命周期 hive操作
	 */
	public List<WholeuserlifecycleEntity> hiveQueryWholeuserlifecycle(BaseDTO dto){
		List<WholeuserlifecycleEntity> list = null;
		String sql = getHqlWholeuserlifecycle(dto);
		
		SqlSession sqlSession = null;
		Date startday = dto.getStartDate();
		Date endday = dto.getEndDate();
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<WholeuserlifecycleEntity>();
				}

				WholeuserlifecycleEntity entity = new WholeuserlifecycleEntity();

				int datenum = rs.getInt(1);
				long startimei = rs.getLong(2);
				long startcnt = rs.getLong(3);
				long startdays = rs.getLong(4);
				long downcnt = rs.getLong(5);

				// 根据datenum产生statDateStr
				Date begin = DateUtil.AddDays(startday, (datenum - 1) * 10);
				Date end = DateUtil.AddDays(startday, datenum * 10 - 1);
				if (!DateUtil.isDateBefore(end, endday)) {
					end = endday;
				}

				entity.setStatDate(begin);
				entity.setStatEndDate(end);
				entity.setStartimei(startimei);
				entity.setStartcnt(startcnt);
				entity.setDowncnt(downcnt);
				entity.setStartdays(startdays);

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
	
	public String getHqlWholeuserlifecycle(BaseDTO dto) {
		String sampleStartDate = DateUtil.formatDate(dto.getSampleStartDate(),
				"yyyyMMdd");
		String sampleEndDate = DateUtil.formatDate(dto.getSampleEndDate(),
				"yyyyMMdd");
		String startDate = DateUtil
				.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		String startDate2 = DateUtil
		        .formatDate(dto.getStartDate(), "yyyy-MM-dd");
//        String endDate2 = DateUtil.formatDate(dto.getEndDate(), "yyyy-MM-dd");
		
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();

		// 拼接Hive查询HQL
		StringBuilder sb = new StringBuilder(256);
		sb.append("select ");
		sb.append("             tp1.stype  , ");
		sb.append("             tp1.start_imei , ");
		sb.append("             tp1.start_times , ");
		sb.append("             tp1.start_days , ");
		sb.append("             tp2.download_times , ");
		sb.append("             tp2.download_imei ");
		sb.append("         from ( ");
		sb.append("             select aa.stype , count(distinct aa.imei) start_imei, count(distinct aa.dayno) start_days , count(1) start_times ");
		sb.append("             from ( ");
		sb.append("                select ceil((datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),'" + startDate2 + "')+1)/10) stype, t.imei , t.dayno ");
		sb.append("                from ( ");
		sb.append("                    select dayno,imei ");
		sb.append("                    from client_start_ods.tjt_app_start ");
		sb.append("                    where system_id = ").append(systemID).append(" and channel_id = '").append(qudao).append("' and dayno >= ").append(startDate).append(" and dayno <= ").append(endDate);
		sb.append("                )t  ");
		sb.append("                inner join (");
		sb.append("                    select imei,dayno");
		sb.append("                    from client_start_ods.fxt_imei ");
		sb.append("                    where dayno >='");
		sb.append(sampleStartDate);
		sb.append("' and dayno <= '");
		sb.append(sampleEndDate);
		sb.append("' and system_id =");
		sb.append(systemID);
		sb.append("                ) t1  ");
		sb.append("                on t.imei=t1.imei  ");
		sb.append("            )  aa ");
		sb.append("            group by aa.stype ");
		sb.append("       ) tp1 ");
		sb.append("       inner join (");
		sb.append("           select ceil((datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),'" + startDate2 + "')+1)/10) stype  , count(distinct t.imei) download_imei, count(1) download_times ");
		sb.append("           from (");
		sb.append("               select dayno,imei ");
		sb.append("               from client_start_ods.tjt_download_trace ");
		sb.append("                    where system_id = ").append(systemID).append(" and channel_id = '").append(qudao).append("' and dayno >= ").append(startDate).append(" and dayno <= ").append(endDate);
		sb.append("           ) t ");
		sb.append("           inner join (");
		sb.append("               select imei,dayno ");
		sb.append("               from client_start_ods.fxt_imei ");
		sb.append("               where dayno >='");
		sb.append(sampleStartDate);
		sb.append("' and dayno <= '");
		sb.append(sampleEndDate);
		sb.append("' and system_id =");
		sb.append(systemID);
		sb.append("           ) t1 ");
		sb.append("           on t.imei=t1.imei ");
		sb.append("           group by ceil((datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),'" + startDate2 + "')+1)/10)) tp2 ");
		sb.append("       on tp1.stype = tp2.stype  ");
		sb.append("       order by tp1.stype");

		return sb.toString();
	}

	/**
	 * 月活跃天数和分布<br>
	 * hive操作
	 * 
	 * @param dto
	 * @return
	 */
	public List<ActivedaysdistributeEntity> hiveQueryActivedaysdistribute(
			BaseDTO dto){
		final String sql = getHqlActivedaysdistribute(dto);
		
		List<ActivedaysdistributeEntity> list = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<ActivedaysdistributeEntity>();
				}

				ActivedaysdistributeEntity entity = new ActivedaysdistributeEntity();

				String activedays = rs.getString(1);
				long startimei = rs.getLong(2);
				long downcnt = rs.getLong(3);
				long startcnt = rs.getLong(4);

				entity.setActivedays(activedays);
				entity.setStartimei(startimei);
				entity.setDowncnt(downcnt);
				entity.setStartcnt(startcnt);

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
	
	/**
	 * hql月活天数和分布
	 * @param dto
	 * @return
	 */
	public String getHqlActivedaysdistribute(BaseDTO dto){
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMM");
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();
		
		// 拼接Hive查询HQL
		  StringBuilder sb = new StringBuilder();
		  sb.append("select t1.dt , t1.start_imei , t2.down_times, t1.start_times ");
		  sb.append("from(");
		  sb.append(" select (case when t.cnt<=2 then '1#1-2D' ");
		  sb.append(" when t.cnt<=5 then '2#3-5D' " );
		  sb.append(" when t.cnt<=8 then '3#6-8D' " );
		  sb.append(" when t.cnt<=15 then '4#9-15D' " );
		  sb.append(" else '5#15D+' end) dt, " );
		  sb.append(" count(distinct imei) start_imei , sum(cnt_1) start_times" );
		  sb.append(" from(" );
		  sb.append(" select imei , count(distinct dayno) cnt , count(1) cnt_1 " );
		  sb.append(" from client_start_ods.tjt_app_start  " );
		  sb.append(" where dayno like '").append(startDate).append("%' and channel_id='").append(qudao).append("' and system_id=").append(systemID).append(" " );
		  sb.append(" group by imei" );
		  sb.append(" ) t" );
		  sb.append(" group by (case when t.cnt<=2 then '1#1-2D' when t.cnt<=5 " );
		  sb.append(" then '2#3-5D' when t.cnt<=8 then '3#6-8D' when t.cnt<=15 then '4#9-15D' else '5#15D+' end) " );
		  sb.append(" ) t1 " );
		  sb.append(" inner join (" );
		  sb.append(" select (case when t.cnt<=2 then '1#1-2D' " );
		  sb.append(" when t.cnt<=5 then '2#3-5D' " );
		  sb.append(" when t.cnt<=8 then '3#6-8D' " );
		  sb.append(" when t.cnt<=15 then '4#9-15D' " );
		  sb.append(" else '5#15D+' end) dt , sum(cnt_1) down_times" );
		  sb.append(" from(" );
		  sb.append(" select imei , count(distinct dayno) cnt , count(1) cnt_1 " );
		  sb.append(" from client_start_ods.tjt_download_trace  " );
		  sb.append(" where dayno like '").append(startDate).append("%' and channel_id='").append(qudao).append("' and system_id=").append(systemID).append(" " );
		  sb.append(" group by imei" );
		  sb.append(" ) t" );
		  sb.append(" group by (case when t.cnt<=2 then '1#1-2D' " );
		  sb.append(" when t.cnt<=5 then '2#3-5D' " );
		  sb.append(" when t.cnt<=8 then '3#6-8D' " );
		  sb.append(" when t.cnt<=15 then '4#9-15D' " );
		  sb.append(" else '5#15D+' end) " );
		  sb.append(" ) t2 " );
		  sb.append(" on t1.dt = t2.dt");
		
		return sb.toString();
	}
	
	/**
	 * 月活跃天数和分布<br>
	 * 存储结果
	 * 
	 * @param dto
	 * @return
	 */
	public int[] insertActivedaysdistribute(
			List<ActivedaysdistributeEntity> list,String jobID) {
		SqlSession sqlSession = null;
		if (list != null) {
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (ActivedaysdistributeEntity entity : list) {
					String activedays = entity.getActivedays();
					long startimei = entity.getStartimei();
					long startcnt = entity.getStartcnt();
					long downcnt = entity.getDowncnt();

					String sql_insert = "insert into dm_hive_activedays_distribute"
							+ "(job_id, activedays, startimei, startcnt, downcnt) "
							+ "values ('" + jobID + "','" + activedays + "','"
							+ startimei + "','" + startcnt + "','" + downcnt + "')";

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
	
	/**
	 * 新用户活跃 -- 第一个月<br>
	 * hive操作
	 */
	public List<NewuseractiveEntity> hiveQueryNewuseractivemonth(BaseDTO dto) {
		List<NewuseractiveEntity> list = null;
		String sql = getHqlNewuseractivemonth(dto);
		
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<NewuseractiveEntity>();
				}

				NewuseractiveEntity entity = new NewuseractiveEntity();

				String activedays = rs.getString(1);
				long startimei = rs.getLong(2);
				long startcnt = rs.getLong(3);
				long downcnt = rs.getLong(5);

				entity.setActivedays(activedays);
				entity.setStartimei(startimei);
				entity.setStartcnt(startcnt);
				entity.setDowncnt(downcnt);

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
	
	public String getHqlNewuseractivemonth(BaseDTO dto) {
//		String sampleStartDate = DateUtil.formatDate(dto.getSampleStartDate(),
//				"yyyy-MM-dd");
		String sampleEndDate = DateUtil.formatDate(dto.getSampleEndDate(),
				"yyyy-MM-dd");
		String sampleStartDate2 = DateUtil.formatDate(dto.getSampleStartDate(),
		        "yyyyMMdd");
		String sampleEndDate2 = DateUtil.formatDate(dto.getSampleEndDate(),
		        "yyyyMMdd");
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();

		String str = "select (case when tp1.act_days =1 then '1' when tp1.act_days <=3 then '2~3' when tp1.act_days <=7 then '4~7' when tp1.act_days <=15 then '8~15' else '>15' end)   , " +
				"count(distinct tp1.imei) , sum(tp1.cnt1) , count(distinct tp2.imei) , sum(tp2.cnt2) " +
				"from " +
				"(select t.imei, count(distinct t.dayno) act_days , count(1) cnt1 " +
				"from ( " +
				"select imei, dayno from client_start_ods.tjt_app_start " +
				"where system_id = "+systemID+" and channel_id = '"+qudao+"' and dayno >= "+sampleStartDate2+ " and dayno <=regexp_replace(date_add('"+sampleEndDate+"',29),'-','') ) t " +
				"inner join " +
				"(select * from client_start_ods.fxt_imei where dayno >="+sampleStartDate2+ " and dayno <= "+sampleEndDate2+" and system_id = "+systemID+") t1 on t.imei = t1.imei " +
				"where datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),from_unixtime(unix_timestamp(t1.dayno,'yyyyMMdd'),'yyyy-MM-dd'))<29 " +
				"group by t.imei ) tp1 " +
				"left outer join " +
				"(select imei , count(1) cnt2 from client_start_ods.tjt_download_trace " +
				"  where system_id = "+systemID+" and channel_id ='"+qudao+"' and dayno >= "+sampleStartDate2+ " and dayno <=regexp_replace(date_add('"+sampleEndDate+"',29),'-','') group by imei) tp2 on tp1.imei = tp2.imei " +
				"group by (case when tp1.act_days =1 then '1' when tp1.act_days <=3 then '2~3' when tp1.act_days <=7 then '4~7' when tp1.act_days <=15 then '8~15' else '>15' end) " ;
		
		return str;
	}

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * 存储结果
	 */
	public int[] insertNewuseractivemonth(List<NewuseractiveEntity> list,String jobID) {
		if (null != list) {
			SqlSession sqlSession = null;
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (NewuseractiveEntity entity : list) {
					String activedays = entity.getActivedays();
					long startimei = entity.getStartimei();
					long startcnt = entity.getStartcnt();
					long downcnt = entity.getDowncnt();

					String sql_insert = "insert into dm_hive_new_user_active_month"
							+ "(job_id, activedays, startimei, startcnt, downcnt) "
							+ "values ('" + jobID + "','" + activedays + "','"
							+ startimei + "','" + startcnt + "','" + downcnt + "')";

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
	
	/**
	 * 新用户活跃 -- 第一个周
	 */
	public List<NewuseractiveEntity> hiveQueryNewuseractiveweek(BaseDTO dto) {
		List<NewuseractiveEntity> list = null;
		final String sql = getHqlNewuseractiveweek(dto);
		
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<NewuseractiveEntity>();
				}

				NewuseractiveEntity entity = new NewuseractiveEntity();

				String activedays = rs.getString(1);
				long startimei = rs.getLong(2);
				long startcnt = rs.getLong(3);
				long downcnt = rs.getLong(5);

				entity.setActivedays(activedays);
				entity.setStartimei(startimei);
				entity.setStartcnt(startcnt);
				entity.setDowncnt(downcnt);

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
	
	public String getHqlNewuseractiveweek(BaseDTO dto) {
//		String sampleStartDate = DateUtil.formatDate(dto.getSampleStartDate(),
//				"yyyy-MM-dd");
		String sampleEndDate = DateUtil.formatDate(dto.getSampleEndDate(),
				"yyyy-MM-dd");
		String sampleStartDate2 = DateUtil.formatDate(dto.getSampleStartDate(),
		        "yyyyMMdd");
        String sampleEndDate2 = DateUtil.formatDate(dto.getSampleEndDate(),
		        "yyyyMMdd");
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();
		
		String str = "select tp1.act_days , count(distinct tp1.imei) , sum(tp1.cnt1) , count(distinct tp2.imei) , sum(tp2.cnt2) from " +
				"(select t.imei, count(distinct t.dayno) act_days , count(1) cnt1 " +
				"from ( select imei, dayno from client_start_ods.tjt_app_start " +
				"where system_id = " + systemID + " and channel_id = '" + qudao + "' and dayno >= " + sampleStartDate2 + " and dayno <=regexp_replace(date_add('" + sampleEndDate +
				"',6),'-','') ) t " +
				"inner join " +
				"(select * from client_start_ods.fxt_imei where dayno >= " + sampleStartDate2 +"  and dayno <=  "+ sampleEndDate2 +" and system_id ="+systemID+") t1 on t.imei = t1.imei " +
				"where datediff(from_unixtime(unix_timestamp(t.dayno,'yyyyMMdd'),'yyyy-MM-dd'),from_unixtime(unix_timestamp(t1.dayno,'yyyyMMdd'),'yyyy-MM-dd'))<6 " +
				"group by t.imei " +
				") tp1 " +
				"left outer join " +
				"(select imei , count(1) cnt2 from client_start_ods.tjt_download_trace " +
				"where system_id = " + systemID +" and channel_id ='" + qudao + "' and dayno >= " + sampleStartDate2 + " and dayno <=regexp_replace(date_add('" + sampleEndDate +
				"',6),'-','') group by imei) tp2 on tp1.imei = tp2.imei " +
				"group by tp1.act_days " ;
		
		return str;
	}

	public int[] insertNewuseractiveweek(List<NewuseractiveEntity> list,String jobID) {
		if (null != list) {
			SqlSession sqlSession = null;
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (NewuseractiveEntity entity : list) {
					String activedays = entity.getActivedays();
					long startimei = entity.getStartimei();
					long startcnt = entity.getStartcnt();
					long downcnt = entity.getDowncnt();

					String sql_insert = "insert into dm_hive_new_user_active_week"
							+ "(job_id, activedays, startimei, startcnt, downcnt) "
							+ "values ('" + jobID + "','" + activedays + "','"
							+ startimei + "','" + startcnt + "','" + downcnt + "')";

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

	/**
	 * 整体用户生命周期<br>
	 * 从结果表中查询
	 */
	public List<WholeuserlifecycleEntity> getWholeuserlifecycle(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.CommonDao."
							+ "getWholeuserlifecycle", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 月活跃天数及分布<br>
	 * 从结果表中查询
	 */
	public List<ActivedaysdistributeEntity> getActivedaysdistribute(
			HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.CommonDao."
							+ "getActivedaysdistribute", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * 从结果表中查询
	 */
	public List<NewuseractiveEntity> getNewuseractivemonth(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.CommonDao."
							+ "getNewuseractivemonth", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 新用户活跃 -- 第一个周<br>
	 * 从结果表中查询
	 */
	public List<NewuseractiveEntity> getNewuseractiveweek(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.CommonDao."
							+ "getNewuseractiveweek", hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 内容子平台->资源分类   结果查询
	 */
	public List<FSResourcetypeChildEntity> getFSResourcetype(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSResourcetype", hdto);
			
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 内容子平台->资源分类   所有日期
	 */
	public List<FSResourcetypeChildEntity> getFSResourcetypeDate(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSResourcetypeDate", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 内容子平台->资源分类  所有分类排序
	 */
	public List<FSResourcetypeChildEntity> getFSResourcetypeCategory(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSResourcetypeCategory", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 内容子平台->资源类别  Hive查询
	 */
	public List<FSResourcetypeChildEntity> hiveQueryFSResourcetype(BaseDTO dto) {
		List<FSResourcetypeChildEntity> list = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(this.getHqlFSResourcetype(dto));
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<FSResourcetypeChildEntity>();
				}
		
				FSResourcetypeChildEntity entity = new FSResourcetypeChildEntity();
		
				String dayno = rs.getString(1);
				String category_id = rs.getString(2);
				//category_id = new String(category_id.getBytes("UTF-8"),Charset.defaultCharset().name());
				long down_nums = rs.getLong(3);
				long down_apps = rs.getLong(4);
		
				entity.setStatDatestr(dayno);
				entity.setTypename(category_id);
				entity.setDowncnt(down_nums);
				entity.setDownapp(down_apps);
		
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

	/**
	 * 内容子平台->资源分类  HQL拼写
	 * @param dto
	 * @return
	 */
	public String getHqlFSResourcetype(BaseDTO dto){
		
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		//取开始时间当月1号
		String startDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getStartDate(),0), "yyyyMMdd");
		//取结束时间下月1号
		String endDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getEndDate(),-1), "yyyyMMdd");
		
		//拼接Hive查询HQL
		StringBuilder sbf = new StringBuilder(256);
		if(Constants.DAILY.equals(dto.getLidu())){
			sbf.append(" select m.dayno, if(n.CATEGORY_NAME is null or length(n.CATEGORY_NAME)<=0, m.category_id, n.CATEGORY_NAME) category_id, m.down_nums, m.down_apps ");
			sbf.append(" from( ");
			sbf.append(" select '' dayno, t.category_id, count(1) down_nums,count(distinct t.product_id) down_apps ");
			sbf.append(" from client_start_ods.tjt_download_trace t ");
			sbf.append(" where t.system_id='");
			sbf.append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='"); //and t.app_version='V2.4.0'
				sbf.append(dto.getQudao());
			}
			sbf.append("' and t.dayno>=");
			sbf.append( startDate );
			sbf.append(" and t.dayno<=");
			sbf.append( endDate );
			sbf.append(" group by t.category_id ");
			sbf.append(" union all ");
			sbf.append(" select t.dayno,t.category_id,count(1) down_nums,count(distinct t.product_id) down_apps ");
			sbf.append(" from client_start_ods.tjt_download_trace t ");
			sbf.append(" where t.system_id='");
			sbf.append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='"); //and t.app_version='V2.4.0'
				sbf.append(dto.getQudao());
			}
			sbf.append("' and t.dayno>=");
			sbf.append( startDate );
			sbf.append(" and t.dayno<=");
			sbf.append( endDate );
			sbf.append(" group by t.dayno,t.category_id ");
			sbf.append(" )m  left outer join  client_start_ods.BT_CATEGORY n on(m.category_id=n.category_id)");
			
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			sbf.append(" select m.dayno, if(n.CATEGORY_NAME is null or length(n.CATEGORY_NAME)<=0, m.category_id, n.CATEGORY_NAME) category_id, m.down_nums, m.down_apps ");
			sbf.append(" from( ");
			sbf.append(" select '' dayno, t.category_id, count(1) down_nums,count(distinct t.product_id) down_apps ");
			sbf.append(" from client_start_ods.tjt_download_trace t ");
			sbf.append(" where t.system_id='");
			sbf.append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='"); //and t.app_version='V2.4.0'
				sbf.append(dto.getQudao());
			}
			sbf.append("' and t.dayno>=");
			sbf.append( startDateMonth );
			sbf.append(" and t.dayno<");
			sbf.append( endDateMonth );
			sbf.append(" group by t.category_id ");
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,t.category_id,count(1) down_nums,count(distinct t.product_id) down_apps ");
			sbf.append(" from client_start_ods.tjt_download_trace t ");
			sbf.append(" where t.system_id='");
			sbf.append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='"); //and t.app_version='V2.4.0'
				sbf.append(dto.getQudao());
			}
			sbf.append("' and t.dayno>=");
			sbf.append( startDateMonth );
			sbf.append(" and t.dayno<");
			sbf.append( endDateMonth );
			sbf.append(" group by substr(t.dayno,0,6),t.category_id ");
			sbf.append(" )m left outer join  client_start_ods.BT_CATEGORY n on(m.category_id=n.category_id) ");
		}
		
		return sbf.toString();
	}
	
	/**
	 * 内容子平台->资源分类  将Hive查询结果存入结果表
	 */
	public int[] insertFSResourcetype(List<FSResourcetypeChildEntity> list,
			String jobID) {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			for (FSResourcetypeChildEntity entity : list) {
				StringBuilder sql = new StringBuilder(256);
				sql.append("INSERT INTO DM_HIVE_CATEGORY_DOWN(JOB_ID,STATDATE,CATEGORY_ID,DOWN_NUMS,DOWN_APPS)");
				sql.append("VALUES('");
				sql.append(jobID);
				sql.append("','");
				sql.append(entity.getStatDatestr());
				sql.append("','");
				sql.append(entity.getTypename());
				sql.append("','");
				sql.append(entity.getDowncnt());
				sql.append("','");
				sql.append(entity.getDownapp());
				sql.append("')");

				stmt.addBatch(sql.toString());
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
	}
	
	/**
	 * 内容子平台->自然模块  Hive查询
	 */
	public List<FSNaturemodleChildEntity> hiveQueryFSNaturemodlenum(BaseDTO dto) {
		List<FSNaturemodleChildEntity> list = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(this.getHqlFSNaturemodle(dto));
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<FSNaturemodleChildEntity>();
				}
				FSNaturemodleChildEntity entity = new FSNaturemodleChildEntity();
				String statdate = rs.getString(1);
				String source_name = rs.getString(2);
				
				long down_nums = rs.getLong(3);
				long down_imeis = rs.getLong(4);
		
				entity.setStatDatestr(statdate);
				entity.setModulename(source_name);
				entity.setDowncnt(down_nums);
				entity.setDownimei(down_imeis);
		
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
	
	/**
	 * 内容子平台->自然模块  HQL拼写
	 * @param dto
	 * @return
	 */
	public String getHqlFSNaturemodle(BaseDTO dto){
		
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		//取开始时间当月1号
		String startDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getStartDate(),0), "yyyyMMdd");
		//取结束时间下月1号
		String endDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getEndDate(),-1), "yyyyMMdd");
		
		//拼接Hive查询HQL
		StringBuilder sbf = new StringBuilder(256);
		if(Constants.DAILY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.GROUP_NAME, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//Group By  GROUP_NAME
			sbf.append(" select '' dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by m.GROUP_NAME ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select t.dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by t.dayno,m.GROUP_NAME ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select t.dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by t.dayno ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" )n ");
			
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.GROUP_NAME, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//*******Group By  GROUP_NAME
			sbf.append(" select '' dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by m.GROUP_NAME ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by substr(t.dayno,0,6),m.GROUP_NAME ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by substr(t.dayno,0,6) ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupcode() && !"".equals(dto.getGroupcode())){
				sbf.append(" and group_name in(").append(dto.getGroupcode()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" )n ");
			
		}
		
		return sbf.toString();
	}
	
	/**
	 * 内容子平台->单个运营点  HQL拼写
	 * @param dto
	 * @return
	 */
	public String getHqlFSOperatepoint(BaseDTO dto){
		
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		//取开始时间当月1号
		String startDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getStartDate(),0), "yyyyMMdd");
		//取结束时间下月1号
		String endDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getEndDate(),-1), "yyyyMMdd");
		
		//拼接Hive查询HQL
		StringBuilder sbf = new StringBuilder(256);
		if(Constants.DAILY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.click_index, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//*******Group By  GROUP_NAME
			sbf.append(" select '' dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by t.click_index ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select t.dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by t.dayno,t.click_index ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select t.dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by t.dayno ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" )n ");
			
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.click_index, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//*******Group By  GROUP_NAME
			sbf.append(" select '' dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by t.click_index ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by substr(t.dayno,0,6),t.click_index ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" group by substr(t.dayno,0,6) ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.BT_SOURCE_CODE_GROUP where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupcode());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion())){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where (length(m.category_id)<=0 and m.click_index is null) or (length(m.category_id)<=0 and m.click_index=t.click_index) or (m.click_index is null and m.category_id=t.category_id) or (m.category_id=t.category_id and m.click_index=t.click_index) ");
			sbf.append(" )n ");
			
		}
		
		return sbf.toString();
	}
	
	/**
	 * 内容子平台->自然模块  将Hive查询结果存入结果表
	 */
	public int[] insertFSNaturemodlenum(List<FSNaturemodleChildEntity> list,
			String jobID) {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			for (FSNaturemodleChildEntity entity : list) {
				StringBuilder sql = new StringBuilder(256);
				sql.append("INSERT INTO DM_HIVE_SOURCE_DOWN(JOB_ID,STATDATE,SOURCE_NAME,DOWN_NUMS,DOWN_IMEIS)");
				sql.append("VALUES('");
				sql.append(jobID);
				sql.append("','");
				sql.append(entity.getStatDatestr());
				sql.append("','");
				sql.append(entity.getModulename());
				sql.append("','");
				sql.append(entity.getDowncnt());
				sql.append("','");
				sql.append(entity.getDownimei());
				sql.append("')");

				stmt.addBatch(sql.toString());
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
	}
	
	/**
	 * 内容子平台->自然模块  结果查询
	 */
	public List<FSNaturemodleChildEntity> getFSNaturemodle(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSNaturemodle", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 内容子平台->自然模块  所有日期
	 */
	public List<FSNaturemodleChildEntity> getFSNaturemodleDate(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSNaturemodleDate", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 内容子平台->自然模块  所有模块排序
	 */
	public List<FSNaturemodleChildEntity> getFSNaturemodleSource(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSNaturemodleSource", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 产品名
	 */
	public List<ProductinfoEntity> getProductinfoList(ProductinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getProductinfoList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	public List<FSOperatepointEntity> getFSOperatepoint(HivejobDTO hdto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FSTopresourceEntity> getFSTopresource(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSTopresource",hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	public List<FSTopresourceEntity> getFSTopresourceTotal(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSTopresourceTotal",hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<FSUpdatenumEntity> getFSUpdatenum(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSUpdatenum",hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	public List<FSUpdatenumEntity> getFSUpdatenumTotal(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getFSUpdatenumTotal",hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ProductrunEntity> getProductrunmodule(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getProductrunmodule",hdto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<FSOperatepointEntity> hiveQueryFSOperatepoint(BaseDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<HiveFSTopresourceEntity> hiveQueryFSTopresource(BaseDTO dto) {
		final String sql = getHqlFSTopresource(dto);
		
		List<HiveFSTopresourceEntity> list = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<HiveFSTopresourceEntity>();
				}

				HiveFSTopresourceEntity entity = new HiveFSTopresourceEntity();

				int statdate = rs.getInt(1);
				int position = rs.getInt(2);
				long downnum = rs.getLong(3);

				entity.setStatdate(statdate);
				entity.setPosition(position);
				entity.setDownnum(downnum);

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
	
	/**
	 * top资源 hql语句
	 * @param dto
	 * @return
	 */
	public String getHqlFSTopresource(BaseDTO dto){
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();
		String lidu = dto.getLidu();
		
		// 拼接Hive查询HQL
		StringBuilder sb = new StringBuilder(256);
		if (Constants.DAILY.equalsIgnoreCase(lidu)) {
			sb.append(" select t1.dayno ,t2.position ,count(1) nums ");
			sb.append(" from client_start_ods.tjt_download_trace t1 ");
			sb.append(" join( ");
			sb.append(" select m.product_id, row_number() over(sort by nums desc) position ");
			sb.append(" from( ");
			sb.append(" select t.product_id,count(1) nums ");
			sb.append(" from client_start_ods.tjt_download_trace t ");
			sb.append(" where 1 = 1 ");
			sb.append(" and t.system_id=").append(systemID);
			if (null != qudao && !"".equals(qudao)) {
				sb.append(" and t.channel_id=").append(qudao);
			}
//			sb.append(" and t.app_version='V2.4.0'");
			sb.append(" and t.dayno>=").append(startDate);
			sb.append(" and t.dayno<=").append(endDate);
			sb.append(" group by t.product_id order by nums desc ");
			sb.append(" )m ");
			sb.append(" )t2 ");
			sb.append(" on ( t1.product_id=t2.product_id ");
			sb.append(" and t1.system_id= ").append(systemID);
			if (null != qudao && !"".equals(qudao)) {
			    sb.append(" and t1.channel_id= ").append(qudao);
			}
//			sb.append(" and t1.app_version='V2.4.0' ");
			sb.append(" and t1.dayno>= ").append(startDate);
			sb.append(" and t1.dayno<=").append(endDate).append(" ) ");
			sb.append(" group by t1.dayno,t2.position ");
			sb.append(" order by nums desc");
		} else if (Constants.MONTHLY.equalsIgnoreCase(lidu)){
			sb.append(" select substr(t1.dayno,0,6),t2.position,count(1) nums ");
			sb.append(" from tjt_download_trace t1 ");
			sb.append(" join( ");
			sb.append(" select m.product_id, row_number() over(sort by nums desc) position ");
			sb.append(" from( ");
			sb.append(" select t.product_id,count(1) nums  ");
			sb.append(" from client_start_ods.tjt_download_trace t  ");
			sb.append(" where 1 = 1 ");
			sb.append(" and t.system_id= ").append(systemID);
			sb.append(" and t.channel_id= ").append(qudao);
//		    sb.append(" and t.app_version='V2.4.0' ");
			sb.append(" and t.dayno>= ").append(startDate);
			sb.append(" and t.dayno<= ").append(endDate);
			sb.append(" group by t.product_id order by nums desc ");
			sb.append(" ) m ");
			sb.append(" ) t2 ");
		    sb.append(" on(t1.product_id=t2.product_id ");
		    sb.append(" and t1.system_id= ").append(systemID);
		    sb.append(" and t1.channel_id= ").append(qudao);
//		    sb.append(" and t1.app_version='V2.4.0' ");
		    sb.append(" and t1.dayno>= ").append(startDate);
		    sb.append(" and t1.dayno<= ").append(endDate).append( " ) ");
			sb.append(" group by substr(t1.dayno,0,6),t2.position ");
			sb.append(" order by nums desc");
		}
		
		return sb.toString();
	}

	public List<FSUpdatenumEntity> hiveQueryFSUpdatenum(BaseDTO dto) {
		List<FSUpdatenumEntity> list = null;
		final String sql = getHqlFSUpdatenum(dto);
		
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<FSUpdatenumEntity>();
				}

				FSUpdatenumEntity entity = new FSUpdatenumEntity();

				String statDatestr = rs.getString(1);
				long downimei = rs.getLong(2);
				long downcnt = rs.getLong(3);
				long updatecnt = rs.getLong(4);
				long updateimei = rs.getLong(5);
				long updateres = rs.getLong(6);

				entity.setStatDatestr(statDatestr);
				entity.setDownimei(downimei);
				entity.setDowncnt(downcnt);
				entity.setUpdatecnt(updatecnt);
				entity.setUpdateimei(updateimei);
				entity.setUpdateres(updateres);

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
	
	public String getHqlFSUpdatenum(BaseDTO dto){
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();
		String lidu = dto.getLidu();
		
		StringBuilder sb = new StringBuilder(256);
		if (Constants.DAILY.equalsIgnoreCase(lidu)) {
			sb.append(" select m.dayno, ");
			sb.append("     count(distinct m.imei), ");
			sb.append("     count(1),");
			sb.append("     sum(m.update_nums), ");
			sb.append("     count(distinct m.update_imeis), ");
			sb.append("     count(distinct m.update_apps) ");
			sb.append(" from( ");
			sb.append("     select t.dayno, t.imei, ");
			sb.append("         case when t.source_code in('1030','1018','1086') then 1 else 0 end update_nums, ");
			sb.append("         case when t.source_code in('1030','1018','1086') then t.imei end update_imeis, ");
			sb.append("         case when t.source_code in('1030','1018','1086') then t.product_id end update_apps ");
			sb.append("     from client_start_ods.tjt_download_trace t");
			sb.append("     where t.channel_id='").append(qudao).append("' and t.system_id=").append(systemID)
			  .append(" and t.dayno>=").append(startDate).append(" and t.dayno<=").append(endDate).append(" ");
			sb.append(" ) m ");
			sb.append(" group by m.dayno");
		} else if(Constants.MONTHLY.equalsIgnoreCase(lidu)){
			sb.append(" select dayno, ");
			sb.append("     count(distinct m.imei), ");
			sb.append("     count(1), ");
			sb.append("     sum(m.update_nums), ");
			sb.append("     count(distinct m.update_imeis), ");
			sb.append("     count(distinct m.update_apps)");
			sb.append(" from( ");
			sb.append("     select substr(t.dayno,0,6) dayno, t.imei, ");
			sb.append("         case when t.source_code in('1030','1018','1086') then 1 else 0 end update_nums, ");
			sb.append("         case when t.source_code in('1030','1018','1086') then t.imei end update_imeis, ");
			sb.append("         case when t.source_code in('1030','1018','1086') then t.product_id end update_apps");
			sb.append("     from client_start_ods.tjt_download_trace t");
			sb.append("     where t.channel_id='").append(qudao).append("' and t.system_id=").append(systemID)
			  .append(" and t.dayno>=").append(startDate).append(" and t.dayno<=").append(endDate).append(" ");
			sb.append(" ) m ");
			sb.append(" group by m.dayno");
		}
		
		return sb.toString();
	}

	public List<ProductrunEntity> hiveQueryProductrunmodule(ProductinfoDTO dto) {
		final String sql = getHqlProductrunmodule(dto);
		
		List<ProductrunEntity> list = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<ProductrunEntity>();
				}

				ProductrunEntity entity = new ProductrunEntity();

				String name = rs.getString(1);
				long downimei = rs.getLong(2);
				long directdown = rs.getLong(3);
				long detaildown = rs.getLong(4);
				long browseimei = rs.getLong(5);
				
				entity.setName(name);
				entity.setDownimei(downimei);
				entity.setBrowseimei(browseimei);
				entity.setDirectdown(directdown);
				entity.setDetaildown(detaildown);

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
	
	public String getHqlProductrunmodule(ProductinfoDTO dto){
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		int systemID = dto.getSystemID();
		String qudao = dto.getQudao();
		String productIDs = dto.getProductIDs();
		
		StringBuilder sb = new StringBuilder(256);
		sb.append("select (case when tp1.group_name is null then tp2.group_name else tp1.group_name end)  group_name ,tp1.down_imei, tp1.down_zj, tp1.down_xq, tp2.br_times ");
		sb.append("from  ");
		sb.append("(select t1.group_name ,  ");
		sb.append("     count(distinct t.imei) down_imei,  ");
		sb.append("     sum(case t1.down_type when 'ZJ' then 1 else 0 end) down_zj,  ");
		sb.append("     sum(case t1.down_type when 'XQ' then 1 else 0 end) down_xq ");
		sb.append(" from (  ");
		sb.append("     select imei, source_code ,category_id ");
		sb.append("     from client_start_ods.tjt_download_trace  ");
		sb.append("     where system_id = ").append(systemID).append(" and dayno >= ").append(startDate).append(" and dayno <= ").append(endDate).append(" and product_id in (").append(productIDs).append(") and channel_id = ").append(qudao).append(" ");
		sb.append(" ) t  ");
		sb.append(" inner join ( ");
		sb.append("     select * from client_start_ods.bt_module ");
		sb.append("     where system_id = ").append(systemID).append(") t1  ");
		sb.append(" on t.source_code = t1.source_code ");
		sb.append(" where  (length(trim(t1.category_id))=0 or t.category_id = t1.category_id or t1.category_id is null)   ");
		sb.append(" group by t1.group_name) tp1 ");
		sb.append(" full outer join   ");
		sb.append("(select t1.group_name, count(1) br_times from  ");
		sb.append("(select * from client_start_ods.tjt_browse_trace  ");
		sb.append("     where system_id = ").append(systemID).append(" and dayno >= ").append(startDate).append(" and dayno <= ").append(endDate).append(" and product_id in (").append(productIDs).append(") and channel_id = ").append(qudao).append(" ");
		sb.append(" ) t2 ");
		sb.append("inner join ( ");
		sb.append("     select * from client_start_ods.bt_module  ");
		sb.append("     where system_id = ").append(systemID).append(") t1  ");
		sb.append("on t2.source_code = t1.source_code ");
		sb.append("where (length(trim(t1.category_id))=0 or t2.category_id = t1.category_id  or t1.category_id is null)   ");
		sb.append("group by t1.group_name) tp2 ");
		sb.append("on tp1.group_name = tp2.group_name ");
		
		return sb.toString();
	}

	public int[] insertFSOperatepoint(List<FSOperatepointEntity> list,
			String jobID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] insertFSTopresource(List<HiveFSTopresourceEntity> list,
			String jobID) {
		if (null != list) {
			SqlSession sqlSession = null;
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (HiveFSTopresourceEntity entity : list) {
					StringBuilder sql = new StringBuilder(256);
					sql.append("INSERT INTO DM_HIVE_TOP_DOWN(JOB_ID,STATDATE,POSITION,DOWN_NUMS)");
					sql.append("VALUES('");
					sql.append(jobID);
					sql.append("','");
					sql.append(entity.getStatdate());
					sql.append("','");
					sql.append(entity.getPosition());
					sql.append("','");
					sql.append(entity.getDownnum());
					sql.append("')");

					stmt.addBatch(sql.toString());
				}
				return stmt.executeBatch();
			} catch (Exception e) {
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

	public int[] insertFSUpdatenum(List<FSUpdatenumEntity> list, String jobID) {
		if (null != list) {
			SqlSession sqlSession = null;
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (FSUpdatenumEntity entity : list) {
					StringBuilder sql = new StringBuilder(256);
					sql.append("INSERT INTO DM_HIVE_UPDATE(JOB_ID,STATDATE,DOWN_IMEIS,DOWN_NUMS,UPDATE_NUMS,UPDATE_IMEIS,UPDATE_APPS)");
					sql.append("VALUES('");
					sql.append(jobID);
					sql.append("','");
					sql.append(entity.getStatDatestr());
					sql.append("','");
					sql.append(entity.getDownimei());
					sql.append("','");
					sql.append(entity.getDowncnt());
					sql.append("','");
					sql.append(entity.getUpdatecnt());
					sql.append("','");
					sql.append(entity.getUpdateimei());
					sql.append("','");
					sql.append(entity.getUpdateres());
					sql.append("')");
					
					stmt.addBatch(sql.toString());
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

	public int[] insertProductrunmodule(List<ProductrunEntity> list,
			String jobID) {
		if (null != list) {
			SqlSession sqlSession = null;
			try {
				sqlSession = sqlSessionFactory.openSession();
				Statement stmt = sqlSession.getConnection().createStatement();
				for (ProductrunEntity entity : list) {
					StringBuilder sql = new StringBuilder(256);
					sql.append("INSERT INTO DM_HIVE_PRODUCTRUN_MODULE(JOB_ID,NAME,DOWN_IMEIS,BROWSE_IMEIS,DIRECT_DOWN,DETAIL_DOWN)");
					sql.append("VALUES('");
					sql.append(jobID);
					sql.append("','");
					sql.append(entity.getName());
					sql.append("','");
					sql.append(entity.getDownimei());
					sql.append("','");
					sql.append(entity.getBrowseimei());
					sql.append("','");
					sql.append(entity.getDirectdown());
					sql.append("','");
					sql.append(entity.getDetaildown());
					sql.append("')");
					
					stmt.addBatch(sql.toString());
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

	public void deleteActivedaysdistribute(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteActivedaysdistribute", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteFSNaturemodlenum(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteFSNaturemodlenum", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteFSResourcetype(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteFSResourcetype", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteFSTopresource(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteFSTopresource", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteFSUpdatenum(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteFSUpdatenum", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteNewuseractivemonth(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteNewuseractivemonth", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteNewuseractiveweek(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteNewuseractiveweek", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteProductrunmodule(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteProductrunmodule", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public void deleteWholeuserlifecycle(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteWholeuserlifecycle", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuledownChildEntity> getModuledown(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModuledown", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuledownChildEntity> getModuledownDate(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModuledownDate", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuledownChildEntity> getModuledownSource(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModuledownSource", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public String getHqlModuledown(BaseDTO dto) {
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		//取开始时间当月1号
		String startDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getStartDate(),0), "yyyyMMdd");
		//取结束时间下月1号
		String endDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getEndDate(),-1), "yyyyMMdd");
		
		//拼接Hive查询HQL
		StringBuilder sbf = new StringBuilder(256);
		if(Constants.DAILY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.GROUP_NAME, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//Group By  GROUP_NAME
			sbf.append(" select '' dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by m.GROUP_NAME ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select t.dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by t.dayno,m.GROUP_NAME ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select t.dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by t.dayno ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" )n ");
			
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.GROUP_NAME, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//*******Group By  GROUP_NAME
			sbf.append(" select '' dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by m.GROUP_NAME ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,m.GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by substr(t.dayno,0,6),m.GROUP_NAME ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by substr(t.dayno,0,6) ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' GROUP_NAME,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID()).append("' ");
			//选择的模块
			if(null!=dto.getGroupname() && !"".equals(dto.getGroupname())){
				sbf.append(" and group_name in(").append(dto.getGroupname()).append(") "); 
			}
			sbf.append(" )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE  ");
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" )n ");
			
		}
		
		return sbf.toString();
	}

	public void deleteModuledown(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteModuledown", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuledownChildEntity> hiveQueryModuledown(BaseDTO dto) {
		List<ModuledownChildEntity> list = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(this.getHqlModuledown(dto));
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<ModuledownChildEntity>();
				}
				ModuledownChildEntity entity = new ModuledownChildEntity();
				String statdate = rs.getString(1);
				String source_name = rs.getString(2);
				
				long down_nums = rs.getLong(3);
				long down_imeis = rs.getLong(4);
		
				entity.setStatDatestr(statdate);
				entity.setModulename(source_name);
				entity.setDowncnt(down_nums);
				entity.setDownimei(down_imeis);
		
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

	public void insertModuledown(List<ModuledownChildEntity> list, String jobID) {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			for (ModuledownChildEntity entity : list) {
				StringBuilder sql = new StringBuilder(256);
				sql.append("INSERT INTO DM_HIVE_MODULE_DOWN(JOB_ID,STATDATE,SOURCE_NAME,DOWN_NUMS,DOWN_IMEIS)");
				sql.append("VALUES('");
				sql.append(jobID);
				sql.append("','");
				sql.append(entity.getStatDatestr());
				sql.append("','");
				sql.append(entity.getModulename());
				sql.append("','");
				sql.append(entity.getDowncnt());
				sql.append("','");
				sql.append(entity.getDownimei());
				sql.append("')");

				stmt.addBatch(sql.toString());
			}
		    stmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}

	public void deleteModuleposition(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.CommonDao."
					+ "deleteModuleposition", hdto);
		} catch (Exception e) {
		} finally {
			sqlSession.close();
		}
	}

	public String getHqlModuleposition(BaseDTO dto) {
		String startDate = DateUtil.formatDate(dto.getStartDate(), "yyyyMMdd");
		String endDate = DateUtil.formatDate(dto.getEndDate(), "yyyyMMdd");
		//取开始时间当月1号
		String startDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getStartDate(),0), "yyyyMMdd");
		//取结束时间下月1号
		String endDateMonth = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(dto.getEndDate(),-1), "yyyyMMdd");
		
		//拼接Hive查询HQL
		StringBuilder sbf = new StringBuilder(256);
		if(Constants.DAILY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.click_index, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//*******Group By  GROUP_NAME
			sbf.append(" select '' dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by t.click_index ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select t.dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by t.dayno,t.click_index ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select t.dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by t.dayno ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDate );
			sbf.append(" and t.dayno<=").append( endDate );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" )n ");
			
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			sbf.append(" select n.dayno, n.click_index, n.down_nums, n.down_imeis ");
			sbf.append(" from( ");
			//*******Group By  GROUP_NAME
			sbf.append(" select '' dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by t.click_index ");
			//*******Group By  dayno GROUP_NAME
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,t.click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by substr(t.dayno,0,6),t.click_index ");
			//*******Group By  dayno
			sbf.append(" union all ");
			sbf.append(" select substr(t.dayno,0,6) dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" group by substr(t.dayno,0,6) ");
			//*******Group By  All
			sbf.append(" union all ");
			sbf.append(" select '' dayno,'' click_index,count(1) down_nums,count(distinct t.imei) down_imeis ");
			sbf.append(" from (select * from client_start_ods.bt_module where system_id='").append(dto.getSystemID());
			//选择的模块
			sbf.append("' and group_name='").append(dto.getGroupname());
			sbf.append("' )m join client_start_ods.tjt_download_trace t on(t.system_id=m.system_id and t.source_code=m.SOURCE_CODE ");
			sbf.append(" and t.click_index<=").append(dto.getPosition());
			sbf.append(" and t.system_id='").append(dto.getSystemID());
			if(null!=dto.getQudao() && !"".equals(dto.getQudao())){
				sbf.append("' and t.channel_id='").append(dto.getQudao()); 
			}
			if(null!=dto.getAppVersion() && !"".equals(dto.getAppVersion()) && !"all".equals(dto.getAppVersion()) ){
				sbf.append("' and t.app_version='").append(dto.getAppVersion());
			}
			sbf.append("' and t.dayno>=").append( startDateMonth );
			sbf.append(" and t.dayno<").append( endDateMonth );
			sbf.append(") where length(m.category_id)=0 or m.category_id=t.category_id ");
			sbf.append(" )n ");
			
		}
		
		return sbf.toString();
	}

	public List<ModulepositionChildEntity> getModuleposition(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModuleposition", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModulepositionChildEntity> getModulepositionDate(HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModulepositionDate", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModulepositionChildEntity> getModulepositionSource(
			HivejobDTO hdto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModulepositionSource", hdto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModulepositionChildEntity> hiveQueryModuleposition(BaseDTO dto) {
		List<ModulepositionChildEntity> list = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = hiveSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(this.getHqlModuleposition(dto));
			while (rs.next()) {
				if (null == list) {
					list = new ArrayList<ModulepositionChildEntity>();
				}
				ModulepositionChildEntity entity = new ModulepositionChildEntity();
				String statdate = rs.getString(1);
				String source_name = rs.getString(2);
				
				long down_nums = rs.getLong(3);
				long down_imeis = rs.getLong(4);
		
				entity.setStatDatestr(statdate);
				entity.setModulename(source_name);
				entity.setDowncnt(down_nums);
				entity.setDownimei(down_imeis);
		
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

	public void insertModuleposition(List<ModulepositionChildEntity> list,
			String jobID) {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			Statement stmt = sqlSession.getConnection().createStatement();
			for (ModulepositionChildEntity entity : list) {
				StringBuilder sql = new StringBuilder(256);
				sql.append("INSERT INTO DM_HIVE_MODULE_POSITION(JOB_ID,STATDATE,SOURCE_NAME,DOWN_NUMS,DOWN_IMEIS)");
				sql.append("VALUES('");
				sql.append(jobID);
				sql.append("','");
				sql.append(entity.getStatDatestr());
				sql.append("','");
				sql.append(entity.getModulename());
				sql.append("','");
				sql.append(entity.getDowncnt());
				sql.append("','");
				sql.append(entity.getDownimei());
				sql.append("')");

				stmt.addBatch(sql.toString());
			}
		    stmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}
}
