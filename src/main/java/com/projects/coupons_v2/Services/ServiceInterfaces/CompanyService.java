package com.projects.coupons_v2.Services.ServiceInterfaces;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;

import java.util.List;

public interface CompanyService {
    Boolean login(String email, String password) throws  CompanyException;


    Coupon addCoupon(Coupon coupon, Integer companyId) throws CouponException;


    void updateCoupon(Coupon coupon, Integer companyId) throws CouponException;


    void deleteCoupon(int couponID, Integer companyId) throws CompanyException;


    List<Coupon> getCompanyCoupons(Integer companyId) throws CompanyException;


    List<Coupon> getCompanyCoupons(Category category, Integer companyId) throws CompanyException;


    List<Coupon> getCompanyCoupons(double maxPrice, Integer companyId) throws CompanyException;


    Company getCompanyDetails(Integer companyId) throws CompanyException;

    Company getByDetails(String email, String name);
}
