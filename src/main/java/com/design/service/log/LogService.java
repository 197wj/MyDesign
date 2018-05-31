package com.design.service.log;

import java.util.Map;

import com.design.model.sys.SysLog;
import com.design.service.BaseService;

/**
 * 日志的Service层
 */

public interface LogService extends BaseService<SysLog, Map<String, Object>> {

	int insert(SysLog record);

    SysLog selectById(Integer logId);
    
    int deleteLogs(Integer[] ids);
}
