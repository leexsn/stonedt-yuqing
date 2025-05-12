package com.stonedt.intelligence.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
/**
 * 该类为跨域过滤器，用于处理跨域请求。
 * 
 * @date  2020年5月25日 下午5:52:52
 */
//@WebFilter(filterName = "CorsFilter")
//@Configuration
public class CorsFilter implements Filter {
	
    /**
     * 过滤器的核心处理方法，用于设置跨域请求的响应头。
     * 
     * @param req 请求对象
     * @param res 响应对象
     * @param chain 过滤器链
     * @throws IOException 当发生输入输出异常时抛出
     * @throws ServletException 当发生Servlet异常时抛出
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 将响应对象转换为HttpServletResponse类型，以便设置HTTP响应头
        HttpServletResponse response = (HttpServletResponse) res;
        // 获取请求的Origin头信息
        String originHeader=((HttpServletRequest) req).getHeader("Origin");
        
        // 注释掉的代码，设置允许所有来源的跨域请求
        //response.setHeader("Access-Control-Allow-Origin","*");
        // 注释掉的代码，设置允许指定来源的跨域请求，appurl未定义
        //response.setHeader("Access-Control-Allow-Origin",appurl);
        // 设置允许当前请求来源的跨域请求
        response.setHeader("Access-Control-Allow-Origin",originHeader);
        // 允许携带凭证（如Cookie）进行跨域请求
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 设置允许的跨域请求方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        // 设置预检请求的缓存时间，单位为秒
        response.setHeader("Access-Control-Max-Age", "3600");
        // 设置允许的请求头
        response.setHeader("Access-Control-Allow-Headers", "token, Origin, X-Requested-With, Content-Type, Accept");
        // 继续执行过滤器链
        chain.doFilter(req, res);
    }
}