
/*
*   Authors:
*   Braxton Marshall
*   Hannah Holloway
*   CS350
*/

package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author braxton.marshall
 * @author holloway.hannah
 */

public class CodeComp
{	
	
	// Vector to store names of students
    private static Vector<Student> studentList;
    // Vector to store Source Files  to add to Students
    private static List<File> srcList;
    // Assignment Directory passed in via CLI
    private File rootDirectory;

	public CodeComp()
	{
		studentList = new Vector<Student>();
		srcList = new ArrayList<>();
		rootDirectory = null;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     CodeComp codecomp=new CodeComp();
    try
    {
        if(args.length>0)
        {
        
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<args.length;i++)
                {
                    sb.append(args[i]+" ");
                }

                if(sb.toString().contains("-help"))
                {
                    System.out.println("The two required parameters are the assignmentDirectory and output Spreadsheet");
                }

                else if(sb.toString().contains("-d"))
                {
                    String name=sb.substring( sb.indexOf("d"),sb.length() );
                    String filename="";
                    filename = name.substring( name.indexOf(" "),name.length() );
                    filename = filename.replace("\\", "\\\\");
                    codecomp.setRootDirectory(new File(filename.trim()));
                    codecomp.FindAllStudents();
                    for (Student s : studentList)
                    {	
                    	int count = 0;
                    	codecomp.listSrcFiles(s);
                    	for (File src : srcList)
                    	{
                    		s.appendFile(src);
                    		count = count + codecomp.countLinesOfCode(src);
                    	}
                    	s.setLinesofCode(count);
                    	System.out.println(s.toString());
                    }
                    /*
                    SpreedSheet ss=new SpreedSheet();
                    ss.report(studentList);
                    */
                }
        }
        else
        {
            System.out.println("required syntax: java -jar filename -d rootdirectoryname");
        }
    }catch(Exception e){System.out.println(e);}
      
      
      
    }
     
    
    


    /**
     * @return the rootDirectory
     */
    public File getRootDirectory() {
        return rootDirectory;
    }

    /**
     * @param rootDirectory the rootDirectory to set
     */

    public void setRootDirectory(File rootDirectory) 
    {
        this.rootDirectory = rootDirectory;
    }

    /**
     * 
     */
    public Vector<Student> getStudentList()
    {
		return studentList;
    }
    /**
     * 
     */
    public List<File> getsrcList()
    {
		return srcList;
    }
    /**
    *	Note: Method handles various versions submitted by the same
    *	Student.
     * 
    */
    public void FindAllStudents()
    {	
    	File dir = this.rootDirectory;
    	File[] files = dir.listFiles();
    	if ( files!= null && files.length > 0 )
    	{
    		for (File file : files )
    		{	
    			Student student = new Student();
    			if( file.isDirectory() );
    			{	
    				student.setRoot(file);
    				String filename = file.getName();
    				
                    /**
                    *   Save Version
                    */
                    String extension = "";
                    int i = filename.lastIndexOf('.');
                    if (i>0) 
                    {	
                    	String studentname = filename.substring(0, i);
                    	student.setName(studentname);
                    	extension = filename.substring(i+1);
                    	student.setVersion(Integer.parseInt(extension));
                    	
                    }
                    if (i == -1)
                    {
                    	student.setName(filename);
                    }
                    
                    /**
                     * Add Student to StudentList - handling cases of multiple versions
                     */
                    
                    if (studentList.contains(student))
                    {
                    	int index = studentList.indexOf(student);
                    	Student student_two = studentList.get(index);
                    	
                    	if (student_two.getVersion() != -1 && student.getVersion() == -1)
                    	{	
                    		studentList.remove(student_two);
                			studentList.add(student);
                    	}
                    	
                    	else if (student_two.getVersion() != -1 && student.getVersion() != -1)
                    	{
                    		if(student.getVersion() > student_two.getVersion())
                    		{
                    			studentList.remove(student_two);
                    			studentList.add(student);
                    			
                    		}
                    	}
                    }
                    /**
                     * Not In List
                     */
                    else
                    {
                    	studentList.add(student);
                    }
                    
    			}
    		}
        }
    }   
    /**
     * @throws IOException 
     * 
     */
    public int countLinesOfCode(File srcfile) throws IOException
    {
    	BufferedReader reader = new BufferedReader(new FileReader(srcfile.getAbsolutePath()));
        int linecount = 0;
        while (reader.readLine() != null){ linecount++; }
        reader.close();
        return linecount;
    }
    /**
     * @throws IOException 
     * 
     *
     * 
     */
    public void listSrcFiles(Student student) throws IOException
    {	
    	srcList.clear();
    	Path path = student.getRoot().toPath();
    	srcList = Files.walk(path)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
    

	
	public void setStudentList(Vector<Student> studentList) {
		CodeComp.studentList = studentList;
	}

}
