package com.projects.coupons_v2.Exceptions.GeneralExceptions;

import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponMsg;

public class GeneralException extends Exception{
    public GeneralException(GeneralMsg errMsg) {
        super (errMsg.getMsg());
    }
}
