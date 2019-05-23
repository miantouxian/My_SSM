package cn.su.controller;

import cn.su.dao.ConsumlogMapper;
import cn.su.dao.CourseMapper;
import cn.su.pojo.ConsumParam;
import cn.su.pojo.Consumlog;
import cn.su.pojo.Course;
import cn.su.pojo.PageBean;
import cn.su.service.ConsumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ConsumLogController {

    @Autowired
    private ConsumlogMapper consumlogMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Resource
    private ConsumService consumService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/queryAllConsumlogs",method = RequestMethod.GET)
    public ModelAndView queryAllConsumlogs(HttpSession session){
        ConsumParam myparam = new ConsumParam();          //实例化一个空的参数，让查找的数据为全部
        myparam.setCurrPage(1);
        PageBean<Consumlog> pageBean = consumService.findByPage(myparam);
        pageBean.setConsumParam(myparam);
        session.setAttribute("pageBean",pageBean);
        ModelAndView modelAndView = new ModelAndView();
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.addObject("pageBean",pageBean);
        modelAndView.setViewName("queryConsumlogs");
        return modelAndView;
    }


    @RequestMapping(value = "/queryConsumlogs",method = RequestMethod.GET)
    public ModelAndView AllConsum(HttpServletRequest request, @RequestParam(value="currentPage",defaultValue="1",required=false) int currentPage){
        HttpSession session = request.getSession();

        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("pageBean")!=null){
            PageBean<Consumlog> pageBean = (PageBean<Consumlog>) session.getAttribute("pageBean");
            ConsumParam myparam = (ConsumParam) pageBean.getConsumParam();
            myparam.setCurrPage(currentPage);
            pageBean = consumService.findByPage(myparam);
            modelAndView.addObject("pageBean",pageBean);
            modelAndView.setViewName("queryConsumlogs");
            return modelAndView;
        }

        ConsumParam myparam = new ConsumParam();          //实例化一个空的参数，让查找的数据为全部
        myparam.setCurrPage(currentPage);
        PageBean<Consumlog> pageBean = consumService.findByPage(myparam);
        pageBean.setConsumParam(myparam);


        modelAndView.addObject("pageBean",pageBean);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryConsumlogs");
        return modelAndView;
    }


    @RequestMapping(value = "/queryConsumlogs",method = RequestMethod.POST)
    public ModelAndView queryConsumlogs(HttpServletRequest request,@ModelAttribute ConsumParam consumParam){

        consumParam.setCurrPage(1);
        System.out.println("时间"+consumParam.getConsumdate());
        PageBean<Consumlog> pageBean = consumService.findByPage(consumParam);
        HttpSession session = request.getSession();
        session.setAttribute("pageBean",pageBean);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageBean",pageBean);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryConsumlogs");
        return modelAndView;

    }



    @RequestMapping(value = "/addConsumlog",method = RequestMethod.GET)
    public String returnAddConumlog(@ModelAttribute Consumlog consumlog,HttpServletRequest request){
        List<Course> alltype = courseMapper.groupByCoursetype();
        request.setAttribute("alltype", alltype);
        return "addConsumlog";
    }

    public void updateMultipartFile(Consumlog consumlog, MultipartFile items_pic) throws Exception{
        String originalFilename = items_pic.getOriginalFilename();
        DateFormat df1 = DateFormat.getDateInstance();
        if(items_pic != null && originalFilename != null && originalFilename.length()>0 && consumlog.getConsumdate() != null){
            String pic_path = "E:\\develop\\upload\\temp\\";
            String uuid = UUID.randomUUID().toString();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));


            File newfile = new File(pic_path+uuid+df1.format(consumlog.getConsumdate())+suffix);

            items_pic.transferTo(newfile);

            consumlog.setImghref(uuid+df1.format(consumlog.getConsumdate())+suffix);

        }
    }
    @RequestMapping(value = "/addConsumlog",method = RequestMethod.POST)
    public ModelAndView addconsumlog(@ModelAttribute Consumlog consumlog, MultipartFile items_pic,
                                     HttpSession session) throws Exception{

        updateMultipartFile(consumlog,items_pic);

        ModelAndView modelAndView = new ModelAndView();
        int i = consumlogMapper.insertSelective(consumlog);
        String msg;
        if(i<=0){
            msg = "添加失败";
        }else{
            msg = "添加成功";
        }
        ConsumParam myparam = new ConsumParam();          //实例化一个空的参数，让查找的数据为全部
        myparam.setCurrPage(1);
        PageBean<Consumlog> pageBean = consumService.findByPage(myparam);
        pageBean.setConsumParam(myparam);
        session.setAttribute("pageBean",pageBean);

        modelAndView.addObject("msg",msg);
        modelAndView.addObject("pageBean",pageBean);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryConsumlogs");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteConsumlog",method = RequestMethod.POST)
    public ModelAndView deleteConsumlog(@ModelAttribute ConsumParam consumParam,HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        String msg ;
        if (consumParam.getDelid() != null){
            List<Integer> delList = new ArrayList<Integer>();
            String[] delid = consumParam.getDelid();
            for(String s : delid){
                delList.add(Integer.valueOf(s.replace("id","")));
            }
            int i = consumlogMapper.delteConsumlogById(delList);
            if(i>0){
                msg = "删除成功";
            }else {
                msg = "删除失败";
            }
        }else{
            msg = "没有要删除的值";
        }
        ConsumParam myparam = new ConsumParam();          //实例化一个空的参数，让查找的数据为全部
        myparam.setCurrPage(1);
        PageBean<Consumlog> pageBean = consumService.findByPage(myparam);
        pageBean.setConsumParam(myparam);
        session.setAttribute("pageBean",pageBean);

        modelAndView.addObject("msg",msg);
        modelAndView.addObject("pageBean",pageBean);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryConsumlogs");
        return modelAndView;

    }


    @RequestMapping(value = "/modifyConsumlog", method = RequestMethod.GET)
    public ModelAndView returnModifyConsumlog(@RequestParam(value="id") int id){
        Consumlog consumlog = consumlogMapper.selectByPrimaryKey(id);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("consumlog",consumlog);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("modifyConsumlog");
        return modelAndView;
    }
    @RequestMapping(value = "/modifyConsumlog", method = RequestMethod.POST)
    public ModelAndView modifyConsumlog(@ModelAttribute Consumlog consumlog, MultipartFile items_pic,
                                        HttpSession session) throws Exception{
        updateMultipartFile(consumlog,items_pic);

        int i = consumlogMapper.updateByPrimaryKeySelective(consumlog);
        String msg ;
        if(i>0){
            msg = "修改成功";
        }else{
            msg = "修改失败";
        }

        ConsumParam consumParam = new ConsumParam();
        PageBean<Consumlog> pageBean = consumService.findByPage(consumParam);
        session.setAttribute("pageBean",pageBean);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageBean",pageBean);
        modelAndView.addObject("msg",msg);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("queryConsumlogs");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteImg",method = RequestMethod.GET)
    public String deleteImg(@RequestParam int id,HttpServletRequest request){
        Consumlog consumlog = consumlogMapper.selectByPrimaryKey(id);
        String filename = consumlog.getImghref();

        String path = "E:\\develop\\upload\\temp\\";
        File file = new File(path+filename);
        file.delete();
        consumlogMapper.delImghrefValue(id);

        return "redirect:modifyConsumlog?id="+id;
    }
    @RequestMapping(value = "/consumlogInfo", method = RequestMethod.GET)
    public ModelAndView consumlogInfo(@RequestParam int id){
        Consumlog consumlog = consumlogMapper.selectByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("consumlog",consumlog);
        List<Course> alltype = courseMapper.groupByCoursetype();
        modelAndView.addObject("alltype", alltype);
        modelAndView.setViewName("consumlogInfo");
        return modelAndView;
    }



}
