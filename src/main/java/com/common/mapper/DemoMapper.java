package com.common.mapper;

import com.common.core.MyBatisRepository;
import com.common.entity.Demo;

@MyBatisRepository
public interface DemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertSelective(Demo record);

    Demo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}