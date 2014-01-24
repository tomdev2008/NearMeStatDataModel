package com.nearme.statistics.dao.sys.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nearme.statistics.dao.sys.PersonInfoDao;
import com.nearme.statistics.model.sys.admin.Admin;

public class PersonInfoDaoImpl implements PersonInfoDao {
	public static Logger logger = LoggerFactory
	.getLogger(PersonInfoDaoImpl.class);
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	
	public int updateUserInfo(Admin admin) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.update("com.nearme.statistics.dao.sys.AdminDao.updateAdminInfo", admin);
		}catch(Exception e){
			logger.error(e.getMessage());
			return -1;
		} finally {
			sqlSession.close();
		}
	}

	public int updateUserPasswd(Admin admin) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.update("com.nearme.statistics.dao.sys.AdminDao.updateAdminPasswd",admin);
		}catch(Exception e){
			logger.error(e.getMessage());
			return -1;
		}finally {
			sqlSession.close();
		}
	}

}
