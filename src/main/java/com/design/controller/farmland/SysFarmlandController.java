package com.design.controller.farmland;

import java.util.ArrayList;
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

import com.design.aop.SysLog;
import com.design.dao.sys.SysFarmlandDao;
import com.design.model.common.Pagination;
import com.design.service.farmland.SysFarmlandService;

@Controller
@RequestMapping("/farmland")
public class SysFarmlandController {

	@Autowired
	private SysFarmlandService sysFarmlandService;
	
	// 跳转到列表数据页面
	@RequestMapping("/farmlandView.action")
	public String farmlandView(){
		
		return "farmland/farmland-list";
	}
	
	// 跳转到添加数据界面
	@RequestMapping("/toAddFarmland.action")
	public String toAddFarmland(){
		
		return "farmland/farmland-add";
	}
	
	// 跳转到数控界面--打开串口读数据
	@RequestMapping("/farmlandRead.action")
	public String farmlandRead(){
		
		return "farmland/farmland-read";
	}
	
	// 跳转到数据预测界面
	@RequestMapping("/farmlandForecast.action")
	public String farmlandForecast(){
		
		return "farmland/farmland-forecast";
	}
	
	/**
	 * 分页获取农田信息
	 * @return
	 */
	@RequestMapping(value="/getFarmlandByPage.action")
	@ResponseBody
	public Map<String, Object> getFarmlandByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
            								@RequestParam(value = "rows",defaultValue = "10") Integer rows,
            								@RequestParam(value = "farmlandId",required=false) Integer farmlandId,
            								@RequestParam(value = "startTime",required=false) String startTime,
            								@RequestParam(value = "endTime",required=false) String endTime){

		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		try {
			List<Map<String, Object>> resultList = null;
			int totalRecords = 0;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("farmlandId", farmlandId);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			
			//调用service父类的方法,进行数据的查询
			Pagination<Map<String, Object>> pagination = sysFarmlandService.getPagingDatas(map, page, rows, "selectFarmlandCount","selectFarmlandList",SysFarmlandDao.class);
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
	 * 批量删除农田信息
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/admin_deleteFarmlands.action", method=RequestMethod.POST)
	@SysLog(operObject="管理员",operPath="farmland/admin_deleteFarmlands.action",operType="批量删除农田信息")
	public String deletefarmlands(@RequestBody HashMap<String, List<String>> param){
	
		List<String> idsString = param.get("ids");
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for (String string : idsString) {
			
			ids.add(Integer.valueOf(string));
		}
		
		sysFarmlandService.deleteByIds(ids);
		
		return "forward:farmland/getFarmlandByPage.action";
	}
	
	@ResponseBody
	@RequestMapping("/getFm1.action")
	public Double getFm1(){
		
		return sysFarmlandService.getFm1();
	}
	
	@ResponseBody
	@RequestMapping("/getFm2.action")
	public Double getFm2(){
		
		return sysFarmlandService.getFm2();
	}
	
	@ResponseBody
	@RequestMapping("/getFm3.action")
	public Double getFm3(){
		
		return sysFarmlandService.getFm3();
	}
	
	@ResponseBody
	@RequestMapping("/getAm.action")
	public Double getAm(){
		
		return sysFarmlandService.getAm();
	}
	
	@ResponseBody
	@RequestMapping("/getAt.action")
	public Double getAt(){
		
		return sysFarmlandService.getAt();
	}

}
