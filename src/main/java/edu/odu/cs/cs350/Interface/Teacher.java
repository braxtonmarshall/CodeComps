package edu.odu.cs.cs350.Interface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.odu.cs.cs350.Output.Workbook;

//import edu.odu.cs.cs350.codeCompCommon.SharedPhrases;

public class Teacher implements Cloneable
{
	
	private ArrayList<Student> students;
	private ArrayList<StudentPair> stupairs;
	
	private boolean isTemplated;
	private boolean isSheetNamed;
	
	private File outputDir;
	private FileOutputStream ostream;
	
	private String template;
	private String sheetName;
	
	private boolean recievedSubmissions;
	private boolean parsedStudentSubmissions;
	private boolean analyzedTokenStreams;
	
	/**
	 * 
	 */
	public Teacher(String outputSpreadsheet)
	{
		template = "";
		sheetName = "";
		
		students = new ArrayList<Student>();
		stupairs = new ArrayList<StudentPair>();
		
		isTemplated = false;
		isSheetNamed = false;
		
		recievedSubmissions = false;
		parsedStudentSubmissions = false;
		analyzedTokenStreams = false;
		
		outputDir = new File(outputSpreadsheet);
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Teacher (Teacher object)
	{
		this.template = new String(object.template);
		this.sheetName = new String(object.sheetName);
		this.students = (ArrayList<Student>) object.students.clone();
		this.stupairs = (ArrayList<StudentPair>) object.stupairs.clone();
		this.isTemplated = object.isTemplated;
		this.isSheetNamed = object.isSheetNamed;
		this.recievedSubmissions = object.recievedSubmissions;
		this.parsedStudentSubmissions = object.parsedStudentSubmissions;
		this.analyzedTokenStreams = object.analyzedTokenStreams;
		this.outputDir = new File(object.outputDir.getPath());
	}
	
	/**
	 * 
	 */
	public boolean isTemplated()
	{
		return isTemplated;
	}
	
	/**
	 * 
	 */
	public boolean isSheetNamed()
	{
		return isSheetNamed;
	}
	
	/**
	 * 
	 */
	public String getTemplate()
	{
		return template;
	}
	
	/**
	 * 
	 */
	public void setTemplate(String s)
	{
		template = s;
	}
	
	/**
	 * 
	 */
	public String getSheetName()
	{
		return sheetName;
	}
	
	/**
	 * 
	 */
	public void setSheetName(String s)
	{
		sheetName = s;
	}
	
	/**
	 * 
	 */
	public boolean isSubmissionRecieved()
	{
		return recievedSubmissions;
	}
	
	/**
	 * 
	 */
	public boolean isSubmissionParsed()
	{
		return parsedStudentSubmissions;
	}
	
	/**
	 * 
	 */
	public boolean isTokenStreamAnalyzed()
	{
		return analyzedTokenStreams;
	}
	
	/**
	 * 
	 */
	public Student getStudent(String name)
	{
		for (int i = 0; i < students.size(); i++)
		{
			if (students.get(i).toString().equals(name))
			{
				return students.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 */
	public StudentPair getStudentPair (String student1, String student2)
	{
		for( StudentPair pair : stupairs ) {
			if( student1.equals(pair.getFirstStudent()) &&
				student2.equals(pair.getSecondStudent()) ) {
				
				return pair;
			}
			
			if( student1.equals(pair.getSecondStudent()) &&
				student2.equals(pair.getFirstStudent()) ) {
				
				return pair;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 */
	public int getTotalStudentCount()
	{
		return students.size();
	}
	
	/**
	 * 
	 */
	public int getTotalStudentPairCount()
	{
		return stupairs.size();
	}
	
	/**
	 * 
	 */
	public boolean acceptStudentSubmission(File subdir)
	{
				if( recievedSubmissions ) {
					return true;
				}
				
				
				if( !subdir.isDirectory() ) {
					return false;
				}
				
				
				File[] directoryFiles = subdir.listFiles();
						
				
				for( int i = 0 ; i < directoryFiles.length ; i++ ) {
					
				
					String folderName = directoryFiles[i].getName();
					
					String id = "";
					
					
					if( folderName.contains(".") ) {
						id = folderName.substring( 0, folderName.indexOf('.') );
					}
					
					else {
						id = folderName;
					}
					
					
					if( getStudent(id) != null ) { 
						
						
						for( int j = 0 ; j < students.size(); j++ ) {
							if( students.get(j).toString().equals(id) ) {
								students.get(j).addSubmission( directoryFiles[i] );
							}
						}
					}
					else {

						Student newStudent = new Student( id );
						
						newStudent.addSubmission( directoryFiles[i] );
						
						for( int index = 0 ; index < students.size() ; index++) {
							stupairs.add( new StudentPair(newStudent , students.get(index)) );
						}

						students.add( newStudent );
					}
				}
				
				recievedSubmissions = true;
				return recievedSubmissions;
	}
	
	/**
	 * 
	 */
	public void outputFeedback()
	{
		if( !recievedSubmissions ) {
			return;
		}
		
		students.sort( null );
		System.out.println( "\nFound " + students.size() + " student submissions:\n");

		for (int i = 0; i < students.size(); i++)
		{
			System.out.print( students.get(i) + "\t\tFiles: " + students.get(i).getTotalFileCount() );
			System.out.println( "\t\t" + "LOC: " + students.get(i).getTotalLineCount() );															
		}
		
		if( students.size() == 0 ) {
			System.out.println( "\nFinished" );
		}
		else if( stupairs.size() == 0 ) {
			System.out.println( "\nRequires at least 2 student submissions for analysis" );
		}
		else {
			System.out.println( "\nAnalyzing codes ...");
			System.out.println( "This may take a while.");
			System.out.println();
		}
	}
	
	/**
	 * 
	 */
	public boolean parseSubmissionDirectory()
	{
		if( parsedStudentSubmissions == true ) 
		{
			return true;
		}
		
		if( recievedSubmissions == false ) 
		{
			return false;
		}
		for( int i = 0 ; i < students.size(); i++ ) 
		{
			
			if( !students.get(i).getPrioritySubmission().tokenizeSubmission() ) {
				return false;
			}
		}
		
		parsedStudentSubmissions = true;
		return parsedStudentSubmissions;
	}
	
	/**
	 * 
	 */
	public boolean analyze()
	{
		if( analyzedTokenStreams ) 
		{
			return true;
		}
				
		
		if( parsedStudentSubmissions == false ) 
		{
			return false;
		}
				
		if( stupairs.size() == 0 ) 
		{
			return true;
		}
				
		stupairs.sort( null );
				
				
		SharedPhrases phrases = new SharedPhrases();
				
		for( Student stud : students ) 
		{
			phrases.addSentence( stud.getTokenStream().toString() , stud.toString() );
		}
				
		int start = 1;
		int end = stupairs.size();
				
		for( StudentPair studPair : stupairs ) 
		{
			System.out.print( start + " of " + end );
					
			double T = 0;
					
					
			for( CharSequence phrase : phrases.allPhrases() ) 
			{
				if( phrases.sourcesOf(phrase.toString()).contains(studPair.getFirstStudent()) && 
							phrases.sourcesOf(phrase.toString()).contains(studPair.getSecondStudent()) ) 
				{
							
					int k = phrases.sourcesOf(phrase.toString()).size();
					double toAdd = phrase.length() / Math.pow( (k - 1), 2.0 );
					T = T + toAdd;
				}
			}
					
			studPair.calcRawScore( T );
					
			System.out.println( " COMPLETED");
			start ++;
		}
				
		double rawScoreAverage = calcRawScoreAvg();
				
		System.out.println();
		System.out.println( "\n\nRESULTS:\n" );
		int index = 1;
				
		for( StudentPair studPair : stupairs ) 
		{
			studPair.calcZScore( rawScoreAverage , calcStdDev( rawScoreAverage ) );
					
			System.out.println( index + ". Scores for " + studPair );
			System.out.println( "Raw Score: " + String.format("%.2f", studPair.getRawScore()) + 
										"\nZ-Score: " + String.format("%.2f", studPair.getZScore()) );
			System.out.println();
			index ++;
		}
				
				analyzedTokenStreams = true;
				return analyzedTokenStreams;
	}
	
	/*
	 * 
	 */
	public double calcRawScoreAvg()
	{
		double rawScoreAverage = 0;
		
		for( StudentPair studPair : stupairs ) {
			rawScoreAverage += studPair.getRawScore();
		}
		
		rawScoreAverage = rawScoreAverage / stupairs.size();
		
		return rawScoreAverage;
	}
	
	/**
	 * 
	 */
	private double calcStdDev(double avgRawScore)
	{
		double standardDeviation = 0;
		double sigma = 0;
		
		// Iterate through every student pair and calculate the standard deviation
		for( StudentPair studPair : stupairs ) {
			sigma += Math.pow( studPair.getRawScore() - avgRawScore , 2.0 );
		}
		
		standardDeviation = Math.sqrt( sigma / stupairs.size() );
		
		return standardDeviation;
	}
	
	/**
	 * 
	 */
	public void displayResults()
	{
		Workbook wb = null;
		
		// checks if there are two variables provided -raw and -template
		if( isTemplated && isSheetNamed ) {
			wb = new Workbook(  sheetName, new File(template) );
		}
		// checks for template of type file
		else if( isTemplated ) {
			wb = new Workbook( new File(template) );
		}
		// checks for sheetName variable of type String
		else if( isSheetNamed ) {
			wb = new Workbook( sheetName );
		}
		
		// default values are set for workbook 
		else {
			wb = new Workbook();
		}
		
		for( StudentPair studPair : stupairs ) {
			wb.addStudentPair( studPair );
		}
		
		wb.writeToFile( ostream );
	}
	
	/**
	 * 
	 */
	public boolean lockOutputFile()
	{
		boolean isLocked = false;
		
		try {
			ostream = new FileOutputStream(new File(outputDir.getPath(), "results.xlsx"));
			isLocked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isLocked;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return outputDir.getName();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object object)
	{
		if( object == null ) {
			return false;
		}
		
		if( !(object instanceof Teacher) ) {
			return false;
		}
		
		if( this == object ) {
			return true;
		}
		
		Teacher copyObj = (Teacher) object;
		
		return this.students.equals( copyObj.students ) &&
			   this.stupairs.equals( copyObj.stupairs ) &&
			   this.isTemplated == copyObj.isTemplated &&
			   this.sheetName == copyObj.sheetName &&
			   this.recievedSubmissions == copyObj.recievedSubmissions &&
			   this.parsedStudentSubmissions == copyObj.parsedStudentSubmissions &&
			   this.analyzedTokenStreams == copyObj.analyzedTokenStreams &&
			   this.outputDir.equals(copyObj.outputDir) &&
			   this.sheetName.equals( copyObj.sheetName ) &&
			   this.template.equals( copyObj.template );
	}
	
	/**
	 * 
	 */
	@Override
	public Object clone()
	{
		return new Teacher(this);
	}
	
}