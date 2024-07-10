package com.projects.coupons_v2.Services.ServiceInterfaces;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.GeneralExceptions.GeneralException;

import java.util.List;

public interface AdminService {
    Boolean login(String email, String password) throws CouponException, GeneralException;
    Company addCompany(Company company) throws CouponException, CompanyException;
    void updateCompany(Company company) throws CouponException, CompanyException;
    void deleteCompany(int companyID) throws CompanyException;
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID) throws CouponException, CompanyException;
    Customer addCustomer(Customer customer) throws CouponException, CustomerException;
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerID);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID);
}
