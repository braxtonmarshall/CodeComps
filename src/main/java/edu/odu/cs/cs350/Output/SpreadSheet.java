package edu.odu.cs.cs350;

import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author holloway.hannah
 */
//h
public class SpreadSheet {
public void report(Vector<Student> studentlist)
{
try
{
 Vector<Student> copy=new Vector<Student>();
 for(int k=0;k<studentlist.size();k++)
 {
     copy.add(studentlist.get(k));
 }
 Map<String,Score> map=new LinkedHashMap<String,Score>();
 double tot=0.0;
 double avg=0.0;
 double count2=0.0;
  for(int i=0;i<studentlist.size();i++)
 {
   Student s1=studentlist.get(i);
  for(int j=0;j<copy.size();j++)
  {
   Student s2=copy.get(j);
   if(map.get(s1.getName()+":"+s2.getName())==null&&map.get(s2.getName()+":"+s1.getName())==null&&!s1.getName().equals(s2.getName()))
   {
       Score score=new Score();
       //LexAnalyzer l=new LexAnalyzer();
       //l.find(studentlist);
       score.score(s1, s2);
       map.put(s1.getName()+":"+s2.getName(), score);
       tot=tot+score.getRawScore();
       count2=count2+1.0;

   }
  }
 }
 avg=tot/count2;
 Double std=0.0;
  for(Map.Entry<String,Score> entry:map.entrySet())
 {
  String key=entry.getKey();
  Score score=entry.getValue();
  score.setMean(avg);;
  std=std+Math.pow(score.getRawScore()-score.getMean(),2);
 }
  std=std/count2;
  std=Math.sqrt(std);
   for(Map.Entry<String,Score> entry:map.entrySet())
 {
  String key=entry.getKey();
  Score score=entry.getValue();
  score.setStandardDev(std);
  score.setMean(avg);
  score.setZscore((score.getRawScore()-score.getMean())/score.getStandardDev());
 }
 FileOutputStream fos=new FileOutputStream("report.xls");
 XSSFWorkbook workbook=new XSSFWorkbook();
 XSSFSheet sheet1=workbook.createSheet("CodeComp");
 Row row = sheet1.createRow(0);   
 row.createCell(0);
 row.createCell(1);
 row.createCell(2);
 row.createCell(3);
 //row.createCell(4);
 //row.createCell(5);
 row.getCell(0).setCellValue("Student 1");
 row.getCell(1).setCellValue("Student 2");
 row.getCell(2).setCellValue("Raw Score");
 row.getCell(3).setCellValue("Z Score");
 //row.getCell(4).setCellValue("Avg");
 //row.getCell(5).setCellValue("STD");
 int count=1;

 for(Map.Entry<String,Score> entry:map.entrySet())
 {
  String key=entry.getKey();
  Score score=entry.getValue();
  String student1=key.substring(0,key.indexOf(":"));
  String student2=key.substring(key.indexOf(":")+1,key.length());
  row = sheet1.createRow(count);
  row.createCell(0);
  row.createCell(1);
  row.createCell(2);
  row.createCell(3);
  //row.createCell(4);
  //row.createCell(5);  
  row.getCell(0).setCellValue(student1);
  row.getCell(1).setCellValue(student2);
  row.getCell(2).setCellValue(score.getRawScore());
  row.getCell(3).setCellValue(Math.round(score.getZscore()*100.0)/100.0); 
  //row.getCell(4).setCellValue(score.getMean());
  //row.getCell(5).setCellValue(Math.round(score.getStandardDev()*100.0)/100.0);  
  count++;
 }
 workbook.write(fos);
 workbook.close();
}catch(Exception e){System.out.println(e);}
}
}