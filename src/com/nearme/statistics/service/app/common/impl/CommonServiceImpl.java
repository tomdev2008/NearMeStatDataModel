package com.nearme.statistics.service.app.common.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.app.common.CommonDao;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.RequestjobDTO;
import com.nearme.statistics.model.ColumnValueEntity;
import com.nearme.statistics.model.common.ActivedaysEntity;
import com.nearme.statistics.model.common.ChanneldailyEntity;
import com.nearme.statistics.model.common.ChannelgeneralEntity;
import com.nearme.statistics.model.common.ChannelhealthEntity;
import com.nearme.statistics.model.common.ChannelqualityEntity;
import com.nearme.statistics.model.common.DetaildayEntity;
import com.nearme.statistics.model.common.DetailmonthEntity;
import com.nearme.statistics.model.common.DetailweekEntity;
import com.nearme.statistics.model.common.DowndayEntity;
import com.nearme.statistics.model.common.DownleijiEntity;
import com.nearme.statistics.model.common.DownmonthEntity;
import com.nearme.statistics.model.common.DownremainEntity;
import com.nearme.statistics.model.common.DownsourceCPTEntity;
import com.nearme.statistics.model.common.DownsourceEntity;
import com.nearme.statistics.model.common.DownweekEntity;
import com.nearme.statistics.model.common.ErrorDetailEntity;
import com.nearme.statistics.model.common.ErrorEntity;
import com.nearme.statistics.model.common.GrandtotalEntity;
import com.nearme.statistics.model.common.HHStartEntity;
import com.nearme.statistics.model.common.IncreaseremainEntity;
import com.nearme.statistics.model.common.ModuledayEntity;
import com.nearme.statistics.model.common.MonthhealthEntity;
import com.nearme.statistics.model.common.NetdayEntity;
import com.nearme.statistics.model.common.PagejumpEntity;
import com.nearme.statistics.model.common.PagevisitEntity;
import com.nearme.statistics.model.common.Remain30daysEntity;
import com.nearme.statistics.model.common.SelfeventEntity;
import com.nearme.statistics.model.common.StartremainEntity;
import com.nearme.statistics.model.common.UactionEntity;
import com.nearme.statistics.model.common.UserlifecycleDecayEntity;
import com.nearme.statistics.model.common.UserlifecycleTotalEntity;
import com.nearme.statistics.model.common.VersiondayEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.StringUtil;

public class CommonServiceImpl implements CommonService {
	private CommonDao dao;

	/**
	 * 日明细
	 */
	public List<DetaildayEntity> getDetaildayList(BaseDTO dto) {
		return dao.getDetaildayList(dto);
	}

	/**
	 * 月明细
	 */
	public List<DetailmonthEntity> getDetailmonthList(BaseDTO dto) {
		List<DetailmonthEntity> list = dao.getDetailmonthList(dto);
		// sql不计算平均值，这里补充
		for (DetailmonthEntity entity : list) {
			Date date = entity.getStatDate();
			int days = DateUtil.getDays(date);

			long startimei = entity.getStartImei();
			// long startuser = entity.getStartUser();
			// long newimei = entity.getNewImei();
			// long newuser = entity.getNewUser();
			long monthstartimei = entity.getMonthstartimei();
			long monthstartuser = entity.getMonthstartuser();
			long monthnewimei = entity.getMonthnewimei();
			long monthnewuser = entity.getMonthnewuser();

			long avgstartimei = Math.round(monthstartimei / (float) days);
			long avgstartuser = Math.round(monthstartuser / (float) days);
			long avgnewimei = Math.round(monthnewimei / (float) days);
			long avgnewuser = Math.round(monthnewuser / (float) days);
			float avgActiveDay = Math.round(monthstartimei * 100
					/ (float) startimei)
					/ (float) 100;

			entity.setAvgStartImei(avgstartimei);
			entity.setAvgStartUser(avgstartuser);
			entity.setAvgNewImei(avgnewimei);
			entity.setAvgNewUser(avgnewuser);
			entity.setAvgActiveDay(avgActiveDay);
		}
		return list;
	}

