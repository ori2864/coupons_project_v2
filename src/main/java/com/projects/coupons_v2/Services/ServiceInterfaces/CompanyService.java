package com.projects.coupons_v2.Services.ServiceInterfaces;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;

import java.util.List;

public interface CompanyService {
    Boolean login(String email, String password) throws  CompanyException;

    void addCoupon(Coupon coupon) throws CouponException;
    void updateCoupon(Coupon coupon) throws CouponException;
    void deleteCoupon(int couponID) throws CompanyException;
    List<Coupon> getCompanyCoupons() throws CompanyException;
    List<Coupon> getCompanyCoupons(Category category) throws CompanyException;
    List<Coupon> getCompanyCoupons(double maxPrice) throws CompanyException;
    Company getCompanyDetails() throws CompanyException;

}
