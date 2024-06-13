package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
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



    @PostMapping("/purchase/{customerID}/{couponID}")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader("Authorization") String jwt,@PathVariable int customerID,@PathVariable int couponID) throws CustomerException, CouponException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        customerService.purchaseCoupon(couponID);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/coupons/all")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String jwt) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCoupons(), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/coupons/by/category/{category}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String jwt, @PathVariable Category category) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCoupons(category), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/coupons/by/price/{maxPrice}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String jwt,@PathVariable double maxPrice) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCoupons(maxPrice), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }


    @GetMapping("/get/customerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String jwt) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerDetails(), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }
}
