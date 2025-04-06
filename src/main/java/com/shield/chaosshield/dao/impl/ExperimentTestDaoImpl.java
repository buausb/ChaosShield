package com.shield.chaosshield.dao.impl;

import com.shield.chaosshield.common.MyBatisUtil;
import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.pojo.ExperimentTest;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ExperimentTestDaoImpl implements ExperimentTestDao {
    @Override
    public Integer createTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentTestDao mapper = session.getMapper(ExperimentTestDao.class);
            Integer result = mapper.createTable();
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer createTrigger() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentTestDao mapper = session.getMapper(ExperimentTestDao.class);
            Integer result = mapper.createTrigger();
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ExperimentTest> selectAll() {
        List<ExperimentTest> list = null;

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentTestDao mapper = session.getMapper(ExperimentTestDao.class);
            list = mapper.selectAll();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ExperimentTest selectById(Integer id) {
        ExperimentTest test = null;

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            // Person person = session.selectOne("com.mouday.dao.PersonDao.selectById", 1);
            // 等价于

            ExperimentTestDao mapper = session.getMapper(ExperimentTestDao.class);
            test = mapper.selectById(id);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return test;
    }

    @Override
    public Integer deleteById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentTestDao mapper = session.getMapper(ExperimentTestDao.class);
            Integer result = mapper.deleteById(id);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer update(ExperimentTest test) {
        return 0;
    }

    @Override
    public Integer insert(ExperimentTest test) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ExperimentTestDao mapper = session.getMapper(ExperimentTestDao.class);
            Integer result = mapper.insert(test);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
