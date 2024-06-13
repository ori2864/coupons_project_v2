package com.projects.coupons_v2.utils.DemoData;

import com.projects.coupons_v2.Beans.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class CompanyData {
    private final UserDetails userDetails1=UserDetails.builder()
            .id(0)
            .email("demo@company.com")
            .name("companyComp")
            .password("1234")
            .userType(UserType.COMPANY)
            .build();
    private final Credentials credentials1=new Credentials("demo@company.com","1234",UserType.COMPANY);
//    private Company company2=Company.builder()
    //            .name("test2Company")
//            .email("test2@spring.com")
//            .password("123")
//            .build();
    private final UserDetails userDetails2=UserDetails.builder()
            .id(0)
            .email("test2@spring.com")
            .name("test2Company")
            .password("123")
            .userType(UserType.COMPANY)
            .build();
    private final Credentials credentials2=new Credentials(userDetails2.getEmail()
            , userDetails2.getPassword(), userDetails2.getUserType());

    //========================================= coupons ======================================
    private final Coupon coupon1=Coupon.builder()
            .companyID(0)
            .amount(2)
            .category(Category.Electricity)
            .image("image")
            .description("bla bla")
            .price(7)
            .title("test coupon")
            .start_date(Date.valueOf(LocalDate.now().minusDays(5)))
            .end_date(Date.valueOf(LocalDate.now().plusDays(5)))
            .build();
    private final Coupon coupon2=Coupon.builder()
            .companyID(0)
            .amount(5)
            .category(Category.Restaurant)
            .image("image")
            .description("bla bla")
            .price(9)
            .title("test coupon")
            .start_date(Date.valueOf(LocalDate.now().minusDays(5)))
            .end_date(Date.valueOf(LocalDate.now().plusDays(5)))
            .build();
    private final Coupon coupon3=Coupon.builder()
            .companyID(0)
            .amount(6)
            .category(Category.Electricity)
            .image("image")
            .description("galaxy note 9 discount")
            .price(20)
            .title("phone discount")
            .start_date(Date.valueOf(LocalDate.now().minusDays(5)))
            .end_date(Date.valueOf(LocalDate.now().plusDays(5)))
            .build();
    private final Coupon coupon4=Coupon.builder()
            .companyID(0)
            .amount(8)
            .category(Category.Electricity)
            .image("image")
            .description("big microwave, partially works")
            .price(20)
            .title("microwave")
            .start_date(Date.valueOf(LocalDate.now().minusDays(5)))
            .end_date(Date.valueOf(LocalDate.now().plusDays(5)))
            .build();
    //========================================= coupons ======================================


}
