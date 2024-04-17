package com.projects.coupons_v2.Exceptions.CustomerExceptions;

import lombok.Getter;

@Getter
public enum CustomerMsg {
    CUSTOMER_ALREADY_EXISTS("this customer already exists"),
    CUSTOMER_DOES_NOT_EXIST("this customer does not exist"),
    CUSTOMER_LOGIN_FAILED("customer username or password incorrect"), COUPON_IS_EXPIRED("this coupon is expired... sorry!");


    private final String msg;
    CustomerMsg(String msg){this.msg=msg;}
}
