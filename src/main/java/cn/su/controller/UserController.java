package cn.su.controller;

import cn.su.dao.CourseMapper;
import cn.su.dao.UserMapper;
import cn.su.pojo.Course;
import cn.su.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(HttpServletResponse response,HttpServletRequest request) {

        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String userLogin(HttpSession session, Model model, @ModelAttribute User user, HttpServletResponse response,
                            HttpServletRequest request) throws Exception{

        Cookie[] cookies = request.getCookies();
        User sqluser = userMapper.selectByUserId(user.getUserid());
        System.out.println(user.getPassword());

        if(sqluser != null){
            if(user.getPassword().equals(sqluser.getPassword())){
                Cookie cookie1 = new Cookie("username",sqluser.getUsername());
                Cookie cookie2 = new Cookie("info","登录成功");
                Cookie cookie3 = new Cookie("userid",sqluser.getUserid());
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
                session.setAttribute("user",sqluser);
                return "redirect:main";
            }else{
                Cookie cookie = new Cookie("info","密码错误");
                request.setAttribute("msg","密码错误");
                response.addCookie(cookie);
                List<Course> alltype = courseMapper.groupByCoursetype();
                request.setAttribute("alltype", alltype);
                return "login";
            }
        }else{
            Cookie cookie = new Cookie("info","用户不存在");
            request.setAttribute("msg","用户不存在");
            response.addCookie(cookie);
            List<Course> alltype = courseMapper.groupByCoursetype();
            request.setAttribute("alltype", alltype);
            return "login";
        }

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String userLogout(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals("username") || cookie.getName().equals("info") || cookie.getName().equals("userid")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        session.invalidate();
        return "redirect:login";
    }


    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String userRegister(@ModelAttribute User user, HttpServletRequest request){
        User sqluser = userMapper.selectByUserId(user.getUserid());
        if(sqluser != null){
            request.setAttribute("msg","学号已存在");
            return "register";
        }else{
            int i = userMapper.insert(user);
            return "redirect:login";
        }
    }

    @RequestMapping(value = "/user/{userid}",method = RequestMethod.GET)
    public ModelAndView userInfo(@PathVariable() String userid){

        User userInfo = userMapper.selectByUserId(userid);
        List<Course> courselist = courseMapper.groupByCoursetype();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo",userInfo);
        modelAndView.addObject("courselist",courselist);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("userInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/modifyUser",method = RequestMethod.GET)
    public ModelAndView returnModifyUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getUserid();
        User userInfo = userMapper.selectByUserId(userid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo",userInfo);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("modifyUser");
        return modelAndView;
    }

    @RequestMapping(value = "/modifyUser",method = RequestMethod.POST)
    public String modifyUser(@ModelAttribute() User user, MultipartFile user_img,HttpSession session,
                             HttpServletRequest request) throws Exception{

        if(user_img != null){
            String originalFilename = user_img.getOriginalFilename();
            if(originalFilename != null && originalFilename.length()>0){
                String pic_path = "E:\\develop\\upload\\temp\\";
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                File newfile = new File(pic_path+user.getUserid()+"."+suffix);

                user_img.transferTo(newfile);

                user.setAvatar(user.getUserid()+"."+suffix);

            }
        }
        int i = userMapper.updateByPrimaryKeySelective(user);

        if(i>0){
            request.setAttribute("msg","修改成功");
        }else {
            request.setAttribute("msg","修改失败");
        }

        User reluser = userMapper.selectByPrimaryKey(user.getId());
        session.setAttribute("user",reluser);
        request.setAttribute("userInfo",reluser);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);

        return "modifyUser";
    }



    @RequestMapping(value = "/deleteUserAvatar",method = RequestMethod.GET)
    public String deleteUserAvatar(@RequestParam String userid){

        User user = userMapper.selectByUserId(userid);
        String filename = user.getAvatar();
        String path = "E:\\develop\\upload\\temp\\";
        File file = new File(path+filename);
        file.delete();
        userMapper.delAvatarValue(userid);
        BigInteger a = new BigInteger(userid);
        return "redirect:update/"+a;
    }

    @RequestMapping(value = "/phoneBook", method = RequestMethod.GET)
    public String returnPhoneBook(HttpServletRequest request){
        List<User> userlist = userMapper.selectAllUser();
        request.setAttribute("userlist",userlist);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "phoneBook";
    }

    @RequestMapping(value = "/phoneBook", method = RequestMethod.POST)
    public String phoneBook(@ModelAttribute User user,HttpServletRequest request){
        List<User> userlist = userMapper.findUser(user);
        request.setAttribute("userlist",userlist);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        User userparam = user;
        request.setAttribute("userparam",userparam);
        return "phoneBook";
    }


}
