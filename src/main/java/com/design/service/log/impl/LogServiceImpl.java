package com.design.service.log.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dao.sys.SysLogDao;
import com.design.model.sys.SysLog;
import com.design.service.BaseServiceImpl;
import com.design.service.log.LogService;

@Service
public class LogServiceImpl extends BaseServiceImpl<SysLog, Map<String, Object>> implements LogService {

	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public int insert(SysLog record) {

		return this.sysLogDao.insert(record);
	}
	
	@Override
	public SysLog selectById(Integer logId) {
		
		return this.sysLogDao.selectByPrimaryKey(logId);
	}

	@Override
	public int deleteLogs(Integer[] ids) {

		return sysLogDao.deleteLogs(ids);
	}
}