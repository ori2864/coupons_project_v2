package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerMsg;
import com.projects.coupons_v2.Repositories.CouponRepo;
import com.projects.coupons_v2.Repositories.CustomerRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;


    private Integer customerID;

    @Override
    public Boolean login(String email, String password) throws CustomerException {
        if (customerRepo.existsByEmailAndPassword(email, password)){
            this.customerID=customerRepo.findByEmailAndPassword(email,password).getId();
            return true;
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public void purchaseCoupon(int customerID, int couponID) {
        //todo
    }

    @Override
    public List<Coupon> getCustomerCoupons() throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()){
            return customerRepo.findById(customerID).get().getCoupons();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()){
            return getCustomerCoupons().stream().filter(coupon -> coupon.getCategory().equals(category)).toList();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()){
            return getCustomerCoupons().stream().filter(coupon -> coupon.getPrice()==maxPrice).toList();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public Customer getCustomerDetails() throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()){
            return customerRepo.findById(customerID).get();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }
}
