package com.it.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1.获取当前类对象
 * 2.获取方法对象
 */
//@WebServlet("*.do")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取当前类对象
        Class<? extends BaseServlet> clazz = this.getClass();
        //获取请求路径
        String uri = req.getRequestURI();
        //根据请求路径获取方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".do"));
        try {
            //获取方法对象，对象必须是public修饰,第一个参数方法名称，后面的是方法的参数
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //通过反射调用方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
