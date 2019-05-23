package cn.su.controller;

import cn.su.dao.CourseMapper;
import cn.su.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CourseMapper courseMapper ;

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String mainController(HttpServletRequest request){
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "main";
    }


}
