package com.shield.chaosshield.dao.impl;

import com.shield.chaosshield.common.MyBatisUtil;
import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.pojo.ChaosShell;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChaosShellDaoImpl implements ChaosShellDao {
    @Override
    public Integer createTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ChaosShellDao mapper = session.getMapper(ChaosShellDao.class);
            Integer result = mapper.createTable();
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ChaosShell> selectAll() {
        List<ChaosShell> list = new ArrayList<>();
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ChaosShellDao mapper = session.getMapper(ChaosShellDao.class);
            list = mapper.selectAll();
            session.commit();
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ChaosShell selectById(Integer id) {
        ChaosShell result = new ChaosShell();
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ChaosShellDao mapper = session.getMapper(ChaosShellDao.class);
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
            ChaosShellDao mapper = session.getMapper(ChaosShellDao.class);
            Integer result = mapper.deleteById(id);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer insert(ChaosShell shell) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ChaosShellDao mapper = session.getMapper(ChaosShellDao.class);
            Integer result = mapper.insert(shell);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer update(ChaosShell shell) {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            ChaosShellDao mapper = session.getMapper(ChaosShellDao.class);
            Integer result = mapper.update(shell);
            session.commit();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
