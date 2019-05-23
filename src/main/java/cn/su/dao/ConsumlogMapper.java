package cn.su.dao;

import cn.su.pojo.ConsumParam;
import cn.su.pojo.Consumlog;

import java.util.List;


public interface ConsumlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Consumlog record);

    int insertSelective(Consumlog record);

    Consumlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consumlog record);

    int updateByPrimaryKey(Consumlog record);


    int selectCount(ConsumParam consumParam);

    List<Consumlog> findConsumlogByPage(ConsumParam consumParam);

    int delteConsumlogById(List<Integer> delid);

    int delImghrefValue(Integer id);

}