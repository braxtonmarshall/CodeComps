package edu.odu.cs.cs350.Output;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import edu.odu.cs.cs350.Interface.StudentPair;
import edu.odu.cs.cs350.Output.SheetTable.TableType;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class Row
{
	private XSSFRow row;
	
	/**
	 * Constructor for header
	 */
	public Row( TableType t , XSSFRow row, XSSFCellStyle cellstyle)
	{
		this.row = row;
		
		//headers to add
		XSSFCell field1 = this.row.createCell(0);
		XSSFCell field2 = this.row.createCell(1);
		XSSFCell field3 = this.row.createCell(2);
		
		field1.setCellValue("Student 1");
		field1.setCellStyle(cellstyle);
		
		field2.setCellValue("Student 2");
		field2.setCellStyle(cellstyle);
		
		field3.setCellValue("Raw Score");
		field3.setCellStyle(cellstyle);
		
		if(t == TableType.REPORTS)
		{
			XSSFCell field4 = this.row.createCell(3);
			
			field4.setCellValue("Z Score");
			field4.setCellStyle(cellstyle);
		}
	}
	/**
	 * Constructor for data
	 */
	public Row(TableType t, XSSFRow row, StudentPair stupair, XSSFCellStyle cellstyle)
	{
		this.row = row;
		
		XSSFCell field1 = this.row.createCell(0);
		XSSFCell field2 = this.row.createCell(1);
		XSSFCell field3 = this.row.createCell(2);
		
		field1.setCellValue(stupair.getFirstStudent());
		field1.setCellStyle(cellstyle);
		
		field2.setCellValue(stupair.getSecondStudent());
		field2.setCellStyle(cellstyle);
		
		field3.setCellValue(stupair.getRawScore());
		field3.setCellStyle(cellstyle);
		
		if(t == TableType.REPORTS)
		{
			XSSFCell field4 = this.row.createCell(3);
			
			field4.setCellValue("Z Score");
			field4.setCellStyle(cellstyle);
		}
	}
	
}