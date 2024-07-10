package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Repositories.CouponRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.GuestService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class GuestServiceImpl implements GuestService {
    private final CouponRepo couponRepo;

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }
}
