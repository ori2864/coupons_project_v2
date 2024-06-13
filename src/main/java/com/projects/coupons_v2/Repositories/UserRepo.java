package com.projects.coupons_v2.Repositories;

import com.projects.coupons_v2.Beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDetails, Integer> {
    UserDetails findByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
