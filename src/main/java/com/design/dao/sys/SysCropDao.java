package com.design.dao.sys;

import java.util.List;
import java.util.Map;

import com.design.model.sys.SysCrop;

public interface SysCropDao {

	int insertCrop(SysCrop record);
	
	int deleteByIds(List<Integer> ids);

	SysCrop selectById(Integer id);
	
	/**
	 * 根据农作物名称获取农作物，避免存在重复
	 * @param cropName
	 * @return
	 */
	SysCrop selectByName(String cropName);

	int updateByIdSelective(Map<String, Object> map);

	List<SysCrop> selectCropList(Map<String, Object> map);

	int selectCropCount(Map<String, Object> map);
}