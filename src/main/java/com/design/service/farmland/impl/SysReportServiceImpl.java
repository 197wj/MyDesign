package com.design.service.farmland.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dao.sys.SysReportDao;
import com.design.model.vo.SysForecastVO;
import com.design.model.vo.SysReportVO;
import com.design.service.farmland.SysReportService;

@Service
public class SysReportServiceImpl implements SysReportService {

	@Autowired
	private SysReportDao reportDao;
	
	@Override
	public List<SysReportVO> dayReportFarmland(Map<String, Object> map) {

		return reportDao.dayReportFarmland(map);
	}

	@Override
	public List<SysReportVO> monthReportFarmland(Map<String, Object> map) {

		return reportDao.monthReportFarmland(map);
	}

	@Override
	public List<SysReportVO> yearReportFarmland(Map<String, Object> map) {

		return reportDao.yearReportFarmland(map);
	}

	@Override
	public List<SysForecastVO> forecastFm1() {
		
		return reportDao.forecastFm1();
	}

	@Override
	public List<SysForecastVO> forecastFm2() {

		return reportDao.forecastFm2();
	}

	@Override
	public List<SysForecastVO> forecastFm3() {

		return reportDao.forecastFm3();
	}
}