	/**
	 * 周明细
	 */
	public List<DetailweekEntity> getDetailweekList(BaseDTO dto) {
		List<DetailweekEntity> list = dao.getDetailweekList(dto);

		for (int i = 0; i < list.size(); i++) {
			DetailweekEntity entity = (DetailweekEntity) list.get(i);
			Date statdate = entity.getStatDate();
			Date enddate = DateUtil.AddDays(statdate, 6);

			String weekStr = String.format("%s~%s", DateUtil.formatDate(statdate,
					"MM-dd"), DateUtil.formatDate(enddate, "MM-dd"));
			entity.setWeekStr(weekStr);
			list.set(i, entity);
		}

		return list;
	}

	/**
	 * 累计
	 */
	public List<GrandtotalEntity> getGrandTotalList(BaseDTO dto) {
		return dao.getGrandTotalList(dto);
	}

	public CommonDao getDao() {
		return dao;
	}

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	// 活跃天数
	public List<ActivedaysEntity> getActivedaysList(BaseDTO dto) {
		List<ActivedaysEntity> list = dao.getActivedaysList(dto);
		long total = 0;
		for (ActivedaysEntity entity : list) {
			total += entity.getStartImei();
		}

		for (ActivedaysEntity entity : list) {
			long startImei = entity.getStartImei();
			float ratioImei = 0;
			if (total != 0) {
				ratioImei = Math.round(startImei * 10000 / (float) total)
						/ (float) 100;
			}
			entity.setRatioImei(ratioImei);
		}

		return list;
	}

