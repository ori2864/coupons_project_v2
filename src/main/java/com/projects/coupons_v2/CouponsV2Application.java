package com.projects.coupons_v2;

import com.projects.coupons_v2.Services.ServiceImpl.CompanyServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CouponsV2Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CouponsV2Application.class, args);
	}

}
