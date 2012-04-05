package com.darkmi.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Description: 此类的内容完全等同于vod1.0中的BaseExcelActionSupport.java, 参照此类的命名, 此类被定位为: 提供一个public的设置固定excel样式的style的方法, 并提供对该style属性的get()方法
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-1-20 下午01:35:10 MagicY created
 */
public class BaseExcelStyleTemplate {
	//excel每个sheet的记录数,可根据需要调整
	public static final int EXCEL_SHEET_SIZE = 1000;
	
	//excel导出限制最大记录数,因为poi插件是将数字一次性写在内存中,数据量过大,会有风险
	public static final int EXCEL_LIMIT_SIZE = 10000;
	
	//excel导出警告文字
	public static final String EXCEL_LARGE_DATA_WARNING = "需导出数据量过大,请通过添加搜索条件查询,减少需导出数据量后,再进行导出工作";
	
	protected Map<String, CellStyle> styles;

	public Map<String, CellStyle> getStyles() {
		return styles;
	}

	/**
	 * Description: 将传入的Workbook设置统一、固定的style,完全从vod1.0 BaseExcelActionSupport.java类中的createStyles方法拷贝而来,未做任何改动
	 * @Version1.0 2011-1-20 下午01:39:59 MagicY created
	 * @param wb
	 * @return 
	 */
	public Map<String, CellStyle> createStyles(Workbook wb) {
		styles = new HashMap<String, CellStyle>();
		DataFormat df = wb.createDataFormat();

		// --字体设定 --//

		//普通字体
		Font normalFont = wb.createFont();
		normalFont.setFontHeightInPoints((short) 10);

		//加粗字体
		Font boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short) 10);
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		//蓝色加粗字体
		Font blueBoldFont = wb.createFont();
		blueBoldFont.setFontHeightInPoints((short) 10);
		blueBoldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		blueBoldFont.setColor(IndexedColors.BLUE.getIndex());

		// --Cell Style设定-- //

		//标题格式
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(boldFont);
		styles.put("header", headerStyle);

		//日期格式
		CellStyle dateCellStyle = wb.createCellStyle();
		dateCellStyle.setFont(normalFont);
		dateCellStyle.setDataFormat(df.getFormat("yyyy-MM-dd hh:mm:ss"));
		setBorder(dateCellStyle);
		styles.put("dateCell", dateCellStyle);

		//数字格式
		CellStyle numberCellStyle = wb.createCellStyle();
		numberCellStyle.setFont(normalFont);
		numberCellStyle.setDataFormat(df.getFormat("#,##0.00"));
		setBorder(numberCellStyle);
		styles.put("numberCell", numberCellStyle);

		//ID格式
		CellStyle idCellStyle = wb.createCellStyle();
		idCellStyle.setFont(normalFont);
		idCellStyle.setDataFormat(df.getFormat("0"));
		setBorder(idCellStyle);
		styles.put("idCell", idCellStyle);

		//合计列格式
		CellStyle totalStyle = wb.createCellStyle();
		totalStyle.setFont(blueBoldFont);
		totalStyle.setWrapText(true);
		totalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		setBorder(totalStyle);
		styles.put("total", totalStyle);

		return styles;
	}

	private void setBorder(CellStyle style) {
		//设置边框
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	}
}
