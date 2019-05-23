package cn.su.controller;

import cn.su.dao.FileMapper;
import cn.su.pojo.File;
import cn.su.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FileController {
    @Autowired
    private FileMapper fileMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @RequestMapping(value = "/createResources",method = RequestMethod.GET)
    public String returnCreateResources (){
        return "createResources";
    }

    @RequestMapping(value = "/createResources",method = RequestMethod.POST)
    public ModelAndView createResources (@ModelAttribute File file, @RequestParam MultipartFile[] resources,
                                    HttpSession session) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        if(resources != null){
            StringBuffer stringBuffer=new StringBuffer();
            int i = 1;
            for (MultipartFile resource : resources){
                if(!resource.isEmpty()){
                    String path = "E:\\develop\\upload\\file\\";
                    String originalFilename = resource.getOriginalFilename();
                    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String uuid = UUID.randomUUID().toString();
                    stringBuffer.append(file.getFilename()+i+suffix);
                    stringBuffer.append(";");
                    java.io.File newfile = new java.io.File(path + file.getFilename()+i+suffix);
                    resource.transferTo(newfile);
                }
                i++;
            }
            String s=stringBuffer.substring(0, stringBuffer.length()-1);
            file.setFilehref(s);
            file.setCreatetime(new Date());
            file.setCreateman(user.getUsername());
            file.setAmount(0);
            int j = fileMapper.insertSelective(file);
            if(j>0){
                modelAndView.addObject("msg","上传成功");
            }else {
                modelAndView.addObject("msg","上传失败");
            }
        }
        modelAndView.setViewName("createFile");
        return modelAndView;
    }

    @RequestMapping(value = "/resourcesInfo",method = RequestMethod.GET)
    public ModelAndView returnFileInfo (@RequestParam int id){
        File file = fileMapper.selectByPrimaryKey(id);
        List<String> download = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(file.getFilehref(), ";");
        while (st.hasMoreTokens()) {
            download.add(st.nextToken());
        }
        file.setDownload(download);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("file",file);
        modelAndView.setViewName("resourcesInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/downloadResources",method = RequestMethod.GET)
    public ModelAndView downloadResources(@RequestParam String downloadfilename, @RequestParam int id, HttpServletResponse response) throws Exception{
        File file = fileMapper.selectByPrimaryKey(id);
        int amount = file.getAmount()+1;
        file.setAmount(amount);
        fileMapper.updateByPrimaryKeySelective(file);
        String path = "E:\\develop\\upload\\file\\";
//        String s = path + file.getDownloadfilename();
//        s = URLEncoder.encode(s,"UTF-8");
        java.io.File newfile = new java.io.File(path + downloadfilename);
        InputStream bis = new BufferedInputStream(new FileInputStream(newfile));

        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(downloadfilename, "UTF-8"));
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        bis.close();
        out.close();

        return null;
    }

    @RequestMapping(value = "/queryAllResources",method = RequestMethod.GET)
    public ModelAndView queryAllResources(){
        List<File> files = fileMapper.selectAll();
        System.out.println("xxxxxxxxxxxxxxxxx"+files.get(0).getAmount());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("files",files);
        modelAndView.setViewName("queryAllResources");
        return modelAndView;
    }


}
