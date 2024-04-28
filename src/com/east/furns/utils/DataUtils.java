package com.east.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class DataUtils {
    public static <T> T copyParametersToBean(T bean, Map map) {
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            return null;
        }
        return bean;
    }
    public static int parseInt(String val,int defInt){
        try{
            int i = Integer.parseInt(val);
            return i;
        }catch (Exception e){
            return defInt;
        }

    }
}
