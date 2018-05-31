package com.design.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class ExcelUtil {

	
	public static HSSFWorkbook getHSSFWorkbook(String sheetName,
							String[] title, String[][] values, HSSFWorkbook wb) {
		
		// 第一步，创建一个webbook，对应一个Excel文件
		if (wb == null) {
			wb = new HSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		
			HSSFSheet sheet = wb.createSheet(sheetName);
			
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			// 设置默认列宽
	        sheet.setDefaultColumnWidth(15);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
//			style.setWrapText(true);                          //设置自动换行
			HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
			headerFont.setFontName("Times New Roman");  //设置字体类型  
			headerFont.setFontHeightInPoints((short) 14);    //设置字体大小  
			style.setFont(headerFont);    //为标题样式设置字体样式
			
			//设置excel内容的样式
			HSSFCellStyle cell_Style = (HSSFCellStyle) wb .createCellStyle();// 设置字体样式  
			cell_Style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			cell_Style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中  
//			cell_Style.setWrapText(true); // 设置为自动换行  
			HSSFFont cell_Font = (HSSFFont) wb.createFont();  
			cell_Font.setFontName("宋体");  
			cell_Font.setFontHeightInPoints((short) 12);  
			cell_Style.setFont(cell_Font);  
			
			
			
			
			
			HSSFCell cell = null;
			for (int i = 0; i < title.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
				cell.setCellStyle(style);
			}
			
			// 创建内容
			for (int i = 0; i < values.length; i++) {
				row = sheet.createRow(i + 1);
				for (int j = 0; j < values[i].length; j++) {
					cell = row.createCell(j);
					cell.setCellValue(values[i][j]);
					cell.setCellStyle(cell_Style);
				}
			}
		return wb;
	}
	
}
