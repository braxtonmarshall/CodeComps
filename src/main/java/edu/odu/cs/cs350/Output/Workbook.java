package edu.odu.cs.cs350.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.odu.cs.cs350.Interface.StudentPair;
import edu.odu.cs.cs350.Output.SheetTable.TableType;

/**
 *
 * 
 */
public class Workbook
{
	
	private XSSFWorkbook wb;
	
	private XSSFCellStyle red;
	private XSSFCellStyle yellow;
	private XSSFCellStyle white;
	private XSSFCellStyle header;
	
	private SheetTable rawScores;
	private SheetTable reports;
	
	private File template;
	private String sname;
	
	/**
	 * Method to add raw score for a student pair
	 */
	private void addRowRawScore(StudentPair stupair)
	{
		rawScores.addRow(stupair, white);
	}
	/**
	 * Method to add student pair data to Reports table
	 */
	private void addRowReports(StudentPair stupair)
	{
		if (stupair.getZScore() < 2)
		{
			reports.addRow(stupair, white);
		}
		else if (stupair.getZScore() < 3)
		{
			reports.addRow(stupair, yellow);
		}
		else
		{
			reports.addRow(stupair, red);
		}
	}
	
	/**
	 * Method to set all cell styles for this workbook
	 */
	private void setAllCellStyles()
	{
		header = wb.createCellStyle();
		red = wb.createCellStyle();
		white = wb.createCellStyle();
		yellow = wb.createCellStyle();
		
		// Header Styles
		XSSFFont bold = wb.createFont();
		bold.setBold(true);
		
		header.setBorderBottom(BorderStyle.THIN);
		header.setBorderTop(BorderStyle.THIN);
		header.setBorderRight(BorderStyle.THIN);
		header.setBorderLeft(BorderStyle.THIN);
		header.setFont(bold);
		
		// White Background Cells
		white.setBorderBottom(BorderStyle.THIN);
		white.setBorderTop(BorderStyle.THIN);
		white.setBorderRight(BorderStyle.THIN);
		white.setBorderLeft(BorderStyle.THIN);
		
		// Red Backgrounded Cells
		red.setFillForegroundColor(new XSSFColor(Color.RED));
		red.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		red.setBorderBottom(BorderStyle.THIN);
		red.setBorderTop(BorderStyle.THIN);
		red.setBorderRight(BorderStyle.THIN);
		red.setBorderLeft(BorderStyle.THIN);
		
		// Yellow Background Cells
		yellow.setFillForegroundColor(new XSSFColor(Color.yellow));
		yellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		yellow.setBorderBottom(BorderStyle.THIN);
		yellow.setBorderTop(BorderStyle.THIN);
		yellow.setBorderRight(BorderStyle.THIN);
		yellow.setBorderLeft(BorderStyle.THIN);
	}
	
	/**
	 * Default Constructor
	 */
	public Workbook()
	{
		wb = new XSSFWorkbook();
		template = null;
		sname = "";
		
		setAllCellStyles();
		
		rawScores = new SheetTable( wb.createSheet("Raw Scores"), TableType.RAWSCORES, header);
		reports = new SheetTable( wb.createSheet("Reports"), TableType.REPORTS, header);
	}
	
	/**
	 * Alt. Constructor based on supplied Excel Template
	 */
	public Workbook(File template)
	{
		this.template = template;
		
		try
		{
			wb = new XSSFWorkbook( new FileInputStream (this.template));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		setAllCellStyles();
		
		rawScores = new SheetTable( wb.createSheet("Raw Scores"), TableType.RAWSCORES, header);
		reports = new SheetTable( wb.createSheet("Reports"), TableType.REPORTS, header);
	}
	
	/**
	 * Alt. Constructor based on supplied sheet name
	 */
	public Workbook(String sname)
	{
		wb = new XSSFWorkbook();
		this.sname = sname;
		this.template = null;
		
		setAllCellStyles();
		
		rawScores = new SheetTable( wb.createSheet("Raw Scores"), TableType.RAWSCORES, header);
		reports = new SheetTable( wb.createSheet("Reports"), TableType.REPORTS, header);
	}
	
	/**
	 * Alt. Constructor based on supplied template with a sheet name
	 */
	public Workbook(String sname, File template)
	{
		this.sname = sname;
		this.template = template;
		
		try
		{
			wb = new XSSFWorkbook( new FileInputStream (this.template));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		setAllCellStyles();
		
		rawScores = new SheetTable( wb.createSheet("Raw Scores"), TableType.RAWSCORES, header);
		reports = new SheetTable( wb.createSheet("Reports"), TableType.REPORTS, header);
	}
	
	/**
	 * Method to retrieve the template file
	 */
	public File getTemplate()
	{
		return template;
	}
	
	/**
	 * Method to retrieve the sheet name
	 */
	public String getSheetName()
	{
		return sname;
	}
	
	/**
	 * Method to write workbook to specified output stream
	 */
	public void writeToFile(FileOutputStream ostream)
	{
		try
		{
			wb.write(ostream);
			ostream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Add student pair to workbook
	 */
	public void addStudentPair(StudentPair stupair)
	{
		addRowRawScore(stupair);
		addRowReports(stupair);
	}
}