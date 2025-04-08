package com.shield.chaosshield.dao;

import com.shield.chaosshield.pojo.ExperimentDetail;

import java.util.List;

public interface ExperimentDetailDao {

    Integer createTable();

    Integer createTrigger();

    List<ExperimentDetail> selectAll();

    ExperimentDetail selectById(Integer id);

    Integer deleteById(Integer id);

    Integer insert(ExperimentDetail detail);

    Integer update(ExperimentDetail detail);

    List<ExperimentDetail> selectByTestId(Integer testId);

    Integer deleteByTestId(Integer testId);

}
