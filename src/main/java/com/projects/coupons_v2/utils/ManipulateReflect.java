package com.projects.coupons_v2.utils;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Beans.Coupon;
import com.projects.coupons_v2.Services.ServiceImpl.AdminServiceImpl;
import com.projects.coupons_v2.Services.ServiceImpl.CompanyServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ManipulateReflect {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
//        String firstWord = "Hello ";
//        String secondWord = "everybody.";
//        Integer bothWords = append(firstWord, secondWord);
//        System.out.println(bothWords);
        reflectCoupons();



    }
//    public static Integer append(String firstWord, String secondWord) {
//        Integer result = null;
//        Class c = Integer.class;
//        Class[] parameterTypes = new Class[] {Integer.class};
//        Method concatMethod;
//        Object[] arguments = new Object[] {5,7};
//        try {
//            concatMethod = c.getMethod("sum", int.class, int.class);
//            result = (Integer) concatMethod.invoke("sum", arguments[0],arguments[1]);
//        } catch (NoSuchMethodException e) {
//            System.out.println(e);
//        } catch (IllegalAccessException e) {
//            System.out.println(e);
//        } catch (InvocationTargetException e) {
//            System.out.println(e);
//        }
//        return result;
//    }
    public static List<Coupon>reflectCoupons() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class a = AdminServiceImpl.class;
        Class c = CompanyServiceImpl.class;
        Method[] classMethods = c.getMethods();
        Object[] arguments = new Object[] {1};
        for (int i = 0; i < classMethods.length; i++) {
            if (classMethods[i].getName().contains("getCompanyDetails")) {
                Method setID = c.getDeclaredMethod("setCompanyID", Integer.class);
                setID.invoke(c.getDeclaredConstructor().newInstance(), arguments);
                System.out.println(classMethods[i].invoke("", (Object[]) null));
            }
        }
//        System.out.println(Arrays.toString(classMethods));



        return null;

//        Method concatMethod;
//        Object[] arguments = new Object[] {5,7};
    }
}

