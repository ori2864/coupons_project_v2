package com.projects.coupons_v2.Beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Table(name = "coupons")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,name = "company_id")
    private Integer companyID;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(length = 15,nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date start_date;
    @Column(nullable = false)
    private Date end_date;
    @Column(nullable = false)
    @Min(0)
    private Integer amount;
    @Column(nullable = false)
    @Min(0)
    private Integer price;
    @Column(nullable = false)
    private String image;
}
