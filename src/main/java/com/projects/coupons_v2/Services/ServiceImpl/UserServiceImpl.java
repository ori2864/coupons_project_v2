package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.Credentials;
import com.projects.coupons_v2.Beans.UserDetails;
import com.projects.coupons_v2.Beans.UserType;
import com.projects.coupons_v2.Repositories.UserRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.CompanyService;
import com.projects.coupons_v2.Services.ServiceInterfaces.CustomerService;
import com.projects.coupons_v2.Services.ServiceInterfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final CompanyService companyService;
    private final CustomerService customerService;

    /*
    private int id;
    private String email;
    private String password;
    private UserType userType;
    private String tel;
    private String location;
    private String genre;
     */

    public boolean registerUser(UserDetails user) throws Exception{
        if (userRepo.existsByEmail(user.getEmail())){
            throw new Exception("UserExists");
        }
        userRepo.save(user);
        return true;
    }

    public UserDetails loginUser(Credentials data) throws Exception {
        if (userRepo.existsByEmailAndPassword(data.getEmail(),data.getPassword())){
            UserDetails userDetails = userRepo.findByEmailAndPassword(data.getEmail(),data.getPassword());
            System.out.println("backend data");
            System.out.println(userDetails);
            return userDetails;
        }

        throw new Exception("Who are you?");
    }
    public void logout(UserType userType){
        switch (userType){
            case COMPANY -> {companyService.clearCompanyId();}
            case CUSTOMER -> {customerService.clearCustomerId();}
        }
    }
}
