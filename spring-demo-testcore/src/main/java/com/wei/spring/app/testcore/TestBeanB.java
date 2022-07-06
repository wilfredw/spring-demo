package com.wei.spring.app.testcore;

public class TestBeanB {
    private TestBeanA testBeanA;

    public TestBeanA getTestBeanA() {
        return testBeanA;
    }

    public void setTestBeanA(TestBeanA testBeanA) {
        this.testBeanA = testBeanA;
    }

    public void test() {
        System.out.println("This is testB");
    }
}
