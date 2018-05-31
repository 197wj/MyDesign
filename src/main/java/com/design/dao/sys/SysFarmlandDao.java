package com.design.dao.sys;

import java.util.List;
import java.util.Map;

import com.design.model.sys.SysFarmland;

public interface SysFarmlandDao {
	
	/**
	 * 逐条添加数据
	 * @param record
	 * @return 返回该条数据的 id 
	 */
	int insertFarmland(SysFarmland record);

    /**
     * 根据 id 获取该条数据
     * @param id
     * @return
     */
	SysFarmland selectById(Integer id);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
    int deleteByIds(List<Integer> ids);
    
    // 获取最新采集的数据
    Double getFm1();
    Double getFm2();
    Double getFm3();
    
    Double getAm();
    Double getAt();
    
    List<SysFarmland> selectFarmlandList(Map<String, Object> map);
    
    int selectFarmlandCount(Map<String, Object> map);
}