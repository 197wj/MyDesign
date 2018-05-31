package com.design.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.aop.SysLog;
import com.design.dao.sys.SysUserDao;
import com.design.model.common.Pagination;
import com.design.model.sys.SysUser;
import com.design.service.user.SysUserService;


@RequestMapping(value="/user")
@Controller
public class SysUserContrioller {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 跳转到用户列表首页
	 * @return
	 */
	@RequestMapping(value="/userView.action")
	public String userView(){
		return "user/user-list";
	}
	
	/**
	 * 用户详情页
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/userInfoView.action")
	public String userInfoView(HttpServletRequest request,
						@RequestParam(value="userId",required=true) Integer userId){
		request.setAttribute("data", userId);
		return "user/user-info";
	}
	
	/**
	 * 分页获取用户
	 * @return
	 */
	@RequestMapping(value="/getUserByPage.action")
	@ResponseBody
	public Map<String, Object> getUserByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
            								 @RequestParam(value = "rows",defaultValue = "10") Integer rows,
            								 @RequestParam(value = "phone",required = false) String phone,
            								 @RequestParam(value = "name", required = false) String name){
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		try {
			List<Map<String, Object>> resultList = null;
			int totalRecords = 0;
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("phone", phone);
			map.put("name", name);
			//调用service方法,进行数据的查询
			Pagination<Map<String, Object>> pagination;
			pagination = this.sysUserService.getPagingDatas(map, page, rows, "selectUserCount","selectUserList",SysUserDao.class);
			resultList = pagination.getPageList();//返回数据
			totalRecords = pagination.getTotalRecords();//返回行数	
			List<SysUser> list = new ArrayList<SysUser>();
			for (int i = 0; i < resultList.size(); i++) {
				SysUser user = (SysUser)resultList.get(i);
				
				list.add(user);
			}
			
			if(totalRecords>0){
				resultMap.put("rows", list);
				resultMap.put("total", totalRecords);
				resultMap.put("status", true);
				resultMap.put("msg", "获取成功");
			}else {
				resultMap.put("status", false);
				resultMap.put("msg", "没有获取到数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param param
	 * @return
	 */
	@SysLog(operObject="管理员",operPath="user/admin_updateUserPassword.action",operType="修改密码")
	@RequestMapping(value="/admin_updateUserPassword.action", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserPassword(@RequestParam("newpassword") String password, HttpServletRequest request){
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		
		SysUser user = (SysUser) request.getSession().getAttribute("changedUser");
		int id = user.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);
		
		int rows = this.sysUserService.updateById(map);
		if (rows > 0) {
			resultMap.put("status", true);
			resultMap.put("msg", "修改成功");
		}else {
			resultMap.put("status", false);
			resultMap.put("msg", "修改失败");
		}
		return resultMap;
	}
	
	@RequestMapping("/admin_updateUser.action")
    public String toUpdatePassword(HttpServletRequest request, @RequestParam("id") int id){
    	
		SysUser user = sysUserService.selectById(id);
		request.getSession().setAttribute("changedUser", user);
		
    	return "user/change-password";
    }
	
	
	@SysLog(operObject="管理员",operPath="user/admin_deleteUser.action",operType="删除用户")
	@RequestMapping("/admin_deleteUser.action")
	public String deleteUser(@RequestParam("id") int id){
		
		sysUserService.deleteById(id);
		
		return "forward:getUserByPage.action";
	}
	
}
