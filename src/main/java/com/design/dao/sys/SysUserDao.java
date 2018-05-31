package com.design.dao.sys;

import java.util.List;
import java.util.Map;

import com.design.model.sys.SysUser;

public interface SysUserDao {
	
	/**
     * 登录
     * @param map
     * @return
     */
    SysUser login(Map<String, Object> map);

    /**
     * 注册，根据输入的列数进行保存
     * @param record
     * @return
     */
    int insertSelective(SysUser record);
    
    /**
     * 根据手机后获取用户
     * @param phone
     * @return
     */
    SysUser getUserByPhone(String phone);
    
    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 更新密码
     * @param record
     * @return
     */
    int updateUserPassword(Map<String, Object> map);

    /**
     * 查看用户信息
     * @param id
     * @return
     */
    SysUser getUserById(Integer id);

    /**
     * 分页获取用户列表
     * @param map
     * @return
     */
    List<SysUser> selectUserList(Map<String, Object> map);
    
    /**
     * 获取用户数量
     * @param map
     * @return
     */
    Integer selectUserCount();
    
}