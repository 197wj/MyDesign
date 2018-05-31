package com.design.service.crop.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dao.sys.SysCropDao;
import com.design.model.sys.SysCrop;
import com.design.service.BaseServiceImpl;
import com.design.service.crop.SysCropService;
import com.design.service.exception.CropExistException;

@Service
public class SysCropServiceImpl extends BaseServiceImpl<SysCrop, Map<String, Object>> implements SysCropService {

	@Autowired
	private SysCropDao sysCropDao;
	
	@Override
	public int insertCrop(SysCrop record) throws CropExistException {
		
		if (sysCropDao.selectByName(record.getCropName())!=null) {
			
			throw new CropExistException("农作物信息已存在");
		}else{
			
			return sysCropDao.insertCrop(record);
		}
	}

	@Override
	public int deleteByIds(List<Integer> ids) {
		
		return sysCropDao.deleteByIds(ids);
	}

	@Override
	public SysCrop selectById(Integer id) {

		return sysCropDao.selectById(id);
	}

	@Override
	public int updateByIdSelective(Map<String, Object> map) {
		
		return sysCropDao.updateByIdSelective(map);
	}

	@Override
	public List<SysCrop> getCropList() {

		Map<String, Object> map = new HashMap<>();
		
		return sysCropDao.selectCropList(map);
	}
}
