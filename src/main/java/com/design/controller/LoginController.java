package com.design.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.design.aop.SysLog;
import com.design.model.sys.SysUser;
import com.design.service.user.SysUserService;
import com.design.util.VerifyCodeUtil;

/**
 * 用户登录控制层
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private SysUserService sysUserService; 	
	
	
	@RequestMapping()
	public String Login(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
	
	
	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@RequestMapping(value="/getVerifyCodeImage.action")
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute("verifyCode", verifyCode.toLowerCase());
		// System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 120, 40, 4, true, Color.WHITE,
				Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}
	
	
	/**
	 * 用户登录功能
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@SysLog(operObject="用户",operPath="login/main.action",operType="登录")
	@RequestMapping(value="/main.action")
	public String loginByPC(SysUser user, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
    	String verifyCode = request.getParameter("validateCode");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pwd");
        try {
            if (verifyCode == "" || verifyCode == null) {
            	resultMap.put("msg", "验证码为空");
            	resultMap.put("status",false);
            	request.setAttribute("data",JSON.toJSONString(resultMap));
            	return "login";
            } else if (!verifyCode.toLowerCase().equals(request.getSession().getAttribute("verifyCode"))) {
            	resultMap.put("status",false);
            	resultMap.put("msg", "验证码不对");
            	request.setAttribute("data",JSON.toJSONString(resultMap));
            	return "login";
            }else{
                //用户登录
                user = this.sysUserService.login(phone, password);
                //把用户放session
                if (user != null) {
                	/*
                	 * 主要获取登录用户的区域信息
                	 */
                	Map<String, Object> map = new HashMap<String, Object>();
                	map.clear();
                	map.put("userId", user.getId());
                	
                    request.getSession().setAttribute("user", user);
                    int userCount = sysUserService.getUserCount();
            		request.getSession().setAttribute("userCount", userCount);
            		
                    request.getSession().setAttribute("password", password);
                    resultMap.put("status", true);
                    resultMap.put("msg", "登录成功");
                    return "index/index";
                }else{
                	resultMap.put("status", false);
                    resultMap.put("msg", "密码错误");
                    request.setAttribute("data",JSON.toJSONString(resultMap));
                    return "login";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }
	
	/**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginOut.action")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "login";
    }
	
    @RequestMapping("/toRegister.action")
    public String toRegister(){
    	
    	return "register";
    }
	
}
