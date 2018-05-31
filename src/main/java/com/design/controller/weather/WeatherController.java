package com.design.controller.weather;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.model.vo.SysWeatherVO;

@Controller
public class WeatherController {

	@ResponseBody
	@RequestMapping(value="/getWeather.action",method=RequestMethod.GET)
	public Map<String, Object> getWeather(@RequestParam(value="cityId",defaultValue="101180101") String cityId) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		String url = "http://www.weather.com.cn/weather/" + cityId + ".shtml";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements content = doc.getElementsByClass("con today clearfix");
			for (Element e : content) {
				Document conDoc = Jsoup.parse(e.toString());
				// 地点
				Elements cru = conDoc.getElementsByClass("crumbs fl");
				
				// 未来七日的天气状态
				Elements sky = content.select("li[class^=sky skyid lv]");
				
				List<SysWeatherVO> weatherList = new ArrayList<>();
				for (Element element : sky) {
					
					String[] weatherInfo = element.text().split(" ");
					SysWeatherVO vo = new SysWeatherVO();
					vo.setDayTime(weatherInfo[0]);
					vo.setWeather(weatherInfo[1]);
					vo.setTemp(weatherInfo[2]);
					vo.setWind(weatherInfo[3]);
					
					weatherList.add(vo);
				}

				map.put("city", cru.text());
				map.put("weather", weatherList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(map.get("city"));
		System.out.println(map.get("weather"));
		
		return map;
	}
}
