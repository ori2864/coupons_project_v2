package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")

public class CustomerController implements CustomerService {
    @Autowired
    CustomerService customerService;


    @Override
    @GetMapping("/login/{email}/{password}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean login(@PathVariable String email,@PathVariable String password) throws CustomerException {
        return customerService.login(email, password);
    }

    @Override
    @PostMapping("/purchase/{customerID}/{couponID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void purchaseCoupon(@PathVariable int customerID,@PathVariable int couponID) {
        customerService.purchaseCoupon(customerID, couponID);
    }

    @Override
    @GetMapping("/get/coupons/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getCustomerCoupons() throws CustomerException {
        return customerService.getCustomerCoupons();
    }

    @Override
    @GetMapping("/get/coupons/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getCustomerCoupons(@PathVariable Category category) throws CustomerException {
        return customerService.getCustomerCoupons(category);
    }

    @Override
    @GetMapping("/get/coupons/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getCustomerCoupons(@PathVariable double maxPrice) throws CustomerException {
        return customerService.getCustomerCoupons(maxPrice);
    }

    @Override
    @GetMapping("/get/customerDetails")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerDetails() throws CustomerException {
        return customerService.getCustomerDetails();
    }
}
