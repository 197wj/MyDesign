package com.design.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.design.model.sys.SysUser;
import com.design.service.BaseService;
import com.design.service.exception.UserExistException;

public interface SysUserService extends BaseService<SysUser, Map<String, Object>> {

	/**
	 * 登录
	 * @param phone
	 * @param pwd
	 * @return
	 */
	SysUser login(String phone , String pwd);
	
	/**
	 * 注册
	 * @param sysUser
	 * @return
	 */
	int register(SysUser sysUser) throws UserExistException;
	
	/**
	 * 注销
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 查看信息
	 * @param userId
	 * @return
	 */
	SysUser selectById(Integer userId);

	/**
	 * 修改密码
	 * @param record
	 * @return
	 */
    int updateById(Map<String, Object> map);
    
    /**
     * 分页展示用户列表
     * @param map
     * @return
     */
    List<SysUser> selectUserList(Map<String, Object> map);
    
    /**
     * 用户总数
     * @return
     */
    int getUserCount();
    
	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return
	 */
    SysUser getUserFromRequest(HttpServletRequest request);
}
