package com.design.service.farmland.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dao.sys.SysFarmlandDao;
import com.design.model.sys.SysFarmland;
import com.design.service.BaseServiceImpl;
import com.design.service.farmland.SysFarmlandService;

@Service
public class SysFarmlandServiceImpl extends BaseServiceImpl<SysFarmland, Map<String, Object>> implements SysFarmlandService {

	@Autowired
	private SysFarmlandDao sysFarmlandDao;
	
	@Override
	public SysFarmland insertFarmland(SysFarmland record) {
		
		return sysFarmlandDao.selectById(sysFarmlandDao.insertFarmland(record));
	}

	@Override
	public SysFarmland selectById(Integer id) {
		
		return sysFarmlandDao.selectById(id);
	}

	@Override
	public int deleteByIds(List<Integer> ids) {
		
		return sysFarmlandDao.deleteByIds(ids);
	}

	@Override
	public Double getFm1() {

		return sysFarmlandDao.getFm1();
	}

	@Override
	public Double getFm2() {

		return sysFarmlandDao.getFm2();
	}

	@Override
	public Double getFm3() {

		return sysFarmlandDao.getFm3();
	}

	@Override
	public Double getAm() {

		return sysFarmlandDao.getAm();
	}

	@Override
	public Double getAt() {

		return sysFarmlandDao.getAt();
	}

}
