package edu.odu.cs.cs350;

import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class Student {
	
	// Name of Student Object
	private String name;
	
    // List of Source File locations found in the Directory
    private Vector<File> fileList;
    
    // Lines of Code found within the Student Directory based on the directory's Source Files
    private int lines_of_code;

    // Version of the Student Directory
    // Note: In cases where the Student Directory has no version, the version number will be set to -1
    private int version;
    
    // Root Directory for Student
    private File rootDirectory;

    /**
    *   Default Student Constructor
    */ 
    public Student()
    {
        name = "";
        fileList = new Vector<File>();
        lines_of_code = 0;
        version = -1;
    }

    /**
    *   Student Constructor
    */
    public Student(String name)
    {
        this.name = name;
        fileList = new Vector<File>();
        this.lines_of_code = 0;
        this.version = -1;
    }

    /**
     * @return the name of the Student
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
    *   @return Total Lines of Code for Student
    */
    public int getLinesofCode()
    {
        return lines_of_code;
    }

    /**
    *   @param code Set the total Lines of Code for Student
    */
    public void setLinesofCode(int code)
    {
        this.lines_of_code = code;
    }

    /**
    *   @return Version Number of this Student Directory
    */
    public int getVersion()
    {
        return version;
    }

    /**
    *   @param v the version of the Student Directory
    */
    public void setVersion(int v)
    {
        this.version = v;
    }

    /**
    *   @return the Root Directory of this Student
    */
    public File getRoot()
    {
        return rootDirectory;
    }

    /**
    *   @param f the Root Directory to be set
    */
    public void setRoot(File f)
    {
        this.rootDirectory = f;
    }
    /**
     * @return the fileList
     */

    public Vector<File> getFileList() 
    {
        return fileList;
    }

    /**
    *  @param file File to be added to the list of Files
    *       - Ignores file if already contained in the list
    */
    public void appendFile(File file)
    {      
        // If file is already in fileList, do nothing
        if (this.fileList.contains(file)){}

        else{ this.fileList.add(file); }
    }
    
    /**
    *   @return hashCode value for this Student
    */
    @Override
    public int hashCode() {

       int result = 17;
       result = 31 * result + ((name!=null) ? name.hashCode() : 0);
       return result;
    }

    /**
    *   Compares two Students for equality. Students are considered
    *   equal if they have the same name.
    *
    *   @param obj - object to be compared
    *   @return true if the object is equal to this object
    *
    */
    @Override
    public boolean equals(Object obj)   {

        // If Object compared to itself - return True
        if (obj == this) {
            return true;
        }
        
        // If Object is not of type Student - return False
        if (!(obj instanceof Student)) {
            return false;
        }
        
        Student student = (Student) obj;
        return (Objects.equals(name, student.name));

    }
    /**
    *   Prints the Student Directory
    */
    @Override
    public String toString()
    {
        return ( this.name + " " + "Files: " + this.fileList.size() + " LOC: " + this.lines_of_code );
    }

}
