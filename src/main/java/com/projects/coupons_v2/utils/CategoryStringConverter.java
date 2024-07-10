package com.projects.coupons_v2.utils;

import com.projects.coupons_v2.Beans.Category;


public class CategoryStringConverter {
    public static Category convertString(String name){
        return Category.valueOf(name.toUpperCase());
    }
}
