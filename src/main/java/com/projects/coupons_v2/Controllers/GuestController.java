package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Services.ServiceInterfaces.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
@CrossOrigin
public class GuestController {
    private final GuestService guestService;
    @GetMapping("/get/coupons/all")
    public List<Coupon>getAllCoupons(){
        return guestService.getAllCoupons();
    }




}
