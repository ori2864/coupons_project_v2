package com.projects.coupons_v2.Controllers;

import com.projects.coupons_v2.Beans.*;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Services.ServiceInterfaces.AdminService;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import com.projects.coupons_v2.Services.ServiceInterfaces.UserService;
import com.projects.coupons_v2.utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UserService usersService;
    private final JWT jwt;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDetails data) throws Exception {
        usersService.registerUser(data);
    }




    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Credentials credentials) throws Exception{
        HttpHeaders headers = new HttpHeaders();

        switch (credentials.getUserType()){
            case ADMIN -> {adminService.login(credentials.getEmail(), credentials.getPassword());

            }
            case COMPANY -> {companyService.login(credentials.getEmail(), credentials.getPassword());}
            case CUSTOMER -> {customerService.login(credentials.getEmail(), credentials.getPassword());}

        }
        UserDetails userDetails = usersService.loginUser(credentials);
            headers.set("Authorization","Bearer "+jwt.generateToken(userDetails));
            return new ResponseEntity<>(true,headers,HttpStatus.OK);

    }


}
