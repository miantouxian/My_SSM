package cn.su.controller;

import cn.su.dao.CourseMapper;
import cn.su.dao.UserCourseMapper;
import cn.su.pojo.Course;
import cn.su.pojo.User;
import cn.su.pojo.UserCourse;
import cn.su.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Resource
    private UserCourseService userCourseService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping(value = "/addCourse",method = RequestMethod.GET)
    public String returnAddCourse(){
        return "addCourse";
    }
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public ModelAndView addCourse(@ModelAttribute Course course){

        ModelAndView modelAndView = new ModelAndView();
        String msg = null;
        List<Course> courses = courseMapper.selectCourse(course);
        if(courses.size() == 0) {
            int i = courseMapper.insert(course);
            if(i<0){
                msg = "课程添加失败";
            }else{
                msg = "课程添加成功";
            }
        }else {
            msg = "不要重复添加课程";
        }

        List<Course> courselist = courseMapper.selectCourse(course);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("msg",msg);
        modelAndView.addObject("courselist",courselist);
        modelAndView.addObject("alltype",alltype);
        modelAndView.setViewName("queryCourse");
        return modelAndView;
    }


    @RequestMapping(value = "/queryCourse",method = RequestMethod.GET)
    public String returnQueryCourse(HttpServletRequest request){
        Course course = new Course();
        List<Course> courselist = courseMapper.selectCourse(course);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("courselist", courselist);
        request.setAttribute("alltype",alltype);
        return "queryCourse";
    }
    @RequestMapping(value = "/queryCourse", method = RequestMethod.POST)
    public String queryCourse(@ModelAttribute Course courseparam,HttpServletRequest request){
        List<Course> courselist = courseMapper.selectCourse(courseparam);
        request.setAttribute("courselist",courselist);
        request.setAttribute("courseparam",courseparam);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "queryCourse";
    }

    @RequestMapping(value = "/chooseCourse",method = RequestMethod.GET)
    public String returnChooseCourse(@RequestParam String coursetype, @RequestParam() String coursename,
                                     HttpServletRequest request, HttpSession session) throws Exception{
        System.out.println("chooseCourse"+coursename);
        String msg = null;
        Cookie[] cookies = request.getCookies();
        User user = (User) session.getAttribute("user");
        //有课程名称才处理
            if (user != null){
                UserCourse userCourse = new UserCourse();
                String username = user.getUsername();
                userCourse.setUsername(username);
                if(coursename != null && cookies != null){
                    if(!coursename.equals("")){
                        userCourse.setCoursename(coursename);
                        List<UserCourse> userCourseList = userCourseMapper.selectUsercourse(userCourse);
                        Course courses = courseMapper.selectByCoursename(userCourse.getCoursename());
                        System.out.println("重复添加"+userCourseList.size());
                        if(userCourseList.size()==0 && courses.getCoursesy() != 0){
                            int i = userCourseMapper.insert(userCourse);
                            if(i<=0){
                                msg = "添加失败";
                                System.out.println(msg);
                            }else{
                                Course course = new Course();
                                course.setCoursename(coursename);
                                List<Course> mycourse =  courseMapper.selectCourse(course);
                                if(mycourse.size()>0){
                                    mycourse.get(0).setCoursesy(mycourse.get(0).getCoursesy()-1);
                                    mycourse.get(0).setCoursepeople(mycourse.get(0).getCoursepeople()+1);
                                    Course update = mycourse.get(0);
                                    int j = courseMapper.updateByPrimaryKeySelective(update);
                                    if(j<=0){
                                        msg = "添加失败";
                                        System.out.println("j<=0"+msg);
                                    }else{
                                        msg = "添加成功";
                                        System.out.println("j<=0"+msg);
                                    }
                                }

                            }
                        }else if(userCourseList.size() != 0){
                            msg = "不要重复提交，服务器会被玩坏的";
                        }else if(courses.getCoursesy() == 0){
                            msg = "没有剩余人数";
                        }else{
                            msg = "添加失败";
                        }

                    }
                }
            }else {
                msg = "没有登录";
            }

        for (Cookie cookie : cookies){
            if(cookie.getName().equals("coursetype")){
                coursetype = cookie.getValue();
            }
        }
        String username = user.getUsername();

        Course course = new Course();
        course.setCoursetype(coursetype);

        List<Course> courselist = userCourseService.handleCourselist(course,username);
        request.setAttribute("msg",msg);
        request.setAttribute("courselist",courselist);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "queryChooseCourse";
    }

    @RequestMapping(value = "/queryChooseCourse",method = RequestMethod.GET)
    public ModelAndView queryChooseCourse(@RequestParam(defaultValue = "") String coursetype,HttpSession session,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        Cookie[] cookies = request.getCookies();
        if(!coursetype.equals("")){
            Cookie cookie1 = new Cookie("coursetype",coursetype);
            response.addCookie(cookie1);
        }else{
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("coursetype")){
                    coursetype = cookie.getValue();
                }
            }
        }

        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        Course course = new Course();
        course.setCoursetype(coursetype);

        List<Course> courselist = userCourseService.handleCourselist(course,username);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courselist",courselist);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryChooseCourse");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteCourse",method = RequestMethod.POST)
    public String deleteCourse(@ModelAttribute Course course,HttpServletRequest request){
        String[] dellist = course.getDellist();
        String msg = null;
        if(dellist != null){

            //写入service

            List<Integer> delid = new ArrayList<Integer>();
            for(String s : dellist){
                delid.add(Integer.valueOf(s.replace("id","")));
            }
            List<Course> courseList =  courseMapper.selectCourseByIdlist(delid);
            if(courseList.size() != 0){
                List<String> coursename = new ArrayList<String>();
                for (Course mycourse : courseList){
                    coursename.add(mycourse.getCoursename());
                }
                userCourseMapper.deleteCourseByCoursenamelist(coursename);
            }
            courseMapper.deleteCourseByIdlist(delid);

            msg = "删除成功";
        }else{
            msg = "没有要删除的课程";
        }

        Course showcourse = new Course();
        List<Course> courselist = courseMapper.selectCourse(showcourse);

        request.setAttribute("msg",msg);
        request.setAttribute("courselist",courselist);

        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "queryCourse";
    }




}
