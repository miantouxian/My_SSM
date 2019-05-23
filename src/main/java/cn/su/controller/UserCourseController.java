package cn.su.controller;

import cn.su.dao.CourseMapper;
import cn.su.dao.UserCourseMapper;
import cn.su.pojo.Course;
import cn.su.pojo.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserCourseController {

    @Autowired
    private UserCourseMapper userCourseMapper;
    @Autowired
    private CourseMapper courseMapper;

    @RequestMapping(value = "queryUserCourse",method = RequestMethod.GET)
    public ModelAndView queryUserCourse(){
        List<UserCourse> uslist = userCourseMapper.selectAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("uslist",uslist);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryUserCourse");
        return modelAndView;
    }


}
