package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import com.projects.coupons_v2.utils.CategoryStringConverter;
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
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController  {
    private final JWT jwtUtil;
    private final CustomerService customerService;



    @PostMapping("/purchase/{couponID}")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader("Authorization") String jwt,@PathVariable int couponID, @RequestHeader("WorkId") Integer customerId) throws CustomerException, CouponException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        customerService.purchaseCoupon(couponID, customerId);
        return new ResponseEntity<>(couponID, headers, HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/coupons/all")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String jwt,@RequestHeader("WorkId") Integer customerId) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCoupons(customerId), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/coupons/by/category/{categoryName}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String jwt, @PathVariable String categoryName,@RequestHeader("WorkId") Integer customerId) throws CustomerException {
        Category category = CategoryStringConverter.convertString(categoryName);
        return new ResponseEntity<>(customerService.getCustomerCoupons(category,customerId), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/coupons/by/price/{maxPrice}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String jwt,@PathVariable double maxPrice,@RequestHeader("WorkId") Integer customerId) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCoupons(maxPrice,customerId), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/customerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String jwt,@RequestHeader("WorkId") Integer customerId) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerDetails(customerId), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }
}
