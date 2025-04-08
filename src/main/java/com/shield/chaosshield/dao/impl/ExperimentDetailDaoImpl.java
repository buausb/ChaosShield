package com.shield.chaosshield.dao.impl;

import com.shield.chaosshield.common.MyBatisUtil;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.pojo.ExperimentDetail;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExperimentDetailDaoImpl implements ExperimentDetailDao {
    @Override
    public Integer createTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            Integer result = mapper.createTable();
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer createTrigger() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            Integer result = mapper.createTrigger();
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ExperimentDetail> selectAll() {
        List<ExperimentDetail> list = new ArrayList<>();
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            list = mapper.selectAll();
            session.commit();
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ExperimentDetail selectById(Integer id) {
        ExperimentDetail result = new ExperimentDetail();
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            result = mapper.selectById(id);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            Integer result = mapper.deleteById(id);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer insert(ExperimentDetail detail) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            Integer result = mapper.insert(detail);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer update(ExperimentDetail detail) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            Integer result = mapper.update(detail);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ExperimentDetail> selectByTestId(Integer testId) {
        List<ExperimentDetail> list = new ArrayList<>();
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            list = mapper.selectByTestId(testId);
            session.commit();
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer deleteByTestId(Integer testId) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentDetailDao mapper = session.getMapper(ExperimentDetailDao.class);
            Integer result = mapper.deleteByTestId(testId);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
