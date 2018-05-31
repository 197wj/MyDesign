package com.design.service.crop;

import java.util.List;
import java.util.Map;

import com.design.model.sys.SysCrop;
import com.design.service.BaseService;
import com.design.service.exception.CropExistException;

public interface SysCropService extends BaseService<SysCrop, Map<String, Object>> {

	/**
	 * 添加农作物信息
	 * @param record
	 * @return
	 */
	int insertCrop(SysCrop record) throws CropExistException;

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteByIds(List<Integer> ids);

	/**
	 * 根据 id 获取农作物信息
	 * @param id
	 * @return
	 */
	SysCrop selectById(Integer id);

	/**
	 * 更新农作物信息
	 * @param map
	 * @return
	 */
	int updateByIdSelective(Map<String, Object> map);
	
	/**
	 * 获取所有的Crop信息
	 * @return
	 */
	List<SysCrop> getCropList();
}
