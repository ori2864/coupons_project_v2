package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import com.projects.coupons_v2.utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController  {
    private final JWT jwtUtil;
    private final CompanyService companyService;


    @PostMapping("/add/coupon")
    public ResponseEntity<?> addCoupon(@RequestHeader("Authorization") String jwt, @RequestBody Coupon coupon) throws CouponException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        companyService.addCoupon(coupon);
        return new ResponseEntity<>(true, headers, HttpStatus.CREATED);
    }


    @PutMapping("/update/coupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader("Authorization") String jwt,@RequestBody Coupon coupon) throws CouponException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        companyService.updateCoupon(coupon);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/coupon/{couponID}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader("Authorization") String jwt,@PathVariable int couponID) throws CompanyException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        companyService.deleteCoupon(couponID);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/coupon/all")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader("Authorization") String jwt) throws CompanyException {
        return new ResponseEntity<>(companyService.getCompanyCoupons(), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/coupon/by/category/{category}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader("Authorization") String jwt,@PathVariable Category category) throws CompanyException {
        return new ResponseEntity<>(companyService.getCompanyCoupons(category), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/coupon/by/price/{maxPrice}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader("Authorization") String jwt,@PathVariable double maxPrice) throws CompanyException {
        return new ResponseEntity<>(companyService.getCompanyCoupons(maxPrice), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/companyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader("Authorization") String jwt) throws CompanyException {
        return new ResponseEntity<>(companyService.getCompanyDetails(), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }

}