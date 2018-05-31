package com.design.controller.similar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.model.sys.SysCrop;
import com.design.model.sys.SysFarmland;
import com.design.service.crop.SysCropService;
import com.design.service.farmland.SysFarmlandService;
import com.design.util.ValueCompareUtil;

/**
 * 获取农田信息与对应的农作物相似度
 * @author 王静
 *
 */
@Controller
@RequestMapping("similar")
public class SimilarController {

	@Autowired
	private SysFarmlandService sysFarmlandService;
	
	@Autowired
	private SysCropService sysCropService;

	/**
	 * 添加农田检测数据
	 * @param sysFarmland
	 * @return	相似度最高的6个农作物信息
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/insertFarmland.action",method=RequestMethod.POST)
	public Integer insertFarmland(HttpServletRequest request, HttpServletResponse response
								,@RequestParam("farmlandId") Integer farmlandId
								,@RequestParam("farmlandMoisture") Double farmlandMoisture
								,@RequestParam("airMoisture") Double airMoisture
								,@RequestParam("airTemp") Double airTemp
								,@RequestParam("testTime") String testTime) throws ServletException, IOException, ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		SysFarmland sysFarmland = new SysFarmland();
		sysFarmland.setFarmlandId(farmlandId);
		sysFarmland.setAirMoisture(airMoisture);
		sysFarmland.setAirTemp(airTemp);
		sysFarmland.setFarmlandMoisture(farmlandMoisture);
		if (testTime != "" && testTime != null) {
			sysFarmland.setTestTime(formatter.parse(testTime));
		}else{
			sysFarmland.setTestTime(null);
		}
		
		
		sysFarmlandService.insertFarmland(sysFarmland);
		
		return sysFarmland.getId();
	}
	
	
	/**
	 * 根据 id 查询该条农田数据对应的农作物相似度
	 * @param id
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/selectFarmlandInfo.action")
	public void selectFarmlandInfo(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setAttribute("similarCropList", getSimilar(id));
		
		request.getRequestDispatcher("../WEB-INF/chart/chart-radar.jsp").forward(request, response);
	}
	
	
	/**
	 * 农田信息和农作物信息的相似度算法
	 * @param id 检测的农田信息数据的id
	 * @return	List<Map<String, Double>> 
	 * 			list： 存放6个对应的相似度最高的农作物
	 * 			map：
	 * 				key：	农作物名称
	 * 				value：	相似度大小
	 */
	public List<Map.Entry<String, Double>> getSimilar(int id){
		
		SysFarmland farmland = sysFarmlandService.selectById(id);
		List<SysCrop> CropList = sysCropService.getCropList();
		
		// 定义一个集合存放 crop 的数据数组
		List<Double[]> listCrops = new ArrayList<Double[]>();
		
		// 遍历CropList集合，将信息数据存放在上述定义的集合中
		for (SysCrop sysCrop : CropList) {
			
			// 在 数组中索引为 0 处存放 crop 的id，方便获取相似度对应的 crop 
			Double[] cropInfo = {sysCrop.getId().doubleValue(), sysCrop.getCropLandMoisture(),sysCrop.getCropAirMoisture(),sysCrop.getCropAirTemp()};
			listCrops.add(cropInfo);
		}
		
		// 使用算数平均最小法计算相似度
		// 定义一个 Map 集合，key 为crop 的 名称， value 为 对应的 crop 的相似度
		Map<String, Double> similarMap = new HashMap<String, Double>();
		
		for (int i = 0; i < listCrops.size(); i++) {
			
			// 根据主键获取map 集合中的 crop 各项指标数据数组  
			// --------- 参数 i是 hashmap 中的key，设置的key为 crop 的id，， 如果有些数据已经删除，其所占用的 id 不存在，会产生空指针异常，id 在 i 之后的数据也获取不到
			Double[] cropInfo = listCrops.get(i);
			
			double x1 = farmland.getFarmlandMoisture();
			double x2 = farmland.getAirMoisture();
			double x3 = farmland.getAirTemp();
			
			double y1 = cropInfo[1];
			double y2 = cropInfo[2];
			double y3 = cropInfo[3];
			
			double a1 = x1 < y1 ? x1 : y1;
			double a2 = x2 < y2 ? x2 : y2;
			double a3 = x3 < y3 ? x3 : y3;
			
			double similar = 2.0*(a1+a2+a3)/(x1+y1+x2+y2+x3+y3);
			
			// 获取当前循环内的 cropId 和与 farmland 的相似度
			similarMap.put(sysCropService.selectById(cropInfo[0].intValue()).getCropName(), similar);
		}
		
		// 使用 Entry 和 Collection.sort 方法按照 value 即相似度进行排序
		List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>();
		
		// list 中含有所有的农作物信息和对应的相似度
		list.addAll(similarMap.entrySet());
		
		// 自定义的Map 中 value 排序方法，使用 Collections.sort(list, vcu) 实现对 list 集合中的map元素的value排序
		ValueCompareUtil vcu = new ValueCompareUtil();
        Collections.sort(list, vcu);
		
		return list;
	}
}
