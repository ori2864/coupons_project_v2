package com.projects.coupons_v2.Exceptions.GeneralExceptions;

import lombok.Getter;

@Getter
public enum GeneralMsg {
    ADMIN_LOGIN_FAILED("admin username or password incorrect"),
    ID_NOT_FOUND("id not found....");

    private final String msg;
    GeneralMsg(String msg){this.msg=msg;}
}
