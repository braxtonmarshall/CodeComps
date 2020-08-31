package edu.odu.cs.cs350.Interface;

/**
 * 
 * 
 * 
 * 
 */
public class StudentPair implements Comparable<StudentPair> , Cloneable
{
	
	private Student student1;
	private Student student2;
	
	private double rawScore;
	private double zScore;
	
	private boolean rawcalculated;
	private boolean zcalculated;
	
	/**
	 * Default Constructor
	 * @param student1 <b> Student </b>
	 * @param student2 <b> Student </b>
	 */
	public StudentPair(Student stu1, Student stu2)
	{
		if (stu1.compareTo(stu2) < 0)
		{
			this.student1 = stu1;
			this.student2 = stu2;
		}
		else if (stu1.compareTo(stu2) > 0)
		{
			this.student1 = stu2;
			this.student2 = stu1;
		}
		
		rawScore = 0.0;
		zScore = 0.0;
		rawcalculated = false;
		zcalculated = false;
	}
	
	/*
	 * Copy Constructor
	 * @param object <b> StudentPair </b> Student Pair to copy
	 */
	public StudentPair(StudentPair object)
	{
		this.student1 = (Student) object.student1.clone();
		this.student2 = (Student) object.student2.clone();
		this.rawScore = object.rawScore;
		this.zScore = object.zScore;
		this.rawcalculated = object.rawcalculated;
		this.zcalculated = object.zcalculated;
	}
	
	/*
	 * Method to retrieve raw score for student pair
	 * @return <b> double </b> Raw Score
	 */
	public double getRawScore()
	{
		return rawScore;
	}
	
	/*
	 * Method to retrieve z score for student pair
	 * @return <b> double </b> Z Score
	 */
	public double getZScore()
	{
		return zScore;
	}
	
	/*
	 * Method to retrieve identifier of first student in student pair
	 * @return <b> String </b> Name of first student
	 */
	public String getFirstStudent()
	{
		return student1.toString();
	}
	
	/*
	 * Method to retrieve identifier of second student in student pair
	 * @return <b> String </b> Name of second student
	 */
	public String getSecondStudent()
	{
		return student2.toString();
	}
	
	/*
	 * Method to check if the raw score has been calculated for student pair
	 * @return <b> Boolean </b>
	 */
	public boolean isRawScoreCalc()
	{
		return rawcalculated;
	}
	
	/*
	 * Method to check if the z score has been calculated for student pair
	 * @return <b> Boolean </b> 
	 */
	public boolean isZScoreCalc()
	{
		return zcalculated;
	}
	
	/*
	 * Method to calculate the raw score for student pair
	 * @param d <b> Double </b>
	 */
	public void calcRawScore(double d)
	{
		if (rawcalculated)
		{
			return;
		}
		
		// (4 * d) / (L1 + L2)^2
		double full_length = student1.getTokenStreamLength() + student2.getTokenStreamLength();
		double num = 4 * d;
		double denom = Math.pow(full_length, 2.0);
		
		rawScore = num / denom;
		rawcalculated = true;
	}
	
	/*
	 * 
	 */
	public void calcZScore(double avg, double stdev)
	{
		if(zcalculated)
		{
			return;
		}
		
		if (!rawcalculated)
		{
			return;
		}
		
		zScore = (rawScore - avg) / stdev;
		zcalculated = true;
	}
	
	/*
	 * 
	 */
	@Override
	public String toString()
	{
		return "( " + student1.toString() + " , " + student2.toString() + " )";
	}
	
	/*
	 * 
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null)
		{
			return false;
		}
		
		if ( !(object instanceof StudentPair) )
		{
			return false;
		}
		
		if (this == object )
		{
			return true;
		}
		
		StudentPair comparedObject = (StudentPair) object;
		
		return (this.student1.equals(comparedObject.student1) && this.student2.equals(comparedObject.student2) );
	}
	/*
	 * 
	 */
	@Override
	public int compareTo(StudentPair object) 
	{
		if (this.student1.compareTo(object.student2) == 0 )
		{
			return this.student2.compareTo(object.student2);
		}
		
		return this.student1.compareTo(object.student1);
	}
	
	/*
	 * 
	 */
	@Override
	public Object clone()
	{
		return new StudentPair(this);
	}
}