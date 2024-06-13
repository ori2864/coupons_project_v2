package com.projects.coupons_v2.Beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "companies")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 15,unique = true)
    private String name;
    @Column(nullable = false,length = 25,unique = true)
    @Email
    private String email;
    @Column(nullable = false,length = 8)
    private String password;
//    @OneToMany(cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    @JoinColumn(name = "company_id")
    @Singular
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "companyID")
    private List<Coupon> coupons;

}
