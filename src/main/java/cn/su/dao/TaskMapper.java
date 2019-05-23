package cn.su.dao;

import cn.su.pojo.Task;

import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> selectAllTask();

    Task selectTaskById(Integer id);

    List<Task> selectTaskByOfferor(String offeror);

    List<Task> selectTaskByOfferee(String offeree);
}