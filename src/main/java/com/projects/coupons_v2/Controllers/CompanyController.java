package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController implements CompanyService {
    @Autowired
    CompanyService companyService;
    @GetMapping("/login/{email}/{password}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Override
    public Boolean login(@PathVariable String email, @PathVariable String password) throws  CompanyException {
        return companyService.login(email,password);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestBody Coupon coupon) throws CouponException {
        companyService.addCoupon(coupon);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) throws CouponException {
        companyService.updateCoupon(coupon);
    }

    @Override
    @DeleteMapping("/{couponID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCoupon(@PathVariable int couponID) throws CompanyException {
        companyService.deleteCoupon(couponID);
    }

    @Override
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getCompanyCoupons() throws CompanyException {
        return companyService.getCompanyCoupons();
    }

    @Override
    @GetMapping("/get/coupons/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getCompanyCoupons(@PathVariable Category category) throws CompanyException {
        return companyService.getCompanyCoupons(category);
    }

    @Override
    @GetMapping("/get/coupons/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getCompanyCoupons(@PathVariable double maxPrice) throws CompanyException {
        return companyService.getCompanyCoupons(maxPrice);
    }

    @Override
    @GetMapping("/get/companyDetails")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyDetails() throws CompanyException {
        return companyService.getCompanyDetails();
    }
}