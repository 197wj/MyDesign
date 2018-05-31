package com.design.controller.crop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.aop.SysLog;
import com.design.dao.sys.SysCropDao;
import com.design.model.common.Pagination;
import com.design.model.sys.SysCrop;
import com.design.service.crop.SysCropService;
import com.design.service.exception.CropExistException;

@Controller
@RequestMapping("crop")
public class SysCropController {

	@Autowired
	private SysCropService sysCropService;
	
	/**
	 * 跳转到日志列表页
	 * @return
	 */
	@RequestMapping(value="/cropView.action")
	public String cropView(){
		return "crop/crop-list";
	}
	
	/**
	 * 分页获取农作物信息
	 * @return
	 */
	@RequestMapping(value="/getCropByPage.action")
	@ResponseBody
	public Map<String, Object> getCropByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
            								@RequestParam(value = "rows",defaultValue = "10") Integer rows,
            								@RequestParam(value = "cropName",required=false) String cropName){
		
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		try {
			List<Map<String, Object>> resultList = null;
			int totalRecords = 0;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cropName", cropName);
			
			//调用service父类的方法,进行数据的查询
			Pagination<Map<String, Object>> pagination = sysCropService.getPagingDatas(map, page, rows, "selectCropCount","selectCropList",SysCropDao.class);
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
	 * 批量删除农作物信息
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/admin_deleteCrops.action", method=RequestMethod.POST)
	@SysLog(operObject="管理员",operPath="crop/admin_deleteCrops.action",operType="批量删除农作物")
	public String deleteCrops(@RequestBody HashMap<String, List<String>> param){
	
		List<String> idsString = param.get("ids");
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for (String string : idsString) {
			
			ids.add(Integer.valueOf(string));
		}
		
		sysCropService.deleteByIds(ids);
		
		return "forward:crop/getCropByPage.action";
	}
	
	@RequestMapping("/toAddCrop.action")
	public String toAddCrop(){
		
		return "crop/crop-add";
	}
	
	@SysLog(operObject="用户",operPath="crop/addCrop.action",operType="添加农作物")
	@RequestMapping(value="/addCrop.action", method=RequestMethod.POST)
	public String addCrop(SysCrop sysCrop, HttpServletRequest request){
		
		try {
			
			sysCropService.insertCrop(sysCrop);
			return "crop/crop-list";
		} catch (CropExistException e) {

			request.getSession().setAttribute("error", "该名称的农作物信息已存在");
			return "error";
		}
	}
	
	/**
	 * 更新跳转
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateCrop.action")
    public String toUpdateCrop(HttpServletRequest request, @RequestParam("id") int id){
    	
		SysCrop crop = sysCropService.selectById(id);
		request.getSession().setAttribute("changedCrop", crop);
		
    	return "/crop/crop-change";
    }
	
	/**
	 * 农作物信息更新
	 * @param param
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SysLog(operObject="用户",operPath="crop/updateCropInfo.action",operType="更新农作物")
	@RequestMapping(value="/updateCropInfo.action", method=RequestMethod.POST)
	public void updateCrop( HttpServletRequest request,HttpServletResponse response,
													@RequestParam("id") Integer id,
													@RequestParam("cropLandMoisture") String cropLandMoisture,
													@RequestParam("cropAirMoisture") String cropAirMoisture,
													@RequestParam("cropAirTemp") String cropAirTemp) throws IOException, ServletException{
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cropLandMoisture", cropLandMoisture);
		map.put("cropAirMoisture", cropAirMoisture);
		map.put("cropAirTemp", cropAirTemp);
		
		int rows = this.sysCropService.updateByIdSelective(map);
		if (rows > 0) {
			resultMap.put("status", true);
			resultMap.put("msg", "修改成功");
		}else {
			resultMap.put("status", false);
			resultMap.put("msg", "修改失败");
		}
//		response.sendRedirect("../WEB-INF/crop/crop-list.jsp");
		// 请求转发
		request.getRequestDispatcher("../WEB-INF/crop/crop-list.jsp").forward(request, response);
	}
	
}
