package com.projects.coupons_v2.Repositories;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
    Boolean existsByEmailAndPassword(String email,String password);
//    Boolean existsByNameOrEmail(String name, String email);

    Company findByName(String name);
    Boolean existsByEmail(String email);
    Boolean existsByName(String name);

    Company findByEmailAndPassword(String email, String password);

    Company findByEmailAndName(String email, String name);
}
