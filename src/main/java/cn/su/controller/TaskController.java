package cn.su.controller;

import cn.su.dao.CourseMapper;
import cn.su.dao.TaskMapper;
import cn.su.pojo.Course;
import cn.su.pojo.Task;
import cn.su.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class TaskController {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CourseMapper courseMapper;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }



    @RequestMapping(value = "/createTask",method = RequestMethod.GET)
    public String returnCreateTask(HttpServletRequest request){

        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "createTask";
    }


    @RequestMapping(value = "/createTask",method = RequestMethod.POST)
    public String createTask(@ModelAttribute() Task task, MultipartFile task_img, Model model,
                             HttpSession session, HttpServletRequest request) throws Exception{

        String originalFilename = task_img.getOriginalFilename();
        User user = (User) session.getAttribute("user");
        task.setOfferor(user.getUsername());
        Date createtime = new Date();
        task.setCreatetime(createtime);
        task.setStatus("开启");
        if(task_img != null && originalFilename != null && originalFilename.length()>0){
            String pic_path = "E:\\develop\\upload\\temp\\";
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String filename = UUID.randomUUID().toString() + "." + suffix;
            File newfile = new File(pic_path + filename);

            task_img.transferTo(newfile);

           task.setTaskimg(filename);
        }
        taskMapper.insertSelective(task);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "createTask";
    }

    @RequestMapping(value = "/queryAllTask", method = RequestMethod.GET)
    public String queryAllTask(HttpServletRequest request){
        List<Task> tasks = taskMapper.selectAllTask();
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "queryAllTask";
    }


    @RequestMapping(value = "/chooseTask", method = RequestMethod.POST)
    public ModelAndView chooseTask(@ModelAttribute Task task, HttpSession session, HttpServletRequest request){
        int id = task.getId();
        Task task1 = taskMapper.selectByPrimaryKey(id);
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if(task1.getOfferor().equals(user.getUsername()) || task1.getStatus().equals("关闭") || task1.getOfferee() != null){
            List<Task> tasks = taskMapper.selectAllTask();
            modelAndView.addObject("tasks",tasks);
            modelAndView.addObject("msg","不能接受自己创建的任务");
            if(task1.getOfferee() != null){
                modelAndView.addObject("msg","不要刷新了服务器很累的");
            }
            modelAndView.setViewName("queryAllTask");
        }else{
            task1.setOfferee(user.getUsername());
            task1.setStatus("接受");
            taskMapper.updateByPrimaryKeySelective(task1);
            modelAndView.addObject("msg","接受成功");
            modelAndView.setViewName("myAcceptTask");
        }
        List<Task> tasks = taskMapper.selectTaskByOfferee(user.getUsername());
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);

        return modelAndView;
    }

    @RequestMapping(value = "/myCreateTask",method = RequestMethod.GET)
    public String myCreateTask(HttpSession session, HttpServletRequest request){

        User user = (User) session.getAttribute("user");
        List<Task> tasks = taskMapper.selectTaskByOfferor(user.getUsername());
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "myCreateTask";
    }

    @RequestMapping(value = "/myAcceptTask",method = RequestMethod.GET)
    public String myAcceptTask(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        List<Task> tasks = taskMapper.selectTaskByOfferee(user.getUsername());
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "myAcceptTask";
    }
    @RequestMapping(value = "/taskData",method = RequestMethod.GET)
    public String taskData(@RequestParam int id, HttpServletRequest request){
        if(id != 0){

            Task task = taskMapper.selectTaskById(id);
            request.setAttribute("task",task);
            List<Course> alltype = courseMapper.groupByCoursetype();
            request.setAttribute("alltype", alltype);
        }
        return "taskInfo";
    }




    @RequestMapping(value = "/offerorConfirm", method = RequestMethod.GET)
    public String offerorConfirm(@RequestParam int id, HttpServletRequest request, HttpSession session){

        Task task = new Task();
        task.setId(id);
        task.setStatus("已完成");
        task.setFinishtime(new Date());
        Task jgTask = taskMapper.selectByPrimaryKey(task.getId());
        if(jgTask.getStatus().equals("待确认")){
            taskMapper.updateByPrimaryKeySelective(task);
            request.setAttribute("msg","操作成功");
        }else{
            request.setAttribute("msg","操作失败");
        }

        User user = (User) session.getAttribute("user");
        List<Task> tasks = taskMapper.selectTaskByOfferor(user.getUsername());
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "myCreateTask";
    }

    @RequestMapping(value = "/offereeConfirm", method = RequestMethod.GET)
    public String offereeConfirm(@RequestParam int id, HttpServletRequest request, HttpSession session){
        Task task = new Task();
        task.setId(id);
        task.setStatus("待确认");
        Task jgTask = taskMapper.selectByPrimaryKey(task.getId());
        if(jgTask.getStatus().equals("接受")){
            taskMapper.updateByPrimaryKeySelective(task);
            request.setAttribute("msg","操作成功");
        }else{
            request.setAttribute("msg","操作失败");
        }

        User user = (User) session.getAttribute("user");
        List<Task> tasks = taskMapper.selectTaskByOfferee(user.getUsername());
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "myAcceptTask";
    }


    @RequestMapping(value = "/deleteMyTask", method = RequestMethod.GET)
    public String deleteMyTask(@RequestParam int id, HttpServletRequest request,HttpSession session){

        if(id>0){
            Task task = taskMapper.selectByPrimaryKey(id);
            User user = (User) session.getAttribute("user");
            if(task.getOfferor().equals(user.getUsername())){
                taskMapper.deleteByPrimaryKey(id);
            }else {
                request.setAttribute("msg","删除失败");
            }
        }else{
            request.setAttribute("msg","没有要删除的任务");
        }
        User user = (User) session.getAttribute("user");
        List<Task> tasks = taskMapper.selectTaskByOfferor(user.getUsername());
        request.setAttribute("tasks",tasks);
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "myCreateTask";
    }





}
