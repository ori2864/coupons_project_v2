package com.projects.coupons_v2.clr;

import com.projects.coupons_v2.Beans.*;
import com.projects.coupons_v2.Controllers.UserController;
import com.projects.coupons_v2.Services.ServiceImpl.AdminServiceImpl;
import com.projects.coupons_v2.Services.ServiceImpl.CompanyServiceImpl;
import com.projects.coupons_v2.Services.ServiceImpl.CustomerServiceImpl;
import com.projects.coupons_v2.utils.DemoData.AdminData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Primary
@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {
    private final AdminServiceImpl adminService;
    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;
    private final UserController userController;
    private AdminData adminData=new AdminData();
    private Company company1 = adminData.getCompany1();
    private Company company2 = adminData.getCompany2();
    private Company company3 = adminData.getCompany3();
    private Customer customer1 = adminData.getCustomer1();
    private UserDetails userDetails1 = adminData.getUserDetails1();
    private Credentials credentials1 = adminData.getCredentials1();
    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== start admin test =====");
//========================building and adding companies&customer============================
        userController.registerUser(userDetails1);
        userController.loginUser(credentials1);
        adminService.addCompany(company1);
        adminService.addCompany(company2);
        adminService.addCompany(company3);
        adminService.addCustomer(customer1);
//=================================================================================

// ==========changing company1 details, printing it before and after update========
        company1.setEmail("updated@spring.com");
        company1.setPassword("555");
        System.out.println("company 1 before update:\n"+adminService.getOneCompany(1));
        adminService.updateCompany(company1);
        System.out.println("company 1 after update:\n"+adminService.getOneCompany(1));
//==================================================================================




//        deleting company 3
//        printing all companies before and after
//        System.out.println(adminService.getAllCompanies());
//        adminService.deleteCompany(3);
//        System.out.println(adminService.getAllCompanies());

        //================================================================

            userController.logout(UserType.ADMIN);
        System.out.println("===== end admin test =====");








    }

}
