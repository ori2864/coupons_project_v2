package com.projects.coupons_v2.Exceptions.CouponExceptions;

public class CouponException extends Exception{
    public CouponException(CouponMsg errMsg) {
        super (errMsg.getMsg());
    }
}
