package cn.su.dao;

import cn.su.pojo.User;
import cn.su.pojo.UserCourse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserCourseTest {
    private ApplicationContext applicationContext;

    @Autowired
    private  UserCourseMapper userCourseMapper;




    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        userCourseMapper = applicationContext.getBean(UserCourseMapper.class);

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void select(){
        UserCourse userCourse = new UserCourse();
        System.out.println(userCourseMapper.selectUsercourse(userCourse));

    }




}