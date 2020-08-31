package edu.odu.cs.cs350.Interface;

import java.util.ArrayList;
import java.io.File;
import edu.odu.cs.cs350.LexicalAnalysis.*;
/**
*	<pre>
*	<b> Submission Class </b>
*	This is a sub-interface that captures the role and attributes of code submissions by students.
*	</pre>
*/

public class Submission implements Comparable<Submission> , Cloneable
{
	// Base directory submitted by the student
	private File submission_dir;

	// List of source files within the submitted directory
	private ArrayList<SourceFile> srcFiles;
	
	// Combination of all token streams for all source files in this submission directory
	private ArrayList<TokenStream> allTokenStreams;
	
	// Boolean value to keep track of tokenization of directory
	private boolean tokenized;
	
	/*
	 * Submission Constructor
	 * @param directory <b> File </b> Submitted directory
	 */
	public Submission(File directory) 
	{
		submission_dir = directory;
		srcFiles = new ArrayList<SourceFile> ();
		allTokenStreams = new ArrayList<TokenStream> ();
		tokenized = false;
		findAllSrcFiles(this.submission_dir);
	}

	/*
	 * Copy Constructor
	 */
	@SuppressWarnings("unchecked")
	public Submission (Submission object)
	{
		this.submission_dir = new File(object.submission_dir.getPath() );
		this.srcFiles = (ArrayList<SourceFile>) object.srcFiles.clone();
		this.allTokenStreams = (ArrayList<TokenStream>) object.allTokenStreams.clone();
		this.tokenized = object.tokenized;
	}
	
	/*
	 * Finds all source code files with valid extensions in submission directory and
	 * adds them to the srcFiles ArrayList.
	 * @param dir <b> File </b> submitted directory
	 */
	private void findAllSrcFiles( File dir )
	{
		for (int i=0; i < dir.listFiles().length; i++)
		{
			if (dir.listFiles()[i].isDirectory())
			{
				findAllSrcFiles(dir.listFiles()[i]);
			}
			else
			{
				String filename = dir.listFiles()[i].getName();
				for (FileExtensions exts : FileExtensions.values()) 
				{
					if (filename.contains(exts.getFileExtensions()))
					{
						SourceFile source = new SourceFile(dir.listFiles()[i]);
						source.setExtension(exts.getFileExtensions());
						srcFiles.add(source);
						return;
					}
				}
			}
		}
	}
	
	/*
	 * Returns all files as an ArrayList
	 * @return <b> ArrayList<SourceFile> </b> All source files saved to this submission
	 */
	public ArrayList<SourceFile> getsrcFiles()
	{
		return srcFiles;
	}
	
	/*
	 * Return total amount of source files in submission directory
	 * @return <b> Integer </b> Total amount of source files
	 */
	public int getTotalSrcFiles() 
	{
		return srcFiles.size();
	}
	
	/*
	 * Return total amount of lines of code for all source files in submission directory
	 * @return <b> Integer </b> Total lines of code
	 */
	public int getTotalLOC() 
	{
		int count = 0;
		for (SourceFile srcs : srcFiles )
		{
			count += srcs.getLineCount();
		}
		return count;
	}
	
	public int getTokenCount()
	{
		int total = 0;
		
		for( TokenStream seq : this.allTokenStreams ) {
			total += seq.getTokenCount();
		}
		
		return total;
	}
	/*
	 * 
	 */
	public boolean tokenizeSubmission()
	{
		if (tokenized)
		{
			return true;
		}
		for(SourceFile srcs : srcFiles)
		{
			if(!(srcs.isTokenized()))
			{
				srcs.tokenize();
				allTokenStreams.add(srcs.getTokenStream());
			}
		}
		
		tokenized = true;
		return tokenized;
	}
	
	/*
	 * Method to check if this submission directory has been tokenized
	 * @return <b> Boolean </b> Returns false if any source files have not been tokenized,
	 * else return True
	 */
	public boolean isTokenized()
	{
		for(SourceFile srcs : srcFiles)
		{
			if(!(srcs.isTokenized()))
			{
				return false;
			}
		}
		return true;
	}
	
	/*
	 * 
	 */
	public StringBuilder getTokenStream()
	{
		StringBuilder fulltokenstream = new StringBuilder();
		
		for (TokenStream stream : allTokenStreams)
		{
			fulltokenstream.append(stream.getTokenStream());
		}
		return fulltokenstream;
	}
	
	/*
	 * Override compareTo to comparing submission directory names
	 * @param object <b> Submission </b>
	 * @return <b> int </b> Value based on comparison
	 */
	@Override
	public int compareTo(Submission object) 
	{
		return this.toString().compareTo(object.toString());
	}
	
	/*
	 * Override toString method
	 * @return <b> String </b> Submission directory name
	 */
	@Override
	public String toString()
	{
		return submission_dir.getName();
	}
	
	/*
	 * Override equals method
	 * Submissions equal if they have the submission directories have the same file name
	 * @param obj <b> Object </b>
	 * @return <b> boolean </b> Returns True if two objects are equal, else returns False
	 */
	@Override
	public boolean equals( Object object )
	{
		if (object == null)
		{
			return false;
		}
		
		if ( !(object instanceof Submission) )
		{
			return false;
		}
		
		if (this == object )
		{
			return true;
		}
		
		Submission comparedObject = (Submission) object;
		
		return this.submission_dir.getAbsolutePath().equals( comparedObject.submission_dir.getAbsolutePath() )
				&& this.tokenized == comparedObject.tokenized
				&& this.allTokenStreams.equals( comparedObject.allTokenStreams)
				&& this.srcFiles.equals(comparedObject.srcFiles);
	}
	
	/*
	 * Override clone() method
	 * @return <b> Object </b> Deep copy of cloned object
	 */
	@Override
	public Object clone()
	{
		return new Submission(this);
	}
	
	
}
