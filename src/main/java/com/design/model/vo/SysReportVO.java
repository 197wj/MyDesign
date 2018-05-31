package com.design.model.vo;

import java.time.YearMonth;
import java.util.Date;

public class SysReportVO {

	private Integer farmlandId;

    private Double avgFarmlandMoisture;

    private Double avgAirTemp;

    private Double avgAirMoisture;

    private String testTime;
    
    private String yearMonth;
    
	public SysReportVO() {
		super();
	}

	public Integer getFarmlandId() {
		return farmlandId;
	}

	public void setFarmlandId(Integer farmlandId) {
		this.farmlandId = farmlandId;
	}

	public Double getAvgFarmlandMoisture() {
		return avgFarmlandMoisture;
	}

	public void setAvgFarmlandMoisture(Double avgFarmlandMoisture) {
		this.avgFarmlandMoisture = avgFarmlandMoisture;
	}

	public Double getAvgAirTemp() {
		return avgAirTemp;
	}

	public void setAvgAirTemp(Double avgAirTemp) {
		this.avgAirTemp = avgAirTemp;
	}

	public Double getAvgAirMoisture() {
		return avgAirMoisture;
	}

	public void setAvgAirMoisture(Double avgAirMoisture) {
		this.avgAirMoisture = avgAirMoisture;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
}
