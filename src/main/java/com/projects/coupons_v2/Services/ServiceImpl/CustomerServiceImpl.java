package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Beans.UserDetails;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponMsg;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerMsg;
import com.projects.coupons_v2.Repositories.CouponRepo;
import com.projects.coupons_v2.Repositories.CustomerRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Data
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;


    private Integer customerID;

    @Override
    public Boolean login(String email, String password) throws CustomerException {
        if (customerRepo.existsByEmailAndPassword(email, password)) {
            Customer customer = customerRepo.findByEmailAndPassword(email, password);
            this.setCustomerID(customer.getId());
            return true;
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public void purchaseCoupon(int couponID) throws CustomerException, CouponException {
        if (customerRepo.existsById(this.customerID) && couponRepo.existsById(couponID)) {
            Customer customer = customerRepo.findById(customerID).get();
            Coupon coupon =couponRepo.findById(couponID).get();
            int oldAmount = coupon.getAmount();
            if (oldAmount<=0){throw new CouponException(CouponMsg.COUPON_OUT_OF_STOCK);
            }
            coupon.setAmount(oldAmount-1);
            couponRepo.saveAndFlush(coupon);
            List<Coupon> coupons = customer.getCoupons();
            coupons.add(coupon);
            System.out.println(coupons);
            customer.setCoupons(coupons);

            customerRepo.saveAndFlush(customer);
        }
        else throw new CustomerException(CustomerMsg.ERROR_PURCHASING_COUPON);
    }

    @Override
    public List<Coupon> getCustomerCoupons() throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()) {
            return customerRepo.findById(customerID).get().getCoupons();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()) {
            return getCustomerCoupons().stream().filter(coupon -> coupon.getCategory().equals(category)).toList();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()) {
            return getCustomerCoupons().stream().filter(coupon -> coupon.getPrice() < maxPrice).toList();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public Customer getCustomerDetails() throws CustomerException {
        if (customerRepo.findById(customerID).isPresent()) {
            return customerRepo.findById(customerID).get();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public void clearCustomerId() {
        this.setCustomerID(null);
    }
}
