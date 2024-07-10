package com.projects.coupons_v2.Services.ServiceImpl;

import com.projects.coupons_v2.Beans.*;
import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Repositories.UserRepo;
import com.projects.coupons_v2.Services.ServiceInterfaces.AdminService;
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
    private final AdminService adminService;

    /*
    private int id;
    private String email;
    private String password;
    private UserType userType;
    private String tel;
    private String location;
    private String genre;
     */

    public boolean registerUser(UserDetails data) throws Exception{
        Integer workId=-1;
        switch (data.getUserType()){
            case ADMIN -> {
                //admin cannot register since its one hard-coded user.
            }

            case COMPANY -> {
                try {
                   Company company = adminService.addCompany(new Company(0, data.getName(), data.getEmail()
                            , data.getPassword(), null));
                   workId=company.getId();
                }catch (CompanyException exception){
                    System.out.println("company already exists - creating user for company");
                    Company company = companyService.getByDetails(data.getEmail(), data.getName());
                    workId = company.getId();
                }

            }
            case CUSTOMER -> {
                try {
                    Customer customer = adminService.addCustomer(new Customer(0, data.getFirstName(), data.getLastName()
                            , data.getEmail(), data.getPassword(), null));
                    workId = customer.getId();
                }catch (CustomerException exception){
                    System.out.println("customer already exists - creating user for customer");
                    Customer customer = customerService.getByDetails(data.getEmail(),data.getPassword());
                    workId = customer.getId();
                }
            }

        }

        if (userRepo.existsByEmail(data.getEmail())){
            throw new Exception("UserExists");
        }
        data.setWorkId(workId);
        userRepo.save(data);
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

}
