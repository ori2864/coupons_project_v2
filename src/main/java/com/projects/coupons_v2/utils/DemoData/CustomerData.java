package com.projects.coupons_v2.utils.DemoData;

import com.projects.coupons_v2.Beans.Credentials;
import com.projects.coupons_v2.Beans.UserDetails;
import com.projects.coupons_v2.Beans.UserType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CustomerData {
    private final UserDetails userDetails1 = UserDetails.builder()
            .email("tester@email.com")
            .password("passi123")
            .firstName("susi")
            .lastName("fishman")
            .name("demoCustomer")
            .userType(UserType.CUSTOMER)
            .build();
    private final Credentials credentials1 = new Credentials(userDetails1.getEmail(), userDetails1.getPassword(), userDetails1.getUserType());
}
