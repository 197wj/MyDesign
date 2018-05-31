package com.design.service.farmland;

import java.util.List;
import java.util.Map;

import com.design.model.vo.SysForecastVO;
import com.design.model.vo.SysReportVO;

public interface SysReportService {

	/**
	 * 日报表：获取指定时间段内指定农田的三项日均值
	 * @param map	map 中有三个参数 Integer farmlandId, Date startTime, Date endTime
	 * @return
	 */
	List<SysReportVO> dayReportFarmland(Map<String, Object> map);
	
	/**
	 * 月报表：该月每隔一周一条数据
	 * @param map	map 中有两个参数 Integer farmlandId, Date monthTime
	 * @return
	 */
	List<SysReportVO> monthReportFarmland(Map<String, Object> map);
	
	/**
	 * 年报表：获取farmland_id和十二个月的每月的三个平均数
	 * @param map	map 中有两个参数 Integer farmlandId, Date yearTime
	 * @return
	 */
	List<SysReportVO> yearReportFarmland(Map<String, Object> map);
	
	/**
	 * 农田1号预测土壤湿度
	 * @return
	 */
	List<SysForecastVO> forecastFm1();
	
	List<SysForecastVO> forecastFm2();
	
	List<SysForecastVO> forecastFm3();
}
