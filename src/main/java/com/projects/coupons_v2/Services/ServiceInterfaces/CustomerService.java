package com.projects.coupons_v2.Services.ServiceInterfaces;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Beans.UserDetails;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;

import java.util.List;

public interface CustomerService {


    Boolean login(String email, String password) throws CustomerException;

    void purchaseCoupon(int couponID, Integer customerId) throws CustomerException, CouponException;

    List<Coupon> getCustomerCoupons(Integer customerId) throws CustomerException;

    List<Coupon> getCustomerCoupons(Category category, Integer customerId) throws CustomerException;

    List<Coupon> getCustomerCoupons(double maxPrice, Integer customerId) throws CustomerException;

    Customer getCustomerDetails(Integer customerId) throws CustomerException;

    Customer getByDetails(String email, String name);
}
