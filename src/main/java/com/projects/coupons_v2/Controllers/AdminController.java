package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.GeneralExceptions.GeneralException;
import com.projects.coupons_v2.Services.ServiceInterfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController implements AdminService {
    @Autowired
    AdminService adminService;


    @Override
    @GetMapping("/login/{email}/{password}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean login(@PathVariable String email, @PathVariable String password) throws CouponException, GeneralException {
        return adminService.login(email, password);
    }

    @Override
    @PostMapping("/add/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponException, CompanyException {
        adminService.addCompany(company);
    }

    @Override
    @PutMapping("/update/company")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCompany(@RequestBody Company company) throws CouponException, CompanyException {
        adminService.updateCompany(company);
    }

    @Override
    @DeleteMapping("/delete/company/{companyID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable int companyID) throws CompanyException {
        adminService.deleteCompany(companyID);
    }

    @Override
    @GetMapping("/get/all/companies")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @Override
    @GetMapping("/get/one/{companyID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Company getOneCompany(@PathVariable int companyID) throws CouponException, CompanyException {
        return adminService.getOneCompany(companyID);
    }

    @Override
    @PostMapping("/add/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponException, CustomerException {
        adminService.addCustomer(customer);
    }

    @Override
    @PutMapping("/update/customer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(Customer customer) {
        adminService.updateCustomer(customer);
    }

    @Override
    @DeleteMapping("/delete/customer/{customerID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable int customerID) {
        adminService.deleteCustomer(customerID);
    }

    @Override
    @GetMapping("/get/all/customers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @Override
    @GetMapping("/get/one/customer/{customerID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer getOneCustomer(@PathVariable int customerID) {
        return adminService.getOneCustomer(customerID);
    }

    @Override
    @DeleteMapping("/delete/coupon/{customerID}/{couponID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCouponPurchase(@PathVariable int customerID, @PathVariable int couponID) {
        adminService.deleteCouponPurchase(customerID, couponID);
    }
}
