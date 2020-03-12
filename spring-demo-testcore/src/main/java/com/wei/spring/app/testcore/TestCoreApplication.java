package com.wei.spring.app.testcore;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.Date;

public class TestCoreApplication {
    public static void main(String[] args) {
        BeanFactory beanFacotry = new XmlBeanFactory(new ClassPathResource("factory.xml"));
        TestBean testBean = (TestBean) beanFacotry.getBean("testBean");
        System.out.println(new Date() + testBean.getTestStr());
    }
}
