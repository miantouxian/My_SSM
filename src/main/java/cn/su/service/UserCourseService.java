package cn.su.service;

import cn.su.pojo.Course;

import java.util.List;

public interface UserCourseService {
    List<Course> handleCourselist(Course course,String username);
}
