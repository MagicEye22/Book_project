package com.zpp.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : zpp
 * @version : 1.0
 */
public class UserServletTest {

    public  void  a(){
        System.out.println("aaa");
    }
    public  void  b(){}
    public  void  c(){}
    public  void  d(){}
    public  void  e(){}

    //这是一个main方法，程序的入口！
    public static void main(String[] args) {
        String method="a";
        try {
            //获取方法名 方法反射对象
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(method);
            //调用方法
            declaredMethod.invoke(new UserServletTest());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
