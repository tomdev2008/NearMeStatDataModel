package com.nearme.statistics.service.app.common;

import java.util.List;

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

public interface CommonService {
	//日明细数据
	public List<DetaildayEntity> getDetaildayList(BaseDTO dto);

	//周明细数据
	public List<DetailweekEntity> getDetailweekList(BaseDTO dto);

	//月明细数据
	public List<DetailmonthEntity> getDetailmonthList(BaseDTO dto);

	//累计数据
	public List<GrandtotalEntity> getGrandTotalList(BaseDTO dto);

	//月健康度数据
	public List<MonthhealthEntity> getMonthhealthList(BaseDTO dto);

	//活跃天数
	public List<ActivedaysEntity> getActivedaysList(BaseDTO dto);

	//新增留存
	public List<IncreaseremainEntity> getIncreaseremainList(BaseDTO dto);

	//启动留存
	public List<StartremainEntity> getStartremainList(BaseDTO dto);

	//用户生命周期
	public List<UserlifecycleTotalEntity> getUserlifecycleTotalList(BaseDTO dto);
	public List<UserlifecycleDecayEntity> getUserlifecycleDecayList(BaseDTO dto);

	// 渠道概况
	public List<ChannelgeneralEntity> getChannelgeneralList(BaseDTO dto);
	// 渠道质量
	public List<ChannelqualityEntity> getChannelqualityList(BaseDTO dto);
	// 渠道日明细
	public List<ChanneldailyEntity> getChanneldailyList(BaseDTO dto);
	// 渠道健康度--新增留存
	public List<ChannelhealthEntity> getChannelhealthnirList(BaseDTO dto);
	// 渠道健康度--启动留存
	public List<ChannelhealthEntity> getChannelhealthsirList(BaseDTO dto);

	//页面访问分析
	public List<PagevisitEntity> getPagevisitList(BaseDTO dto);
	public List<PagejumpEntity> getPagejumpList(BaseDTO dto);

	//自定义事件
	public List<SelfeventEntity> getSelfeventList(BaseDTO dto);

	//地区分析
	public List<ColumnValueEntity> getAreatop10List(BaseDTO dto);
	public List<ColumnValueEntity> getAreadetailList(BaseDTO dto);

	//使用频率时长分析
	public List<ColumnValueEntity> getUseoftenStartcntList(BaseDTO dto);
	public List<ColumnValueEntity> getUseoftenUsetimeList(BaseDTO dto);

	//错误分析
	public List<ErrorEntity> getErrordailyList(BaseDTO dto);
	public List<ErrorDetailEntity> getErrordetailList(BaseDTO dto);
	public Long getErrordetailCnt(BaseDTO dto);//查询错误详细信息记录数（用于分页）
	public List<ErrorDetailEntity> getErrormodeldistribute(BaseDTO dto);//错误机型分布

	//版本占比
	public List<ColumnValueEntity> getVersionzhanbiList(BaseDTO dto);
	//网络占比
	public List<ColumnValueEntity> getNetzhanbiList(BaseDTO dto);
	//终端占比
	public List<ColumnValueEntity> getModulezhanbiList(BaseDTO dto);

	//版本日明细
	public List<VersiondayEntity> getVersiondayList(BaseDTO dto);
	//网络日明细
	public List<NetdayEntity> getNetdayList(BaseDTO dto);
	//终端日明细
	public List<ModuledayEntity> getModuledayList(BaseDTO dto);

	//用户行为分析
	public List<UactionEntity> getUactionList(BaseDTO dto);

	//下载日明细
	public List<DowndayEntity> getDowndayList(BaseDTO dto);
	//下载周明细
	public List<DownweekEntity> getDownweekList(BaseDTO dto);
	//下载月明细
	public List<DownmonthEntity> getDownmonthList(BaseDTO dto);
	//下载渠道占比
	public List<ColumnValueEntity> getDownqudaozhanbiList(BaseDTO dto);
	//下载终端占比
	public List<ColumnValueEntity> getDownmobilezhanbiList(BaseDTO dto);
	//下载版本占比
	public List<ColumnValueEntity> getDownversionzhanbiList(BaseDTO dto);
	//下载留存
	public List<DownremainEntity> getDownremainList(BaseDTO dto);
	//单个资源下载
	public List<DownsourceEntity> getDownsourceList(BaseDTO dto);
	public List<DownsourceCPTEntity> getDownsourceCPTList(BaseDTO dto);
	//下载累计
	public List<DownleijiEntity> getDownleijiList(BaseDTO dto);

	//时段分析
	public List<HHStartEntity> listHHStart(BaseDTO dto);

	//需求提交
	public int createRequest(RequestjobDTO dto);
	
	//30天留存
	public List<Remain30daysEntity> getRemain30daysList(BaseDTO dto);
}
