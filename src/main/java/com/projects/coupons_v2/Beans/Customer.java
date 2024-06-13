package com.projects.coupons_v2.Beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Table(name = "customers")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 15,unique = true)
    private String first_name;
    @Column(nullable = false,length = 15,unique = true)
    private String last_name;
    @Column(nullable = false,length = 25,unique = true)
    private String email;
    @Column(nullable = false,length = 8)
    private String password;


    //        @ManyToMany(cascade = CascadeType.REMOVE)
    //    @ManyToMany(targetEntity = Coupon.class)
    //    @PrimaryKeyJoinColumn(name = "coupon_id")
    //    @JoinTable(name = "customers_vs_coupons")

    @Singular
//    @OneToMany(cascade = CascadeType.REMOVE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "customerId"),

            inverseJoinColumns = @JoinColumn(name = "couponId"))
    private List<Coupon>coupons;
}
