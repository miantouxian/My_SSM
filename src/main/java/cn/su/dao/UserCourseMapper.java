package cn.su.dao;

import cn.su.pojo.Course;
import cn.su.pojo.UserCourse;

import java.util.List;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    UserCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);

    List<UserCourse> selectByUsername(String username);

    List<UserCourse> selectAll();

    List<UserCourse> selectUsercourse(UserCourse userCourse);

    int deleteCourseByCoursenamelist(List<String> coursename);
}