	/**
	 * 新增留存
	 */
	public List<IncreaseremainEntity> getIncreaseremainList(BaseDTO dto) {
		List<IncreaseremainEntity> list = dao.getIncreaseremainList(dto);
		for (IncreaseremainEntity entity : list) {
			double nrr1 = entity.getNrr1();
			double nrr2 = entity.getNrr2();
			double nrr3 = entity.getNrr3();
			double nurr1 = entity.getNurr1();
			double nurr2 = entity.getNurr2();
			double nurr3 = entity.getNurr3();

			DecimalFormat formatter = new DecimalFormat("##.##");
			double nrr1f = Double.valueOf(formatter.format(nrr1 * 100));
			double nrr2f = Double.valueOf(formatter.format(nrr2 * 100));
			double nrr3f = Double.valueOf(formatter.format(nrr3 * 100));
			double nurr1f = Double.valueOf(formatter.format(nurr1 * 100));
			double nurr2f = Double.valueOf(formatter.format(nurr2 * 100));
			double nurr3f = Double.valueOf(formatter.format(nurr3 * 100));

			if ("WEEKLY".equals(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
			entity.setNrr1(nrr1f);
			entity.setNrr2(nrr2f);
			entity.setNrr3(nrr3f);
			entity.setNurr1(nurr1f);
			entity.setNurr2(nurr2f);
			entity.setNurr3(nurr3f);
		}
		return list;
	}

	/**
	 * 月健康度数据
	 */
	public List<MonthhealthEntity> getMonthhealthList(BaseDTO dto) {
		return dao.getMonthhealthList(dto);
	}

	/**
	 * 启动留存
	 */
	public List<StartremainEntity> getStartremainList(BaseDTO dto) {
		List<StartremainEntity> list = dao.getStartremainList(dto);
		for (StartremainEntity entity : list) {
			double lrr1 = entity.getLrr1();
			double lrr2 = entity.getLrr2();
			double lrr3 = entity.getLrr3();
			double lurr1 = entity.getLurr1();
			double lurr2 = entity.getLurr2();
			double lurr3 = entity.getLurr3();

			DecimalFormat formatter = new DecimalFormat("##.##");
			double lrr1f = Double.valueOf(formatter.format(lrr1 * 100));
			double lrr2f = Double.valueOf(formatter.format(lrr2 * 100));
			double lrr3f = Double.valueOf(formatter.format(lrr3 * 100));
			double lurr1f = Double.valueOf(formatter.format(lurr1 * 100));
			double lurr2f = Double.valueOf(formatter.format(lurr2 * 100));
			double lurr3f = Double.valueOf(formatter.format(lurr3 * 100));

			if ("WEEKLY".equals(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
			entity.setLrr1(lrr1f);
			entity.setLrr2(lrr2f);
			entity.setLrr3(lrr3f);
			entity.setLurr1(lurr1f);
			entity.setLurr2(lurr2f);
			entity.setLurr3(lurr3f);
		}
		return list;
	}

	public List<UserlifecycleDecayEntity> getUserlifecycleDecayList(BaseDTO dto) {
		List<UserlifecycleDecayEntity> list = new ArrayList<UserlifecycleDecayEntity>();
		for (int i = 1; i < 5; i++) {
			UserlifecycleDecayEntity entity = new UserlifecycleDecayEntity();
			entity.setStartImei(200 * i);
			entity.setStartUser(100 * i);
			entity.setStatDate(DateUtil.parseDate(DateUtil
					.getDateOfXdaysAgo(5 - i), "yyyy-MM-dd"));
			list.add(entity);
		}
		return list;
		// return dao.getUserlifecycleDecayList(dto);
	}

	public List<UserlifecycleTotalEntity> getUserlifecycleTotalList(BaseDTO dto) {
		return dao.getUserlifecycleTotalList(dto);
	}

	/**
	 * 渠道概况
	 */
	public List<ChannelgeneralEntity> getChannelgeneralList(BaseDTO dto) {
		List<ChannelgeneralEntity> list = dao.getChannelgeneralList(dto);
		
		// 计算总量
		long totalnewimei = 0;
		long totalnewssoid = 0;
		long totalstartimei = 0;
		long totalstartssoid = 0;
		for (ChannelgeneralEntity entity : list) {
			long newimei = entity.getNewimei();
			long newssoid = entity.getNewssoid();
			long startimei = entity.getStartimei();
			long startssoid = entity.getStartssoid();
			
			totalnewimei += newimei;
			totalnewssoid += newssoid;
			totalstartimei += startimei;
			totalstartssoid += startssoid;
		}
		
		// 计算百分比并填充
		for (ChannelgeneralEntity entity : list) {
			long newimei = entity.getNewimei();
			long newssoid = entity.getNewssoid();
			long startimei = entity.getStartimei();
			long startssoid = entity.getStartssoid();
			
			float newimeiratio = totalnewimei == 0 ? 0 : Math.round(newimei * 10000 / (float) totalnewimei) / (float) 100;
			float newssoidratio = totalnewssoid == 0 ? 0 : Math.round(newssoid * 10000 / (float) totalnewssoid) / (float) 100;
			float startimeiratio = totalstartimei == 0 ? 0 : Math.round(startimei * 10000 / (float) totalstartimei) / (float) 100;
			float startssoidratio = totalstartssoid == 0 ? 0 : Math.round(startssoid * 10000 / (float) totalstartssoid) / (float) 100;
			
			entity.setNewimeiratio(newimeiratio);
			entity.setNewssoidratio(newssoidratio);
			entity.setStartimeiratio(startimeiratio);
			entity.setStartssoidratio(startssoidratio);
		}
		
		return list;
	}
	
	/**
	 * 渠道质量
	 */
	public List<ChannelqualityEntity> getChannelqualityList(BaseDTO dto) {
		List<ChannelqualityEntity> list = dao.getChannelqualityList(dto);
		
		for (ChannelqualityEntity entity : list) {
			float nirday1 = entity.getNirday1();
			float nirday7 = entity.getNirday7();
			float nirday30 = entity.getNirday30();
			
			nirday1 = Math.round(nirday1 * 10000 / (float) 100);
			nirday7 = Math.round(nirday7 * 10000 / (float) 100);
			nirday30 = Math.round(nirday30 * 10000 / (float) 100);
			
			entity.setNirday1(nirday1);
			entity.setNirday7(nirday7);
			entity.setNirday30(nirday30);
		}
		
		return list;
	}

	/**
	 * 页面跳转
	 */
	public List<PagejumpEntity> getPagejumpList(BaseDTO dto) {
		List<PagejumpEntity> list = new ArrayList<PagejumpEntity>();
		for (int i = 0; i < 4; i++) {
			PagejumpEntity entity = new PagejumpEntity();
			entity.setJumppage("page" + i);
			entity.setVimeiJumpRatio("50%");
			entity.setVjumpRatio("40%");
			list.add(entity);
		}
		return list;
		// return dao.getPagejumpList(dto);
	}

	/**
	 * 页面访问
	 */
	public List<PagevisitEntity> getPagevisitList(BaseDTO dto) {
		List<PagevisitEntity> list = new ArrayList<PagevisitEntity>();
		for (int i = 0; i < 4; i++) {
			PagevisitEntity entity = new PagevisitEntity();
			entity.setPage("pagevisit" + i);
			entity.setPageVisit("1200");
			entity.setPageVisitImei("2400");
			list.add(entity);
		}
		return list;
		// return dao.getPagevisitList(dto);
	}

	/**
	 * 自定义事件
	 */
	public List<SelfeventEntity> getSelfeventList(BaseDTO dto) {
		List<SelfeventEntity> list = new ArrayList<SelfeventEntity>();
		for (int i = 0; i < 5; i++) {
			SelfeventEntity entity = new SelfeventEntity();
			entity.setStatDate(DateUtil.parseDate(DateUtil
					.getDateOfXdaysAgo(5 - i), "yyyy-MM-dd"));
			entity.setEventActiveCnt(100 * i);
			entity.setEventActiveImei(200 * i);
			entity.setStartCnt(300 * i);
			entity.setStartImei(400 * i);
			list.add(entity);
		}
		return list;
		// return dao.getSelfeventList(dto);
	}

	/**
	 * 版本占比
	 */
	public List<ColumnValueEntity> getVersionzhanbiList(BaseDTO dto) {
		List<ColumnValueEntity> list = dao.getVersionzhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	/**
	 * 网络占比
	 */
	public List<ColumnValueEntity> getNetzhanbiList(BaseDTO dto) {
		List<ColumnValueEntity> list = dao.getNetzhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	/**
	 * 终端占比
	 */
	public List<ColumnValueEntity> getModulezhanbiList(BaseDTO dto) {
		List<ColumnValueEntity> list = dao.getModulezhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	/**
	 * 版本日明细
	 */
	public List<VersiondayEntity> getVersiondayList(BaseDTO dto) {
		List<VersiondayEntity> list = dao.getVersiondayList(dto);

		for (VersiondayEntity entity : list) {
			long allstartImei = entity.getAllstartImei();
			long allnewImei = entity.getAllnewImei();
			long startImei = entity.getStartImei();
			long newImei = entity.getNewImei();
			long updateImei = entity.getUpdateImei();

			float startRatio = Math.round(startImei * 10000
					/ (float) allstartImei)
					/ (float) 100;
			float newRatio = Math.round(newImei * 10000 / (float) allnewImei)
					/ (float) 100;
			if (updateImei < 0) {
				updateImei = 0;
			}

			entity.setStartRatio(startRatio);
			entity.setNewRatio(newRatio);
			entity.setUpdateImei(updateImei);
		}

		return list;
	}

	/**
	 * 网络日明细
	 */
	public List<NetdayEntity> getNetdayList(BaseDTO dto) {
		List<NetdayEntity> list = dao.getNetdayList(dto);

		for (NetdayEntity entity : list) {
			long allstartImei = entity.getAllstartImei();
			long allnewImei = entity.getAllnewImei();
			long startImei = entity.getStartImei();
			long newImei = entity.getNewImei();

			float startRatio = Math.round(startImei * 10000
					/ (float) allstartImei)
					/ (float) 100;
			float newRatio = Math.round(newImei * 10000 / (float) allnewImei)
					/ (float) 100;

			entity.setStartRatio(startRatio);
			entity.setNewRatio(newRatio);
		}

		return list;
	}

	/**
	 * 终端日明细
	 */
	public List<ModuledayEntity> getModuledayList(BaseDTO dto) {
		List<ModuledayEntity> list = dao.getModuledayList(dto);

		for (ModuledayEntity entity : list) {
			long allstartImei = entity.getAllstartImei();
			long allnewImei = entity.getAllnewImei();
			long startImei = entity.getStartImei();
			long newImei = entity.getNewImei();

			float startRatio = Math.round(startImei * 10000
					/ (float) allstartImei)
					/ (float) 100;
			float newRatio = Math.round(newImei * 10000 / (float) allnewImei)
					/ (float) 100;

			entity.setStartRatio(startRatio);
			entity.setNewRatio(newRatio);
		}

		return list;
	}

	/**
	 * 地区分析详情
	 */
	public List<ColumnValueEntity> getAreadetailList(BaseDTO dto) {
		List<ColumnValueEntity> list = new ArrayList<ColumnValueEntity>();
		for (int i = 1; i <= 5; i++) {
			ColumnValueEntity entity = new ColumnValueEntity();
			entity.setValue1("2013/05/" + (10 + i));
			entity.setValue2("800");
			entity.setValue3("0.2");
			list.add(entity);
		}

		return list;
		// return dao.getAreadetailList(dto);
	}

	/**
	 * 地区分析top10
	 */
	public List<ColumnValueEntity> getAreatop10List(BaseDTO dto) {
		List<ColumnValueEntity> list = new ArrayList<ColumnValueEntity>();
		for (int i = 1; i <= 5; i++) {
			ColumnValueEntity entity = new ColumnValueEntity();
			entity.setValue1("地区" + i);
			entity.setValue2("50");
			entity.setValue3("0.2");
			list.add(entity);
		}

		return list;
		// return dao.getAreatop10List(dto);
	}

	public List<ColumnValueEntity> getUseoftenStartcntList(BaseDTO dto) {
		List<ColumnValueEntity> list = new ArrayList<ColumnValueEntity>();
		for (int i = 1; i <= 5; i++) {
			ColumnValueEntity entity = new ColumnValueEntity();
			entity.setValue1("i");
			entity.setValue2("" + (1600 * i / 10));
			entity.setValue3("0.2");
			list.add(entity);
		}
		return list;
		// return dao.getUseoftenStartcntList(dto);
	}

	public List<ColumnValueEntity> getUseoftenUsetimeList(BaseDTO dto) {
		List<ColumnValueEntity> list = new ArrayList<ColumnValueEntity>();
		for (int i = 1; i <= 5; i++) {
			ColumnValueEntity entity = new ColumnValueEntity();
			entity.setValue1("jS");
			entity.setValue2("" + (100 * i / 10));
			entity.setValue3("0.2");
			list.add(entity);
		}
		return list;
		// return dao.getUseoftenUsetimeList(dto);
	}

	/**
	 * 错误分析-错误数日分布
	 */
	public List<ErrorEntity> getErrordailyList(BaseDTO dto) {
		List<ErrorEntity> list = dao.getErrordailyList(dto);
		// 启动数查询。。。
		List<DetaildayEntity> startList = dao.getDetaildayList(dto);
		
		// 匹配计算比率
		for (ErrorEntity entity : list) {
			Date statdate = entity.getStatDate();
			long errorcnt = entity.getErrorcnt();
			for (DetaildayEntity startentity : startList) {
				if(statdate.equals(startentity.getStatDate())){
					long starttimes = startentity.getStartTimes();
					
					float errorstartratio = starttimes == 0 ? 0 : Math.round(errorcnt * 100000 / starttimes) / (float)100;
					
					entity.setStartcnt(starttimes);
					entity.setErrorstartratio(errorstartratio);
				}
			}
		}
		
		return list;
	}

	/**
	 * 错误分析-错误详情
	 */
	public List<ErrorDetailEntity> getErrordetailList(BaseDTO dto) {
		return dao.getErrordetailList(dto);
	}
	
	/**
	 * 错误分析-查询错误详细信息记录数（用于分页）
	 */
	public Long getErrordetailCnt(BaseDTO dto) {
		return dao.getErrordetailCnt(dto);
	}

	/**
	 * 用户行为分析
	 */
	public List<UactionEntity> getUactionList(BaseDTO dto) {
		return dao.getUactionList(dto);
	}

	/**
	 * 渠道日明细
	 */
	public List<ChanneldailyEntity> getChanneldailyList(BaseDTO dto) {
		return dao.getChanneldailyList(dto);
	}

	/**
	 * 渠道健康度——新增留存
	 */
	public List<ChannelhealthEntity> getChannelhealthnirList(BaseDTO dto) {
		List<ChannelhealthEntity> list  = dao.getChannelhealthnirList(dto);

		for (ChannelhealthEntity entity : list) {
			entity.setImei1x((float)(Math.round((entity.getImei1x()*10000))) / (float)100);
			entity.setImei2x((float)(Math.round((entity.getImei2x()*10000))) / (float)100);
			entity.setImei3x((float)(Math.round((entity.getImei3x()*10000))) / (float)100);
			entity.setSsoid1x((float)(Math.round((entity.getSsoid1x()*10000))) / (float)100);
			entity.setSsoid2x((float)(Math.round((entity.getSsoid2x()*10000))) / (float)100);
			entity.setSsoid3x((float)(Math.round((entity.getSsoid3x()*10000))) / (float)100);

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	/**
	 * 渠道健康度——启动留存
	 */
	public List<ChannelhealthEntity> getChannelhealthsirList(BaseDTO dto) {
		List<ChannelhealthEntity> list  = dao.getChannelhealthsirList(dto);

		for (ChannelhealthEntity entity : list) {
			entity.setImei1x((float)(Math.round((entity.getImei1x()*10000))) / (float)100);
			entity.setImei2x((float)(Math.round((entity.getImei2x()*10000))) / (float)100);
			entity.setImei3x((float)(Math.round((entity.getImei3x()*10000))) / (float)100);
			entity.setSsoid1x((float)(Math.round((entity.getSsoid1x()*10000))) / (float)100);
			entity.setSsoid2x((float)(Math.round((entity.getSsoid2x()*10000))) / (float)100);
			entity.setSsoid3x((float)(Math.round((entity.getSsoid3x()*10000))) / (float)100);

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	/**
	 * 下载日明细
	 */
	public List<DowndayEntity> getDowndayList(BaseDTO dto) {
		return dao.getDowndayList(dto);
	}

	/**
	 * 下载月明细
	 */
	public List<DownmonthEntity> getDownmonthList(BaseDTO dto) {
		return dao.getDownmonthList(dto);
	}

	/**
	 * 下载周明细
	 */
	public List<DownweekEntity> getDownweekList(BaseDTO dto) {
		List<DownweekEntity> list = dao.getDownweekList(dto);

		for (int i = 0; i < list.size(); i++) {
			DownweekEntity entity = (DownweekEntity) list.get(i);
			Date statdate = entity.getStatDate();
			Date enddate = DateUtil.AddDays(statdate, 6);

			String weekStr = String.format("%s~%s", DateUtil.formatDate(statdate,
					"MM-dd"), DateUtil.formatDate(enddate, "MM-dd"));
			entity.setWeekStr(weekStr);
			list.set(i, entity);
		}

		return list;
	}

	/**
	 * 下载终端占比
	 */
	public List<ColumnValueEntity> getDownmobilezhanbiList(BaseDTO dto) {
		List<ColumnValueEntity> list = dao.getDownmobilezhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	/**
	 * 下载渠道占比
	 */
	public List<ColumnValueEntity> getDownqudaozhanbiList(BaseDTO dto) {
		List<ColumnValueEntity> list = dao.getDownqudaozhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	/**
	 * 下载版本占比
	 */
	public List<ColumnValueEntity> getDownversionzhanbiList(BaseDTO dto) {
		List<ColumnValueEntity> list = dao.getDownversionzhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	/**
	 * 下载留存
	 */
	public List<DownremainEntity> getDownremainList(BaseDTO dto) {
		List<DownremainEntity> list = dao.getDownremainList(dto);//查询集
        List<DownremainEntity> result = new ArrayList<DownremainEntity>();//结果集
		
		DownremainEntity record = null;
		String tagStr = null;
		for (int i = 0; i < list.size(); i++) {
			DownremainEntity entity = list.get(i);
			if (null == tagStr) {
				tagStr = DateUtil.formatDate(entity.getStatDate(), "yyyy-MM-dd");
			}
			
			if (null == record) {
				record = new DownremainEntity();
			}
			
			// 下一条记录到来，上一条记录插入
			if ( !tagStr.equals(DateUtil.formatDate(entity.getStatDate(), "yyyy-MM-dd"))) {
				float drr1 = record.getDownimei() == 0 ? 0 : 
					Math.round(record.getDr1() * 10000 / record.getDownimei()) / (float)100;
				float drr2 = record.getDownimei() == 0 ? 0 : 
					Math.round(record.getDr2() * 10000 / record.getDownimei()) / (float)100;
				float drr3 = record.getDownimei() == 0 ? 0 : 
					Math.round(record.getDr3() * 10000 / record.getDownimei()) / (float)100;
				float ndrr1 = record.getNewdownimei() == 0 ? 0 : 
					Math.round(record.getNdr1() * 10000 / record.getNewdownimei()) / (float)100;
				float ndrr2 = record.getNewdownimei() == 0 ? 0 : 
					Math.round(record.getNdr2() * 10000 / record.getNewdownimei()) / (float)100;
				float ndrr3 = record.getNewdownimei() == 0 ? 0 : 
					Math.round(record.getNdr3() * 10000 / record.getNewdownimei()) / (float)100;
				
				
				record.setDrr1(drr1);
				record.setDrr2(drr2);
				record.setDrr3(drr3);
				record.setNdrr1(ndrr1);
				record.setNdrr2(ndrr2);
				record.setNdrr3(ndrr3);
				
				result.add(record);
				
				record = new DownremainEntity();
				tagStr = DateUtil.formatDate(entity.getStatDate(), "yyyy-MM-dd");
			} 
			
			// 取查询集数据
			Date statDate = entity.getStatDate();
			Date statEndDate = entity.getStatEndDate();
			long downimei = entity.getDownimei();
			long newdownimei = entity.getNewdownimei();

			String statDateStr = DateUtil.formatDate(statDate, "yyyy-MM-dd");
			String day1after = null;
			String day2after = null;
			String day3after = null;
			String statEndDateStr = DateUtil.formatDate(statEndDate,"yyyy-MM-dd");
			if (Constants.DAILY.equalsIgnoreCase(dto.getLidu())) {
				day1after = DateUtil.getDateOfXdaysAgo(statDate, -1, "yyyy-MM-dd");// 1天后
				day2after = DateUtil.getDateOfXdaysAgo(statDate, -2, "yyyy-MM-dd");// 2天后
				day3after = DateUtil.getDateOfXdaysAgo(statDate, -3, "yyyy-MM-dd");// 3天后
			} else if (Constants.WEEKLY.equalsIgnoreCase(dto.getLidu())) {
				day1after = DateUtil.getDateOfXdaysAgo(statDate, -7, "yyyy-MM-dd");// 1周后
				day2after = DateUtil.getDateOfXdaysAgo(statDate, -14, "yyyy-MM-dd");// 2周后
				day3after = DateUtil.getDateOfXdaysAgo(statDate, -21, "yyyy-MM-dd");// 3周后
			} else if (Constants.MONTHLY.equalsIgnoreCase(dto.getLidu())) {
				day1after = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(statDate, -1), "yyyy-MM-dd");// 1月后
				day2after = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(statDate, -2), "yyyy-MM-dd");// 2月后
				day3after = DateUtil.formatDate(DateUtil.getFirstDayOfXmonthAgo(statDate, -3), "yyyy-MM-dd");// 3月后
			}
			
			
			// 一条记录填入
			if (null == record.getStatDate()) {
				record.setStatDate(statDate);
				if (Constants.WEEKLY.equalsIgnoreCase(dto.getLidu())) {//周留存要填充周的结尾时间
					statEndDate = DateUtil.parseDate(DateUtil.getDateOfXdaysAgo(statDate, -6, "yyyy-MM-dd"), "yyyy-MM-dd");
					record.setStatEndDate(statEndDate);
				}
			}
			
			if (statDateStr.equals(statEndDateStr)) {// 下载量和新增下载量
				record.setDownimei(downimei);
				record.setNewdownimei(newdownimei);
			} else if (day1after.equals(statEndDateStr)) { // 1日/周/月留存
				record.setDr1(downimei);
				record.setNdr1(newdownimei);
			} else if (day2after.equals(statEndDateStr)) { // 2日/周/月留存
				record.setDr2(downimei);
				record.setNdr2(newdownimei);
			} else if (day3after.equals(statEndDateStr)) { // 3日/周/月留存
				record.setDr3(downimei);
				record.setNdr3(newdownimei);
			}
			
			// 最后一条记录
			if (i == list.size()-1) { 
				float drr1 = record.getDownimei() == 0 ? 0 : 
					Math.round(record.getDr1() * 10000 / record.getDownimei()) / (float)100;
				float drr2 = record.getDownimei() == 0 ? 0 : 
					Math.round(record.getDr2() * 10000 / record.getDownimei()) / (float)100;
				float drr3 = record.getDownimei() == 0 ? 0 : 
					Math.round(record.getDr3() * 10000 / record.getDownimei()) / (float)100;
				float ndrr1 = record.getNewdownimei() == 0 ? 0 : 
					Math.round(record.getNdr1() * 10000 / record.getNewdownimei()) / (float)100;
				float ndrr2 = record.getNewdownimei() == 0 ? 0 : 
					Math.round(record.getNdr2() * 10000 / record.getNewdownimei()) / (float)100;
				float ndrr3 = record.getNewdownimei() == 0 ? 0 : 
					Math.round(record.getNdr3() * 10000 / record.getNewdownimei()) / (float)100;
				
				
				record.setDrr1(drr1);
				record.setDrr2(drr2);
				record.setDrr3(drr3);
				record.setNdrr1(ndrr1);
				record.setNdrr2(ndrr2);
				record.setNdrr3(ndrr3);
				
				result.add(record);
			}
		}
		return result;
	}

	/**
	 * 单个资源下载CPT
	 */
	public List<DownsourceCPTEntity> getDownsourceCPTList(BaseDTO dto) {
		return dao.getDownsourceCPTList(dto);
	}

	/**
	 * 单个资源下载
	 */
	public List<DownsourceEntity> getDownsourceList(BaseDTO dto) {
		return dao.getDownsourceList(dto);
	}

	/**
	 * 下载累计
	 */
	public List<DownleijiEntity> getDownleijiList(BaseDTO dto) {
		return dao.getDownleijiList(dto);
	}

	/**
	 * 时段分析
	 */
	public List<HHStartEntity> listHHStart(BaseDTO dto) {
		try{
			return dao.listHHStart(dto);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return null;
	}

	/**
	 * 需求提交
	 */
	public int createRequest(RequestjobDTO dto) {
		return dao.createRequest(dto);
	}

	/**
	 * 错误机型分布
	 */
	public List<ErrorDetailEntity> getErrormodeldistribute(BaseDTO dto) {
		return dao.getErrormodeldistribute(dto);
	}

	/**
	 * 30天留存
	 */
	public List<Remain30daysEntity> getRemain30daysList(BaseDTO dto) {
        List<Remain30daysEntity> list = dao.getRemain30daysList(dto);
        List<Remain30daysEntity> result = new ArrayList<Remain30daysEntity>();
        
        String tagStr = null;
        for (Remain30daysEntity entity : list) {
			Date statDate = entity.getStatDate();
			String statDateStr = DateUtil.formatDate(statDate, "yyyy-MM-dd");
			
			if (null == tagStr || !tagStr.equals(statDateStr)) {
				tagStr = statDateStr;
				
				String day1Str = DateUtil.getDateOfXdaysAgo(statDate, -1, "yyyy-MM-dd");// 1天后
				String day2Str = DateUtil.getDateOfXdaysAgo(statDate, -2, "yyyy-MM-dd");// 2天后
				String day3Str = DateUtil.getDateOfXdaysAgo(statDate, -3, "yyyy-MM-dd");// 3天后
				String day4Str = DateUtil.getDateOfXdaysAgo(statDate, -4, "yyyy-MM-dd");// 4天后
				String day5Str = DateUtil.getDateOfXdaysAgo(statDate, -5, "yyyy-MM-dd");// 5天后
				String day6Str = DateUtil.getDateOfXdaysAgo(statDate, -6, "yyyy-MM-dd");// 6天后
				String day7Str = DateUtil.getDateOfXdaysAgo(statDate, -7, "yyyy-MM-dd");// 7天后
				String day14Str = DateUtil.getDateOfXdaysAgo(statDate, -14, "yyyy-MM-dd");// 14天后
				String day30Str = DateUtil.getDateOfXdaysAgo(statDate, -30, "yyyy-MM-dd");// 30天后
				
				Remain30daysEntity item = new Remain30daysEntity();
				item.setStatDate(statDate);
				long remainbegin = 0;
				for (Remain30daysEntity enty : list) {
					Date date = enty.getStatDate();
					String dateStr = DateUtil.formatDate(date, "yyyy-MM-dd");
					
					if (null != dateStr && tagStr.equals(dateStr)) {
						Date remainDate = enty.getStatEndDate();
						String remainDateStr = DateUtil.formatDate(remainDate, "yyyy-MM-dd");
						long remain = enty.getRemain();
						
						if (remainDateStr.equals(dateStr)) {
							item.setRemain(remain);
							remainbegin = remain;
						} else if (remainDateStr.equals(day1Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio1day(ratio);
						} else if (remainDateStr.equals(day2Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio2day(ratio);
						} else if (remainDateStr.equals(day3Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio3day(ratio);
						} else if (remainDateStr.equals(day4Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio4day(ratio);
						} else if (remainDateStr.equals(day5Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio5day(ratio);
						} else if (remainDateStr.equals(day6Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio6day(ratio);
						} else if (remainDateStr.equals(day7Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio7day(ratio);
						} else if (remainDateStr.equals(day14Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio14day(ratio);
						} else if (remainDateStr.equals(day30Str)) {
							float ratio = remainbegin == 0 ? 0 : Math.round(remain * 10000 / remainbegin) / (float)100;
							item.setRemain1day(remain);
							item.setRemainratio30day(ratio);
						} 
					}
				}
				
				result.add(item);
			}
		}
        return result;
	}
}
