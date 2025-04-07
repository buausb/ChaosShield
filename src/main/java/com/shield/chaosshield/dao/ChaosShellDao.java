package com.shield.chaosshield.dao;

import com.shield.chaosshield.pojo.ChaosShell;

import java.util.List;

public interface ChaosShellDao {
    Integer createTable();

    List<ChaosShell> selectAll();

    ChaosShell selectById(Integer id);

    Integer deleteById(Integer id);

    Integer insert(ChaosShell shell);

    Integer update(ChaosShell shell);

    ChaosShell selectByName(String name);
}
