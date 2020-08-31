package edu.odu.cs.cs350.Interface;

import java.io.File;
import java.util.Objects;
import java.util.ArrayList;

/**
* <pre>
* <b> Student Class </b>
* This is a sub-interface that captures the role and attributes of the students.
* A student has unique identification and his or her assignment submissions.
* </pre>
*/
     
public class Student implements Comparable<Student> , Cloneable
{
	
	// The unique identification is the submission directory
	private String submissionid;
	
    // List of source files found in the submission directory
    private ArrayList<Submission> filelist;

    // Priority Submission
    private Submission priosubmission;

    /**
    *   Default Student Constructor
    */ 
    public Student()
    {
        submissionid = "";
        filelist = new ArrayList<Submission>();
        priosubmission = null;
    }

    /**
    *   Student Constructor
    *   @param s <b> String </b> Submission Directory Name
    */
    public Student(String s)
    {
        this.submissionid = s;
        filelist = new ArrayList<Submission>();
        priosubmission = null;
    }

    /**
    *   Copy Constructor
    *   @param object <b> Student </b> Student Object to copy
    */
    @SuppressWarnings("unchecked")
	public Student ( Student object )
    {
        this.submissionid = new String(object.submissionid);
        this.filelist = ((ArrayList<Submission>) object.filelist.clone());
    }

    /**
     * @return the name of the Student
     */
    public String getName() {
        return submissionid;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.submissionid = name;
    }

    /**
    *   Add submissions to ArrayList of submissions for student
    *   @param directory <b> File </b> The submitted directory
    */
    public void addSubmission ( File directory )
    {
        filelist.add(new Submission(directory));
        updatePrioritySubmission();
    }

    /**
    *   <pre>
    *   Search Student's ArrayList of submissions for specific submission
    *   </pre>
    *   @param name <b> String </b> Submission to search for
    *   @return <b> Submission </b> Submission object found
    */
    public Submission getSubmission( String name )
    {
        for(int i=0; i<filelist.size();i++)
        {
            if (filelist.get(i).toString().equals(name))
            {
                return filelist.get(i);
            }
        }

        // If not found, return null
        return null;
    }

    /**
    *   Returns priority submission for this student
    *   @return <b> Submission </b> Priority Submission
    */
    public Submission getPrioritySubmission()
    {
        return priosubmission;
    }

    /*
    *   Parses student object's arraylist for priority submission, and then sets the prioritysubmission attribute
    */
    public void updatePrioritySubmission()
    {
        // Sort submissions based on index numbers at the end of string
        filelist.sort(null);

        // Submissions with no version number always get priority
        for (int i=0; i < filelist.size(); i++)
        {
            if (filelist.get(i).toString().lastIndexOf('.') == -1 )
            {
                priosubmission = filelist.get(i);
            }
        }

        priosubmission = filelist.get(filelist.size() - 1);
    }

    /**
    *   Returns total number of submissions in student's directory
    *   @return <b> int </b> Total number of submissions.
    */
    public int getTotalSubmissions()
    {
        return filelist.size();
    }

    /**
    *   Returns the total amount of files in the priority submission for the student
    *   @return <b> int </b> Total number of files in priority submission directory.
    */
    public int getTotalFileCount()
    {
        return getPrioritySubmission().getTotalSrcFiles();
    }

    /**
    *   Returns the total amount of lines of code within the files contained in the priority
    *   submission directory.
    *   @return <b> int </b> Total LOC of files in priority submission directory.
    */
    public int getTotalLineCount()
    {
        return getPrioritySubmission().getTotalLOC();
    }
    
    /*
     * 
     */
    public StringBuilder getTokenStream()
    {
    	return getPrioritySubmission().getTokenStream();
    }
    
    /*
     * 
     */
    public int getTokenStreamLength()
    {
    	return getTokenStream().length();
    }
    /**
    *   @return hashCode value for this Student
    */
    @Override
    public int hashCode() {

       int result = 17;
       result = 31 * result + ((submissionid!=null) ? submissionid.hashCode() : 0);
       return result;
    }

    /**
    *   Override equals() method. Compares two Students for equality.
    *
    *   @param obj - object to be compared
    *   @return <b> boolean </b> Return true if the objects are equal
    */
    @Override
    public boolean equals(Object obj)   
    {

        if (obj == null) 
        {
            return false;
        }

        if (!(obj instanceof Student)) 
        {
            return false;
        }

        if (this == obj) 
        {
            return true;
        }
        
        
        Student student = (Student) obj;
        return (Objects.equals(submissionid, student.submissionid));

    }

    /**
     *  Override comparison for default sorting mechanism when applied to a collection
     *  of student objects.
     *  @param stu <b> Student </b> Student object being compared
     *  @return <b> int </b> value based on comparison result
     */
    @Override
    public int compareTo(Student stu)
    {
        return this.submissionid.compareTo(stu.submissionid);
    }

    /**
     *   Prints the Student Name
     */
    @Override
    public String toString()
    {
        return submissionid;
    }

    /**
    *   Override clone() method
    * @return <b> Object </b> A deep copy of student object
    */
    @Override
    public Object clone()
    {
        return new Student(this);
    }

}
