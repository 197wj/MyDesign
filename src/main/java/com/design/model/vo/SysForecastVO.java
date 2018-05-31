package com.design.model.vo;

import java.util.Date;

public class SysForecastVO {

	private Date beforeDay;
	private double avgFm;
	private double forecastFm;
	
	public Date getBeforeDay() {
		return beforeDay;
	}
	public void setBeforeDay(Date beforeDay) {
		this.beforeDay = beforeDay;
	}
	public double getAvgFm() {
		return avgFm;
	}
	public void setAvgFm(double avgFm) {
		this.avgFm = avgFm;
	}
	public double getForecastFm() {
		return forecastFm;
	}
	public void setForecastFm(double forecastFm) {
		this.forecastFm = forecastFm;
	}
}
