package com.design.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.design.model.sys.SysUser;
import com.design.service.exception.UserExistException;
import com.design.service.user.SysUserService;

@RestController
public class RegisterController {

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="/register.action", method=RequestMethod.POST)
	public String register(SysUser sysUser, HttpServletRequest request){
		
		try {
			
			sysUserService.register(sysUser);
			
			return "login";
		} catch (UserExistException e) {

			request.getSession().setAttribute("error", "手机号已注册可以直接登录");
			return "error";
		}
	}
}
