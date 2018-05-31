package com.design.controller.farmland;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.model.vo.SysForecastVO;
import com.design.model.vo.SysReportVO;
import com.design.service.farmland.SysReportService;

@Controller
@RequestMapping("report")
public class SysReportController {

	@Autowired
	private SysReportService reportService;
	
	@RequestMapping("/toGetDayReport.action")
	public String toGetDayReport(){
		
		return "chart/chart-day";
	}
	
	@ResponseBody
	@RequestMapping(value="/getDayReport.action")
	public Map<String, Object> dayReport(@RequestParam(value="farmlandId", defaultValue="1") Integer farmlandId
							,@RequestParam(value="startTime", defaultValue="2018-05-25") String startTime
							,@RequestParam(value="endTime", defaultValue="2018-05-31") String endTime) throws ServletException, IOException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("farmlandId", farmlandId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		List<SysReportVO> vo = reportService.dayReportFarmland(map);
		
		List<String> listTime = new ArrayList<String>();
		List<Double> listFm = new ArrayList<Double>();
		List<Double> listAt = new ArrayList<Double>();
		List<Double> listAm = new ArrayList<Double>();
		
		for (SysReportVO vo1 : vo) {
			
			listTime.add(vo1.getTestTime());
			listFm.add(vo1.getAvgFarmlandMoisture());
			listAt.add(vo1.getAvgAirTemp());
			listAm.add(vo1.getAvgAirMoisture());
		}
		
		Map<String, Object> resultMap = new LinkedHashMap<String ,Object>();
		resultMap.put("listTime", listTime);
		resultMap.put("listFm", listFm);
		resultMap.put("listAt", listAt);
		resultMap.put("listAm", listAm);
		
		return resultMap;
	}
	
	@RequestMapping("/toGetMonthReport.action")
	public String toGetMonthReport(){
		
		return "chart/chart-month";
	}
	
	@ResponseBody
	@RequestMapping(value="/getMonthReport.action")
	public Map<String, Object> monthReport(@RequestParam(value="farmlandId", defaultValue="1") Integer farmlandId
							,@RequestParam(value="monthTime",defaultValue="2018-05-01") String monthTime) throws ServletException, IOException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("farmlandId", farmlandId);
		map.put("monthTime", monthTime);
		
		List<SysReportVO> vo = reportService.monthReportFarmland(map);
		
		List<String> listTime = new ArrayList<String>();
		List<Double> listFm = new ArrayList<Double>();
		List<Double> listAt = new ArrayList<Double>();
		List<Double> listAm = new ArrayList<Double>();
		
		for (SysReportVO vo1 : vo) {
			
			listTime.add(vo1.getTestTime());
			listFm.add(vo1.getAvgFarmlandMoisture());
			listAt.add(vo1.getAvgAirTemp());
			listAm.add(vo1.getAvgAirMoisture());
		}
		
		Map<String, Object> resultMap = new LinkedHashMap<String ,Object>();
		resultMap.put("listTime", listTime);
		resultMap.put("listFm", listFm);
		resultMap.put("listAt", listAt);
		resultMap.put("listAm", listAm);
		
		return resultMap;
	}
	
	@RequestMapping("/toGetYearReport.action")
	public String toGetYearReport(){
		
		return "chart/chart-year";
	}
	
	@ResponseBody
	@RequestMapping(value="/getYearReport.action")
	public Map<String, Object> yearReport(@RequestParam(value="farmlandId", defaultValue="1") Integer farmlandId
							,@RequestParam(value="yearTime", defaultValue="2018-05-01") String yearTime) throws ServletException, IOException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("farmlandId", farmlandId);
		map.put("yearTime", yearTime);
		
		List<SysReportVO> vo = reportService.yearReportFarmland(map);
		
		List<String> listTime = new ArrayList<String>();
		List<Double> listFm = new ArrayList<Double>();
		List<Double> listAt = new ArrayList<Double>();
		List<Double> listAm = new ArrayList<Double>();
		
		for (SysReportVO vo1 : vo) {
			
			listTime.add(vo1.getYearMonth());
			listFm.add(vo1.getAvgFarmlandMoisture());
			listAt.add(vo1.getAvgAirTemp());
			listAm.add(vo1.getAvgAirMoisture());
		}
		
		Map<String, Object> resultMap = new LinkedHashMap<String ,Object>();
		resultMap.put("listTime", listTime);
		resultMap.put("listFm", listFm);
		resultMap.put("listAt", listAt);
		resultMap.put("listAm", listAm);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/fm1.action")
	public List<SysForecastVO> forecastFm1(){
		
		List<SysForecastVO> list = reportService.forecastFm1();
		for (int i=0; i<list.size(); i++) {
			
			System.out.println("时间：" + list.get(i).getBeforeDay() + "----");
			System.out.println("平均值：" + list.get(i).getAvgFm() + "----");
			System.out.println("预测值：" + list.get(i).getForecastFm());
		}
		
		return reportService.forecastFm1();
	}
	
	@ResponseBody
	@RequestMapping("/fm2.action")
	public List<SysForecastVO> forecastFm2(){
		
		return reportService.forecastFm2();
	}
	
	@ResponseBody
	@RequestMapping("/fm3.action")
	public List<SysForecastVO> forecastFm3(){
		
		return reportService.forecastFm3();
	}
}
