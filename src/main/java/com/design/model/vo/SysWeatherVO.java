package com.design.model.vo;

public class SysWeatherVO {

	private String dayTime;
	private String weather;
	private String temp;
	private String wind;
	
	@Override
	public String toString() {
		return "SysWeatherVO [dayTime=" + dayTime + ", weather=" + weather + ", temp=" + temp + ", wind=" + wind + "]";
	}
	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
}
