package cn.su.dao;

import cn.su.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {
    private ApplicationContext applicationContext;

    @Autowired
    private  UserMapper userMapper;




    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        userMapper = applicationContext.getBean(UserMapper.class);

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void select(){
        System.out.println(userMapper.selectByUserId("3157102520"));

    }

    @Test
    public void insertinto(){
        User user = new User();
        user.setUserid("3157102520");
        user.setUsername("王培杰");
        user.setPassword("1234");
        userMapper.insert(user);
    }



}