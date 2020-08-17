package edu.odu.cs.cs350;

/*
 *  reads input, matches the input against 
 *  the regular expressions in the spec file, and 
 *  runs the corresponding action if a regular expression matched.
 */
import java.io.*;


import java_cup.runtime.*;



public class JavaParser {
  int intDec = 37;
  long longDec = 37l;
  int intHex = 0x0001;
  long longHex = 0xFFFFl;
  int intOct = 0377;
  long longOc = 007l;
  int smallest = -2147483648;   

  public String parse(File src) {   
      String str="";
      if(src!=null)
      {
      try {      
       Symbol s;
       Scanner scanner = new Scanner(new UnicodeEscapes(new FileReader(src)));
       //scanner.yytext().replaceAll("@", "");
       s=scanner.next_token();

       while(s.sym!=sym.EOF)
       {
         try{
         //scanner.yytext().replaceAll("@", "");
         s=scanner.next_token();
         //System.out.println(s);
         if(s.value!=null&&!s.value.equals(""))
         {
         scanner.string.append(s.value+":"+s.sym+":");
         
         }
         }catch(jflex.ScannerException se){System.out.println(se);} 
       }
       //System.out.println("tokens "+s.string);
       //System.out.println("No errors.");

       str=scanner.string.toString();
       
     }
     catch (Exception e) {
       e.printStackTrace(System.out);
       System.exit(1);
     }
      }
return str;
 }

 

}