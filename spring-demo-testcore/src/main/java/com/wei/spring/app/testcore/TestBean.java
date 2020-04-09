package com.wei.spring.app.testcore;

public class TestBean {
    private String testStr= "TestBean String.";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("This is test");
    }
}
