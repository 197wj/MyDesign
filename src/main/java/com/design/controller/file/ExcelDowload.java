package com.design.controller.file;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.design.dao.sys.SysFarmlandDao;
import com.design.model.sys.SysFarmland;
import com.design.util.ExcelUtil;

@Controller
public class ExcelDowload {
	
	@Autowired
	private SysFarmlandDao sysFarmlandDao;


	@RequestMapping(value="/download.action",method=RequestMethod.GET)
	public void downExcel(HttpServletRequest request, HttpServletResponse response, 
							@RequestParam(value = "farmlandId",required=false) Integer farmlandId,
							@RequestParam(value = "startTime",required=false) String startTime,
							@RequestParam(value = "endTime",required=false) String endTime) throws IOException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("farmlandId", farmlandId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		List<SysFarmland> dataset = sysFarmlandDao.selectFarmlandList(map);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		
		
		//导出excel文件
		//设置文件名称
		String fileName = null;
		String []title = null;
		String [][]values = null;
		String sheetName = null;
		
		title = new String[]{"ID", "农田编号", "农田湿度", "空气温度", "空气湿度", "检测时间"};
		fileName = "农田信息数据";
		sheetName = "数据";
		values = new String[dataset.size()][];
		for(int i=0; i<dataset.size();i++){
			values[i] = new String[title.length];
			values[i][0] = dataset.get(i).getId()+"";
			values[i][1] = dataset.get(i).getFarmlandId()+"";
			values[i][2] = dataset.get(i).getFarmlandMoisture()+"";
			values[i][3] = dataset.get(i).getAirTemp()+"";
			values[i][4] = dataset.get(i).getAirMoisture()+"";
			values[i][5] = sdf.format(dataset.get(i).getTestTime())+"";
		}
		
		
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);
		 //将文件存到指定位置  
		try {  
     	
			response.reset();
	        response.setHeader("Content-disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1")+".xls");
	        response.setContentType("application/msexcel");  

          ServletOutputStream os = response.getOutputStream();
          
          
          wb.write(os);
          
          os.flush();  
          os.close();

		} catch (Exception e) {  
          e.printStackTrace();  
		}
		
	}
}
