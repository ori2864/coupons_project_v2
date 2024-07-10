package com.projects.coupons_v2.clr;

import com.projects.coupons_v2.Beans.*;
import com.projects.coupons_v2.Controllers.UserController;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import com.projects.coupons_v2.utils.DemoData.CustomerData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
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
//        login returns a token and frontend extracts customerId(1)
        System.out.println("===== start customer test =====");
        userController.registerUser(userDetails1);

        userController.loginUser(credentials1);

        Integer relevantId = 1;

        customerService.login("tester@email.com","passi123");
        customerService.purchaseCoupon(1,relevantId);
        System.out.println("printing customer details: "+customerService.getCustomerDetails(relevantId));
        System.out.println("printing all customer coupons: "+customerService.getCustomerCoupons(relevantId));
        System.out.println("printing all customer coupons below price 10: "+customerService.getCustomerCoupons(10,relevantId));
        System.out.println("printing all customer coupons by category - electricity: "+customerService.getCustomerCoupons(Category.ELECTRICITY,relevantId));


        System.out.println("===== end customer test =====");
    }
}
