package com.projects.coupons_v2.Advices;


import com.projects.coupons_v2.Exceptions.CompanyExceptions.CompanyException;
import com.projects.coupons_v2.Exceptions.CouponExceptions.CouponException;
import com.projects.coupons_v2.Exceptions.CustomerExceptions.CustomerException;
import com.projects.coupons_v2.Exceptions.GeneralExceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {
    @ExceptionHandler(value = GeneralException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String generalErrMessage(Exception e){
        return "error: "+e.getMessage();
    }
    @ExceptionHandler(value = CompanyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String companyErrMessage(Exception e){
        return "error: "+e.getMessage();
    }
    @ExceptionHandler(value = CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String customerErrMessage(Exception e){
        return "error: "+e.getMessage();
    }
    @ExceptionHandler(value = CouponException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String couponErrMessage(Exception e){
        return "error: "+e.getMessage();
    }

}
