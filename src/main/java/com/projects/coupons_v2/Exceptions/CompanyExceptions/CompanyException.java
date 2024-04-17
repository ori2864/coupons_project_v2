package com.projects.coupons_v2.Exceptions.CompanyExceptions;

import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponMsg;

public class CompanyException extends Exception {
    public CompanyException(CompanyMsg errMsg) {
        super (errMsg.getMsg());
    }
}
