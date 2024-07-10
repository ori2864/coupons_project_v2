package com.projects.coupons_v2.Services.ServiceInterfaces;

import com.projects.coupons_v2.Beans.Credentials;
import com.projects.coupons_v2.Beans.UserDetails;
import com.projects.coupons_v2.Beans.UserType;

public interface UserService {
     boolean registerUser(UserDetails user) throws Exception;
     UserDetails loginUser(Credentials data) throws Exception;

}
