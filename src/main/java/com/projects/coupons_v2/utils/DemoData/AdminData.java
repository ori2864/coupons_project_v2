package com.projects.coupons_v2.utils.DemoData;

import com.projects.coupons_v2.Beans.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AdminData {
    private UserDetails userDetails1 = UserDetails.builder()
            .userType(UserType.ADMIN)
            .name("ori2864")
            .firstName("Ori")
            .lastName("Tzach")
            .email("admin@admin.com")
            .password("admin")
            .build();
    private Credentials credentials1 = new Credentials(userDetails1.getEmail(),
            userDetails1.getPassword(), userDetails1.getUserType());
    private Company company1=Company.builder()
            .name("testCompany")
            .email("test@spring.com")
            .password("123")
            .build();



    private Company company2=Company.builder()
            .name("test2Company")
            .email("test2@spring.com")
            .password("123")
            .build();
    private Company company3 = Company.builder()
            .name("AnotherCompany")
                .email("another@company.com")
                .password("123")
                    .build();
    private Customer customer1= Customer.builder()
            .email("tester@email.com")
            .password("passi123")
            .first_name("name")
            .last_name("last")
            .build();



}
