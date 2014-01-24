package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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

/**
 * 公共Dao
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-17
 */
public class CommonDaoImpl implements CommonDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 日明细数据
	 */
	public List<DetaildayEntity> getDetaildayList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getDetaildayList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 月明细数据
	 */
	public List<DetailmonthEntity> getDetailmonthList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getDetailmonthList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 周明细数据
	 */
	public List<DetailweekEntity> getDetailweekList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getDetailweekList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 累计数据
	 */
	public List<GrandtotalEntity> getGrandTotalList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getGrandTotalList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 活跃天数
	 */
	public List<ActivedaysEntity> getActivedaysList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getActivedaysList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 新增留存
	 */
	public List<IncreaseremainEntity> getIncreaseremainList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getIncreaseremainList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 月健康度数据
	 */
	public List<MonthhealthEntity> getMonthhealthList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getMonthhealthList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 启动留存
	 */
	public List<StartremainEntity> getStartremainList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getStartremainList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户生命周期-衰减
	 */
	public List<UserlifecycleDecayEntity> getUserlifecycleDecayList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getUserlifecycleDecayList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户生命周期-整体信息
	 */
	public List<UserlifecycleTotalEntity> getUserlifecycleTotalList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getUserlifecycleTotalList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 渠道概况
	 */
	public List<ChannelgeneralEntity> getChannelgeneralList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getChannelgeneralList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 渠道质量
	 */
	public List<ChannelqualityEntity> getChannelqualityList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getChannelqualityList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 渠道日明细
	 */
	public List<ChanneldailyEntity> getChanneldailyList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getChanneldailyList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 渠道健康度——新增留存
	 */
	public List<ChannelhealthEntity> getChannelhealthnirList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getChannelhealthnirList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 渠道健康度——启动留存
	 */
	public List<ChannelhealthEntity> getChannelhealthsirList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getChannelhealthsirList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 页面访问分析-页面跳转明细
	 */
	public List<PagejumpEntity> getPagejumpList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getPagejumpList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 页面访问分析
	 */
	public List<PagevisitEntity> getPagevisitList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getPagevisitList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 自定义事件
	 */
	public List<SelfeventEntity> getSelfeventList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getSelfeventList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 版本占比
	 */
	public List<ColumnValueEntity> getVersionzhanbiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getVersionzhanbiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 网络占比
	 * @param dto
	 * @return
	 */
	public List<ColumnValueEntity> getNetzhanbiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getNetzhanbiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 终端占比
	 * @param dto
	 * @return
	 */
	public List<ColumnValueEntity> getModulezhanbiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModulezhanbiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 版本日明细
	 * @param dto
	 * @return
	 */
	public List<VersiondayEntity> getVersiondayList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getVersiondayList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 网络日明细
	 * @param dto
	 * @return
	 */
	public List<NetdayEntity> getNetdayList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getNetdayList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 终端日明细
	 * @param dto
	 * @return
	 */
	public List<ModuledayEntity> getModuledayList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getModuledayList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getAreadetailList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getAreadetailList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getAreatop10List(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getAreatop10List",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getUseoftenStartcntList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getUseoftenStartcntList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getUseoftenUsetimeList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getUseoftenUsetimeList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ErrorEntity> getErrordailyList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getErrordailyList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ErrorDetailEntity> getErrordetailList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getErrordetailList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public Long getErrordetailCnt(BaseDTO dto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Object obj = sqlSession.selectOne("com.nearme.statistics.dao.app.common.CommonDao." +
					"getErrordetailCnt",dto);
			if (obj != null) {
				return (Long) obj;
			} else {
				return 0l;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<UactionEntity> getUactionList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.getUactionList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<DowndayEntity> getDowndayList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDowndayList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<DownmonthEntity> getDownmonthList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownmonthList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<DownweekEntity> getDownweekList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownweekList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getDownmobilezhanbiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownmobilezhanbiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getDownqudaozhanbiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownqudaozhanbiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getDownversionzhanbiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownversionzhanbiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<DownremainEntity> getDownremainList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownremainList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 单个资源下载CPT
	 */
	public List<DownsourceCPTEntity> getDownsourceCPTList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownsourceCPTList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 单个资源下载
	 */
	public List<DownsourceEntity> getDownsourceList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownsourceList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 时段分析
	 */
	public List<HHStartEntity> listHHStart(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao.listHHStart", dto);
	}

	/**
	 * 需求提交
	 */
	public int createRequest(RequestjobDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.insert("com.nearme.statistics.dao.app.common.CommonDao." +
					"createRequest",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	/**
	 * 下载累计
	 */
	public List<DownleijiEntity> getDownleijiList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getDownleijiList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 错误机型分布
	 */
	public List<ErrorDetailEntity> getErrormodeldistribute(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getErrormodeldistribute",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 30天留存
	 */
	public List<Remain30daysEntity> getRemain30daysList(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.CommonDao." +
					"getRemain30daysList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
