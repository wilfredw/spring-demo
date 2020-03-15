package com.wei.spring.app.testcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TestContextApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factory.xml");
        TestBean testBean = (TestBean)applicationContext.getBean("testBean");
        System.out.println(new Date() + " --- " + testBean.getTestStr());
    }
}
