package com.projects.coupons_v2.Exceptions.CustomerExceptions;

import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponMsg;

public class CustomerException extends Exception{
    public CustomerException(CustomerMsg errMsg) {
        super (errMsg.getMsg());
    }
}
