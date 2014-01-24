package com.nearme.statistics.dao.app.zjb.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.zjb.ZjbChannelinfoDao;
import com.nearme.statistics.dto.app.zjb.ZJBChannelinfoDTO;
import com.nearme.statistics.model.zjbsetting.ZJBChannelinfoEntity;

public class ZjbChannelinfoDaoImpl implements ZjbChannelinfoDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 添加
	 */
	public void add(ZJBChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.zjb.ZjbChannelinfoDao." +
					"add", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 删除
	 */
	public void delete(ZJBChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.zjb.ZjbChannelinfoDao." +
					"delete", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 查询
	 */
	public List<ZJBChannelinfoEntity> getZJBChnnelinfoList(ZJBChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.zjb.ZjbChannelinfoDao."
					+ "getZJBChnnelinfoList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 更新
	 */
	public void update(ZJBChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.zjb.ZjbChannelinfoDao." +
					"update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
