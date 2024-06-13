package com.projects.coupons_v2.clr;

import com.projects.coupons_v2.Beans.Category;
import com.projects.coupons_v2.Beans.Credentials;
import com.projects.coupons_v2.Beans.UserDetails;
import com.projects.coupons_v2.Beans.UserType;
import com.projects.coupons_v2.Controllers.UserController;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import com.projects.coupons_v2.utils.DemoData.CustomerData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Order(3)
public class CustomerTest implements CommandLineRunner {
    private final CustomerService customerService;
    private final UserController userController;
    private CustomerData customerData = new CustomerData();
    private final UserDetails userDetails1 = customerData.getUserDetails1();
    private final Credentials credentials1 = customerData.getCredentials1();
    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== start customer test =====");
        userController.registerUser(userDetails1);
        userController.loginUser(credentials1);
        customerService.login("tester@email.com","passi123");
        customerService.purchaseCoupon(1);
        System.out.println("printing customer details: "+customerService.getCustomerDetails());
        System.out.println("printing all customer coupons: "+customerService.getCustomerCoupons());
        System.out.println("printing all customer coupons below price 10: "+customerService.getCustomerCoupons(10));
        System.out.println("printing all customer coupons by category - electricity: "+customerService.getCustomerCoupons(Category.Electricity));

        userController.logout(UserType.CUSTOMER);
        System.out.println("===== end customer test =====");
    }
}
