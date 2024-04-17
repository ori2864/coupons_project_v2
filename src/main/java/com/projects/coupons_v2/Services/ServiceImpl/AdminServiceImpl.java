package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyMsg;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerMsg;
import com.projects.coupons_v2.Exceptions.GeneralExceptions.GeneralException;
import com.projects.coupons_v2.Exceptions.GeneralExceptions.GeneralMsg;
import com.projects.coupons_v2.Repositories.CompanyRepo;
import com.projects.coupons_v2.Repositories.CustomerRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;

    @Override
    public Boolean login(String email, String password) throws CouponException, GeneralException {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            return true;
        } else {
            throw new GeneralException(GeneralMsg.ADMIN_LOGIN_FAILED);
        }
    }

    @Override
    public void addCompany(Company company) throws CompanyException {
//check if company name or email already exist and throw exception if needed
        if (companyRepo.existsByName(company.getName())
                || companyRepo.existsByEmail(company.getEmail())) {
            throw new CompanyException(CompanyMsg.COMPANY_ALREADY_EXISTS);
        }

        companyRepo.save(company);
    }

    @Override
    public void updateCompany(Company company) throws CompanyException {
        Company checkCompany = new Company();
        //getting company by id for name validation
        if (companyRepo.findById(company.getId()).isPresent()) {
            checkCompany = companyRepo.findById(company.getId()).get();
        }

        //making sure company name is the same
        if (checkCompany.getName().equals(company.getName())) {
            companyRepo.saveAndFlush(company);
        } else throw new CompanyException(CompanyMsg.CANNOT_UPDATE_COMPANY);
    }

    @Override
    public void deleteCompany(int companyID) throws CompanyException {
        if (companyRepo.findById(companyID).isPresent()) {
            var company = companyRepo.findById(companyID).get();
            company.setCoupons(null);
            companyRepo.saveAndFlush(company);
            companyRepo.deleteById(companyID);
        }
        else throw new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST);
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getOneCompany(int companyID) throws CompanyException {
        return companyRepo.findById(companyID).orElseThrow(() -> new CompanyException(CompanyMsg.COMPANY_DOES_NOT_EXIST));

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {
        //todo
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerException {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CustomerException(CustomerMsg.CUSTOMER_ALREADY_EXISTS);
        }
        customerRepo.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepo.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerID) {
        if (companyRepo.findById(customerID).isPresent()) {
            var customer = customerRepo.findById(customerID).get();
            customer.setCoupons(null);
            customerRepo.saveAndFlush(customer);
            customerRepo.deleteById(customerID);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return customerRepo.findById(customerID).orElseThrow();
    }
}
