package com.wei.spring.app.testcore;

public class TestBeanA {
    private TestBeanB testBeanB;

    public TestBeanB getTestBeanB() {
        return testBeanB;
    }

    public void setTestBeanB(TestBeanB testBeanB) {
        this.testBeanB = testBeanB;
    }

    public void test() {
        System.out.println("This is testA");
    }
}
