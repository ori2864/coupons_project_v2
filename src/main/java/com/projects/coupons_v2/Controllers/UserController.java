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
        switch (data.getUserType()){
            case ADMIN -> {
                //todo check if added regularly or require special validation

            }

            case COMPANY -> {
                try {
                    adminService.addCompany(new Company(0, data.getName(), data.getEmail()
                            , data.getPassword(), null));
                }catch (CompanyException exception){
                    System.out.println("company already exists - creating user for company");
                }

            }
            case CUSTOMER -> {
                try {
                    adminService.addCustomer(new Customer(0, data.getFirstName(), data.getLastName()
                            , data.getEmail(), data.getPassword(), null));
                }catch (CustomerException exception){
                    System.out.println("customer already exists - creating user for customer");
                }
            }
        }
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
    @GetMapping("/logout/{userType}")
    public void logout(@PathVariable UserType userType){
        usersService.logout(userType);

    }

}
