package com.projects.coupons_v2.clr;

import com.projects.coupons_v2.Beans.*;
import com.projects.coupons_v2.Controllers.UserController;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import com.projects.coupons_v2.utils.DemoData.CompanyData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class CompanyTest implements CommandLineRunner {
    private final CompanyService companyService;
    private final UserController userController;
    private CompanyData companyData = new CompanyData();
    UserDetails userDetails1 = companyData.getUserDetails1();
    UserDetails userDetails2 = companyData.getUserDetails2();
    Credentials credentials1 = companyData.getCredentials1();
    Credentials credentials2 = companyData.getCredentials2();
    @Override
    public void run(String... args) throws Exception {

        userController.registerUser(userDetails1);
        userController.loginUser(credentials1);

//============================= adding coupons ==================================
        companyService.addCoupon(companyData.getCoupon1());
        companyService.addCoupon(companyData.getCoupon2());
        companyService.addCoupon(companyData.getCoupon3());
//===============================================================================
        System.out.println("================================");
        System.out.println("get company coupons: "+companyService.getCompanyCoupons());
        System.out.println("================================");
        System.out.println("get company coupons below price - 10: "+companyService.getCompanyCoupons(10));
        System.out.println("================================");
        System.out.println("get company coupons by category - electricity: "+companyService.getCompanyCoupons(Category.Electricity));
        System.out.println("================================");
        userController.logout(UserType.COMPANY);

        userController.registerUser(userDetails2);
        userController.loginUser(credentials2);


        companyService.addCoupon(companyData.getCoupon4());

        userController.logout(UserType.COMPANY);


        System.out.println("===== end company test =====");

    }
}
