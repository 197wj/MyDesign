package com.design.controller.log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.dao.sys.SysLogDao;
import com.design.model.common.Pagination;
import com.design.service.log.LogService;

/**
 * 日志的控制层
 */
@Controller
@RequestMapping(value="log")
public class LogController {

	@Autowired
	private LogService logService;
	
	/**
	 * 跳转到日志列表页
	 * @return
	 */
	@RequestMapping(value="/logView.action")
	public String logView(){
		return "log/log-list";
	}
	
	/**
	 * 分页获取日志信息
	 * @return
	 */
	@RequestMapping(value="/getLogByPage.action")
	@ResponseBody
	public Map<String, Object> getLogByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
            								@RequestParam(value = "rows",defaultValue = "10") Integer rows,
            								@RequestParam(value = "startTime",required=false) String startTime,
            								@RequestParam(value = "endTime",required=false) String endTime){
		
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		try {
			List<Map<String, Object>> resultList = null;
			int totalRecords = 0;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			
			//调用service父类的方法,进行数据的查询
			Pagination<Map<String, Object>> pagination = logService.getPagingDatas(map, page, rows, "selectLogCount","selectLogList",SysLogDao.class);
			resultList = pagination.getPageList();//返回数据
			totalRecords = pagination.getTotalRecords();//返回行数	
			
			if(totalRecords>0){
				resultMap.put("rows", resultList);
				resultMap.put("total", totalRecords);
				resultMap.put("status", true);
				resultMap.put("msg", "获取成功");
			}else {
				resultMap.put("status", false);
				resultMap.put("msg", "没有获取到数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * 批量删除日志信息
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/admin_deleteLogs.action", method=RequestMethod.POST)
	public String deleteLogs(@RequestBody HashMap<String, List<String>> param){
	
		System.out.println("---------------------------------------");
		List<String> ids = param.get("ids");
		Integer[] idInt = new Integer[ids.size()];
		for(int i=0; i<ids.size();i++){
			idInt[i]=Integer.valueOf(ids.get(i));
		}
		
		logService.deleteLogs(idInt);
		
		return "forward:log/getLogByPage.action";
	}
	
}
