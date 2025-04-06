package com.shield.chaosshield.dao;

import com.shield.chaosshield.pojo.ExperimentTest;

import java.util.List;

public interface ExperimentTestDao {
    Integer createTable();
    Integer createTrigger();
    List<ExperimentTest> selectAll();
    ExperimentTest selectById(Integer id);
    Integer deleteById(Integer id);
    Integer update(ExperimentTest test);
    Integer insert(ExperimentTest test);
}
