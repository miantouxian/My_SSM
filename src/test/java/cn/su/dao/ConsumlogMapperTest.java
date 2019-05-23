package cn.su.dao;

import cn.su.pojo.ConsumParam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class ConsumlogMapperTest {
    private ApplicationContext applicationContext;

    @Autowired
    private ConsumlogMapper consumlogMapper;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        consumlogMapper = applicationContext.getBean(ConsumlogMapper.class);

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void mytest(){
        List<Integer> delid = new ArrayList<Integer>();
        delid.add(26);
        delid.add(25);
        consumlogMapper.delteConsumlogById(delid);
    }



}