package cn.su.service.imp;

import cn.su.dao.CourseMapper;
import cn.su.dao.UserCourseMapper;
import cn.su.pojo.Course;
import cn.su.pojo.UserCourse;
import cn.su.service.UserCourseService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service("userCourseService")
public class UserCourseServiceImp implements UserCourseService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private UserCourseMapper userCourseMapper;

    @Override
    public List<Course> handleCourselist(Course course, String username){
        List<Course> courselist = courseMapper.selectCourse(course);
        List<UserCourse> ucList = userCourseMapper.selectByUsername(username);

        //判断选课的url是否显示，剩余容量为0，或者选课表和课程表中没有相同课程名的记录
        if(ucList.size()<=0 || courselist.size()<=0){
            for(int i = 0;i<courselist.size();i++){
                courselist.get(i).setJgchoose(0);
            }
        }else{
            for(int i = 0;i<courselist.size();i++){
                for(int j=0;j<ucList.size();j++){
                    if(courselist.get(i).getCoursename().equals(ucList.get(j).getCoursename())){
                        courselist.get(i).setJgchoose(1);
                        break;
                    }else{
                        courselist.get(i).setJgchoose(0);
                    }
                    if(courselist.get(i).getCoursesy()==0){
                        courselist.get(i).setJgchoose(2);
                        break;
                    }
                }
            }
        }
        return courselist;
    }
}
