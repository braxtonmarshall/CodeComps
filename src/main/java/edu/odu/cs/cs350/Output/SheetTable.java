package edu.odu.cs.cs350.Output;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import edu.odu.cs.cs350.Interface.StudentPair;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/*
 *
 **/
public class SheetTable implements Cloneable
{
	
	private XSSFSheet sheettable;
	private TableType type;
	private ArrayList<Row> rows;
	private int rowCount;
	
	public enum TableType
	{
		RAWSCORES,
		REPORTS;
	}
	
	/**
	 * Default Constructor
	 */
	public SheetTable(XSSFSheet sheet, TableType type, XSSFCellStyle cellStyle)
	{
		this.sheettable = sheet;
		this.type = type;
		this.rows = new ArrayList<Row>();
		
		rows.add(new Row(this.type, this.sheettable.createRow(0), cellStyle ) );
		rowCount = 1;
	}
	
	/*
	 * Copy Constructor
	 */
	@SuppressWarnings("unchecked")
	public SheetTable(SheetTable tableCopy)
	{
		this.sheettable = tableCopy.sheettable;
		this.type = tableCopy.type;
		this.rowCount = tableCopy.rowCount;
		this.rows = (ArrayList<Row>)tableCopy.rows.clone();
	}
	
	/*
	 * 
	 */
	public void addRow(StudentPair stupair, XSSFCellStyle cellstyle)
	{
		rows.add(new Row(type, sheettable.createRow(rowCount) , stupair , cellstyle));
		rowCount++;
	}
	
	/*
	 * Override toString method
	 */
	@Override
	public String toString()
	{
		return sheettable.getSheetName();
	}
	
	/*
	 * Override clone method
	 */
	@Override
	public Object clone()
	{
		return new SheetTable(this);
	}
}