package com.projects.coupons_v2.Exceptions.CouponExceptions;

import lombok.Getter;

@Getter
public enum CouponMsg {
    COUPON_DOES_NOT_EXIST("the coupon id you inserted is not valid, coupon does not exist"),
    ILLEGAL_COUPON_UPDATE("you cannot change company id to existing coupon"),
    COUPON_TITLE_ALREADY_EXISTS("this coupon already exists in this company with this title");


    private final String msg;
    CouponMsg(String msg){this.msg=msg;}
}