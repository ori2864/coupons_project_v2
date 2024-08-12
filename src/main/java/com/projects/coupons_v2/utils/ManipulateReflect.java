package com.projects.coupons_v2.utils;

import com.projects.coupons_v2.Beans.Company;
import com.projects.coupons_v2.Services.ServiceImpl.AdminServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipulateReflect {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        reflectCoupons(context);
        context.close();
    }

    public static List<Company> reflectCoupons(AnnotationConfigApplicationContext context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Company>companies = new ArrayList<>();
        Class<AdminServiceImpl> a = AdminServiceImpl.class;
        Method[] classMethods = a.getMethods();
        Arrays.stream(a.getConstructors()).forEach(System.out::println);
        System.out.println(Arrays.toString(a.getMethods()));
        System.out.println(Arrays.toString(a.getConstructors()));

        // Retrieve the AdminServiceImpl bean from the context
        AdminServiceImpl adminService = context.getBean(AdminServiceImpl.class);

        for (Method method : classMethods) {
            if (method.getName().contains("getAllCompanies")) {
                Method getAll = a.getDeclaredMethod("getAllCompanies");
                Object result = getAll.invoke(adminService);  // Invoke the method on the bean instance
                System.out.println(result);
                companies = (List<Company>)result;
            }
        }

        return companies;
    }
}
