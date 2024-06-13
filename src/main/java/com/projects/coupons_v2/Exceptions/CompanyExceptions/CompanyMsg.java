package com.projects.coupons_v2.Exceptions.CompanyExceptions;

import com.projects.coupons_v2.Beans.Company;
import lombok.Getter;

@Getter
public enum CompanyMsg {
    COMPANY_LOGIN_FAILED("company username or password incorrect"),
    COMPANY_ALREADY_EXISTS("this company already exists..."),
    COMPANY_DOES_NOT_EXIST("this company does not exist"),
    ILLEGAL_COUPON_DELETE("this coupon id is either wrong or belongs to another company"),
    CANNOT_UPDATE_COMPANY("cannot update company name, please" +
            " make sure the name is correct and try again..."), COUPON_ALREADY_EXISTS("this coupon already exists in this company by its title"), COUPON_DOES_NOT_EXIST("this coupon does not exist");
    private final String msg;
    CompanyMsg(String msg){this.msg=msg;}

}
