package com.projects.coupons_v2.Repositories;

import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Boolean existsByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);

}
