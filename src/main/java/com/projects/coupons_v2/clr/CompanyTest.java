package com.projects.coupons_v2.clr;

import com.projects.coupons_v2.Beans.*;
import com.projects.coupons_v2.Controllers.UserController;
import com.projects.coupons_v2.Services.ServiceInterfaces.AdminService;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import com.projects.coupons_v2.utils.DemoData.AdminData;
import com.projects.coupons_v2.utils.DemoData.CompanyData;
import com.projects.coupons_v2.utils.JWT;
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
    //======================================================
    private AdminData adminData=new AdminData();
    private final CustomerService customerService;
    private final AdminService adminService;
    private Customer customer1 = adminData.getCustomer1();
    //======================================================
    final JWT jwtUtil;
    @Override
    public void run(String... args) throws Exception {

        userController.registerUser(userDetails1);
        userController.loginUser(credentials1);
//      login returns a token and frontend extracts companyId(4)
        Integer relevantId = 4;

//============================= adding coupons ==================================
        companyService.addCoupon(companyData.getCoupon1(),relevantId);
        companyService.addCoupon(companyData.getCoupon2(),relevantId);
        companyService.addCoupon(companyData.getCoupon3(),relevantId);
//===============================================================================
        System.out.println("================================");
        System.out.println("get company coupons: "+companyService.getCompanyCoupons(relevantId));
        System.out.println("================================");
        System.out.println("get company coupons below price - 10: "+companyService.getCompanyCoupons(10,relevantId));
        System.out.println("================================");
        System.out.println("get company coupons by category - electricity: "+companyService.getCompanyCoupons(Category.ELECTRICITY,relevantId));
        System.out.println("================================");


        userController.registerUser(userDetails2);
        userController.loginUser(credentials2);


        companyService.addCoupon(companyData.getCoupon4(),relevantId);







        System.out.println("===== end company test =====");

    }
}
