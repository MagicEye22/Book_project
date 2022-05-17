package com.zpp.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author : zpp
 * @version : 1.0
 */
public class WebUtils {

    /**
     * 把Map中的值注入到对应的javaBean属性中
     * @param value
     * @param bean
     */
    public  static  <T> T copyParamToBean(Map value, T bean){
        try {
            //把所有请求都注入到user对象中
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     *
     * @param s
     * @return
     */
    public static  int parseInt(String s,int value){
            if (s!=null&&s!=""){
                return  Integer.parseInt(s);
            }

        return  value;
    }
}
