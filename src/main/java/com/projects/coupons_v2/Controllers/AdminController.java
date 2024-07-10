package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.GeneralExceptions.GeneralException;
import com.projects.coupons_v2.Services.ServiceInterfaces.AdminService;
import com.projects.coupons_v2.utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final JWT jwtUtil;
    private final AdminService adminService;


    @PostMapping("/add/company")
    public ResponseEntity<?> addCompany(@RequestHeader("Authorization") String jwt, @RequestBody Company company) throws CouponException, CompanyException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        Company returnCompany = adminService.addCompany(company);
        return new ResponseEntity<>(returnCompany.getId(), headers, HttpStatus.CREATED);
    }


    @PutMapping("/update/company")
    public ResponseEntity<?> updateCompany(@RequestHeader("Authorization") String jwt, @RequestBody Company company) throws CouponException, CompanyException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        adminService.updateCompany(company);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/company/{companyID}")
    public ResponseEntity<?> deleteCompany(@RequestHeader("Authorization") String jwt, @PathVariable int companyID) throws CompanyException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        adminService.deleteCompany(companyID);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/all/companies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String jwt) {

        return new ResponseEntity<>(adminService.getAllCompanies(), jwtUtil.getHeaders(jwt), HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/one/company/{companyID}")
    public ResponseEntity<?> getOneCompany(@RequestHeader("Authorization") String jwt, @PathVariable int companyID) throws CouponException, CompanyException {

        return new ResponseEntity<>(adminService.getOneCompany(companyID), jwtUtil.getHeaders(jwt), HttpStatus.ACCEPTED);
    }


    @PostMapping("/add/customer")
    public ResponseEntity<?> addCustomer(@RequestHeader("Authorization") String jwt, @RequestBody Customer customer) throws CouponException, CustomerException {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        Customer returnCustomer = adminService.addCustomer(customer);
        return new ResponseEntity<>(returnCustomer.getId(), headers, HttpStatus.CREATED);
    }


    @PutMapping("/update/customer")
    public ResponseEntity<?> updateCustomer(@RequestHeader("Authorization") String jwt, @RequestBody Customer customer) {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/customer/{customerID}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String jwt, @PathVariable int customerID) {
        HttpHeaders headers=jwtUtil.getHeaders(jwt);
        adminService.deleteCustomer(customerID);
        return new ResponseEntity<>(true, headers, HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/all/customers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Authorization") String jwt) {
        System.out.println(jwtUtil.getHeaders(jwt));
        return new ResponseEntity<>(adminService.getAllCustomers(), jwtUtil.getHeaders(jwt), HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/one/customer/{customerID}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader("Authorization") String jwt, @PathVariable int customerID) {
        return new ResponseEntity<>(adminService.getOneCustomer(customerID), jwtUtil.getHeaders(jwt), HttpStatus.ACCEPTED);
    }



}
