package com.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制层
 */
@Controller
public class IndexController {
	
	
	
	/*@RequestMapping("/index.action")
	public String index(){
		return "index/index";
	}*/
	
	@RequestMapping(value="/welcome.action")
	public String welcome(){
		return "index/welcome";
	}
	
}
