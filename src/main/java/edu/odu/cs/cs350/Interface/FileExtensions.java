package edu.odu.cs.cs350.Interface;

public enum FileExtensions 
{
	CPP (".cpp"),
	HPP (".hpp"),
	JAVA (".java"),
	CPPH (".h");
	
	private final String extension;
	
	FileExtensions(String ext) 
	{
		this.extension = ext;
	}
	
	public String getFileExtensions()
	{
		return this.extension;
	}
}