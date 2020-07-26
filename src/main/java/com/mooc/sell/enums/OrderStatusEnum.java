package com.mooc.sell.enums;

public enum OrderStatusEnum implements codeEnum {
    NEW(0,"新订单"),
    FINISHE(1,"完结"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;

    private  String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
