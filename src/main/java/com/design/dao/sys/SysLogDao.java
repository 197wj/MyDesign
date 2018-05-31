package com.design.dao.sys;

import java.util.List;
import java.util.Map;

import com.design.model.sys.SysLog;

public interface SysLogDao {

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer logId);

    List<SysLog> selectLogList(Map<String, Object> map);
    
    int selectLogCount(Map<String, Object> map);
    
    int deleteLogs(Integer[] ids);
}