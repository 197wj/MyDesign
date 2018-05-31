package com.design.service.farmland;

import java.util.List;
import java.util.Map;

import com.design.model.sys.SysFarmland;
import com.design.service.BaseService;

public interface SysFarmlandService extends BaseService<SysFarmland, Map<String, Object>>{

	/**
	 * 逐条添加数据 -- 返回该数据来与农作物进行对比
	 * @param record
	 * @return 返回该条数据
	 */
	SysFarmland insertFarmland(SysFarmland record);

    /**
     * 根据 id 获取该条数据 -- 在列表中通过 id 获取这条数据并比对合适农作物
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
}
