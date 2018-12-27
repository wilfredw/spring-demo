package com.wei.spring.app.model.dto;

public class ResultDTO {

    private Integer code;
    private String message;
    private Object data;

    public void setSuccess(Object data) {
        this.code = 0;
        this.message = "success.";
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
