package com.design.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dao.sys.SysUserDao;
import com.design.model.sys.SysUser;
import com.design.service.BaseServiceImpl;
import com.design.service.exception.UserExistException;
import com.design.service.user.SysUserService;
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Map<String, Object>> implements SysUserService {

	@Autowired
	private SysUserDao  sysUserDao;
	
	@Override
	public SysUser login(String phone, String pwd) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("password", pwd);
		
		return this.sysUserDao.login(map);
	}
	
	@Override
	public int register(SysUser sysUser) throws UserExistException {

		SysUser existUser = sysUserDao.getUserByPhone(sysUser.getPhone());
		
		if (existUser == null) {
			
			return sysUserDao.insertSelective(sysUser);
		}else{
			
			throw new UserExistException("手机号已注册");
		}
		
	}

	@Override
	public int deleteById(Integer id) {

		return sysUserDao.deleteById(id);
	}
	
	@Override
	public SysUser selectById(Integer userId) {

		return this.sysUserDao.getUserById(userId);
	}

	@Override
	public int updateById(Map<String, Object> map) {

		return this.sysUserDao.updateUserPassword(map);
	}
	
	@Override
	public List<SysUser> selectUserList(Map<String, Object> map) {

		return this.sysUserDao.selectUserList(map);
	}

	@Override
	public int getUserCount() {

		return this.sysUserDao.selectUserCount();
	}

	@Override
    public SysUser getUserFromRequest(HttpServletRequest request) {
        SysUser user  = (SysUser) request.getSession().getAttribute("user");
        return user;
    }
}
