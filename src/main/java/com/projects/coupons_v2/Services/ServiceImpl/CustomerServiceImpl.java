package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponMsg;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerMsg;
import com.projects.coupons_v2.Repositories.CouponRepo;
import com.projects.coupons_v2.Repositories.CustomerRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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



    @Override
    public Boolean login(String email, String password) throws CustomerException {
        if (customerRepo.existsByEmailAndPassword(email, password)) {
            Customer customer = customerRepo.findByEmailAndPassword(email, password);
            return true;
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public void purchaseCoupon(int couponID, Integer customerId) throws CustomerException, CouponException {
        if (customerRepo.existsById(customerId) && couponRepo.existsById(couponID)) {
            Customer customer = customerRepo.findById(customerId).get();
            Coupon coupon =couponRepo.findById(couponID).get();
            int oldAmount = coupon.getAmount();
            if (oldAmount<=0){throw new CouponException(CouponMsg.COUPON_OUT_OF_STOCK);
            }
            if (customer.getCoupons().contains(coupon)){
                System.out.println(customer.getCoupons());
                throw new CustomerException(CustomerMsg.COUPON_ALREADY_PURCHASED);
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
    public List<Coupon> getCustomerCoupons(Integer customerId) throws CustomerException {
        if (customerRepo.findById(customerId).isPresent()) {
            return customerRepo.findById(customerId).get().getCoupons();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category, Integer customerId) throws CustomerException {
        if (customerRepo.findById(customerId).isPresent()) {
            return getCustomerCoupons(customerId).stream().filter(coupon -> coupon.getCategory().equals(category)).toList();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice, Integer customerId) throws CustomerException {
        if (customerRepo.findById(customerId).isPresent()) {
            return getCustomerCoupons(customerId).stream().filter(coupon -> coupon.getPrice() < maxPrice).toList();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public Customer getCustomerDetails(Integer customerId) throws CustomerException {
        if (customerRepo.findById(customerId).isPresent()) {
            return customerRepo.findById(customerId).get();
        }
        throw new CustomerException(CustomerMsg.CUSTOMER_DOES_NOT_EXIST);
    }

    @Override
    public Customer getByDetails(String email, String password) {
        return customerRepo.findByEmailAndPassword(email, password);
    }

}
