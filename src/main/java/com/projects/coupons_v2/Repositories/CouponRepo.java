package com.projects.coupons_v2.Repositories;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon,Integer> {

    Boolean existsByCompanyIDAndTitle(Integer company_ID, String title);
   



    List<Coupon> findByCompanyID(Integer companyId);
    Coupon findByTitle(String title);

    List<Coupon> findByCompanyIDAndCategory(Integer companyID, Category category);

    List<Coupon> findByCompanyIDAndPriceLessThan(Integer companyID, double maxPrice);



    //  void deleteByEndDateBefore(Date endDate);

}
