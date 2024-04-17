package com.projects.coupons_v2.Services.ServiceInterfaces;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;

import java.util.List;

public interface CustomerService {
    Boolean login (String email, String password) throws CustomerException;
    void purchaseCoupon(int customerID, int couponID);
    List<Coupon>getCustomerCoupons() throws CustomerException;
    List<Coupon>getCustomerCoupons(Category category) throws CustomerException;
    List<Coupon>getCustomerCoupons(double maxPrice) throws CustomerException;
    Customer getCustomerDetails() throws CustomerException;

}
