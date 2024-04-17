package com.projects.coupons_v2.clr;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Beans.Customer;
import com.projects.coupons_v2.Services.ServiceImpl.AdminServiceImpl;
import com.projects.coupons_v2.Services.ServiceImpl.CompanyServiceImpl;
import com.projects.coupons_v2.Services.ServiceImpl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class ServiceTest implements CommandLineRunner {
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    CompanyServiceImpl companyService;
    @Autowired
    CustomerServiceImpl customerService;
    @Override
    public void run(String... args) throws Exception {
        Company company1=Company.builder()
                        .name("testCompany")
                        .email("test@spring.com")
                        .password("123")
                .build();
        Company company2=Company.builder()
                .name("test2Company")
                .email("test2@spring.com")
                .password("123")
                .build();
        adminService.addCompany(company1);
        adminService.addCompany(company2);
        try {
            System.out.println(companyService.login("tesa@spring.com", "133"));
        } catch (Exception err){
            System.out.println(err.getMessage());
        }
        System.out.println(companyService.login("test@spring.com","123"));
        company1.setEmail("updated@spring.com");
        company1.setPassword("555");
        System.out.println(adminService.getOneCompany(company1.getId()));
        adminService.updateCompany(company1);
        System.out.println(adminService.getOneCompany(company1.getId()));
//        adminService.deleteCompany(company1.getId());
        System.out.println(adminService.getAllCompanies());
        Coupon coupon=Coupon.builder()
                .companyID(2)
                .amount(2)
                .category(Category.Electricity)
                .image("image")
                .description("bla bla")
                .price(7)
                .title("test coupon")
                .start_date(Date.valueOf(LocalDate.now().minusDays(5)))
                .end_date(Date.valueOf(LocalDate.now().plusDays(5)))
                .build();
        Coupon coupon1=Coupon.builder()
                .companyID(1)
                .amount(5)
                .category(Category.Restaurant)
                .image("image")
                .description("bla bla")
                .price(9)
                .title("test coupon")
                .start_date(Date.valueOf(LocalDate.now().minusDays(5)))
                .end_date(Date.valueOf(LocalDate.now().plusDays(5)))
                .build();
        companyService.addCoupon(coupon);
        companyService.addCoupon(coupon1);
//        adminService.deleteCompany(2);
        Customer customer= Customer.builder()
                .email("tester@email.com")
                .password("passi123")
                .first_name("name")
                .last_name("last")
                .coupon(coupon)
                .build();
        companyService.login("updated@spring.com","555");
        System.out.println("================================");
        System.out.println("get company coupons: "+companyService.getCompanyCoupons());
        System.out.println("================================");
        System.out.println(companyService.getCompanyCoupons(8));








    }

}
