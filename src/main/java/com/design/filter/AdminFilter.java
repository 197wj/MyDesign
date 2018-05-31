package com.design.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.design.model.sys.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminFilter extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
         HttpSession session = request.getSession();
         
         SysUser user = (SysUser) session.getAttribute("user");
         
         if(!user.getName().equals("admin")){
             //禁止操作
             // 创建需要返回给客户端的结果
        	 Map<String, String> map = new HashMap<String, String>();
        	 map.put("警告：", "权限不足！");
        	 
             // 将对象转化为json格式
             ObjectMapper om = new ObjectMapper();
             
             String json = om.writeValueAsString(map);
             
             // 设置编码格式
              response.setContentType("application/json;charset=utf-8");
             
             response.getWriter().println(json);
             
             return false; // false不能通过
         }else {
             
             return true; // 通过
         }
         
    }
}

