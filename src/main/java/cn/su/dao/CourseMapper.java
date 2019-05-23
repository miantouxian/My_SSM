package cn.su.dao;

import cn.su.pojo.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectCourse(Course record);

    List<Course> groupByCoursetype();

    Course selectByCoursename(String coursename);

    int deleteCourseByIdlist(List<Integer> delid);

    List<Course> selectCourseByIdlist(List<Integer> delid);

}