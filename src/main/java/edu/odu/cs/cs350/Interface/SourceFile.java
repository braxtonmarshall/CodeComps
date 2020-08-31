package edu.odu.cs.cs350.Interface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds.LanguageTypes;
import edu.odu.cs.cs350.LexicalAnalysis.TokenStream;

/*
 * 
 */
@SuppressWarnings("serial")
public class SourceFile extends File
{
	// File name of the source file
	private File source;
	
	// File Extension
	private String ext;
	
	// Flag to indicate if source file has been tokenized
	private boolean tokenized;
	
	// Stream of tokens for this Source File
	private TokenStream tokenstream;
	
	/*
	 * Source File Constructor
	 * @param source <b> File </b>
	 */
	public SourceFile(File source) {
		super(source.getAbsolutePath());
		this.source = source;
		this.tokenized = false;
		this.ext = "";
		this.tokenstream = null;
	}
	
	/*
	 * Sets the file extension for this source file
	 * @param fileExtension <b> String </b>
	 */
	public void setExtension(String fileExtension) 
	{
		this.ext = fileExtension;
	}
	
	/*
	 * 
	 */
	public TokenStream getTokenStream()
	{
		return tokenstream;
	}
	/*
	 * Returns true if this source file has been parsed for its token sequence
	 * @param <b> boolean </b> T
	 */
	public boolean isTokenized()
	{
		return tokenized;
	}
	/*
	 * Return total lines of code for this source file
	 * @return <b> Integer </b> Total lines of code
	 */
	public int getLineCount()
	{
		int lines = 0;
		
		try (LineNumberReader lnr = new LineNumberReader(new FileReader(source)))
		{
			while (lnr.readLine() != null);
			lines = lnr.getLineNumber();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return lines;
	}
	
	/*
	 * 
	 */
	public void tokenize()
	{
		if (tokenized)
		{
			return;
		}
		
		TokenStream tokstream = null;
		
		BufferedReader buffread = null;
		
		try
		{
			buffread = new BufferedReader(new FileReader (source));
		}
		catch(FileNotFoundException error)
		{
			error.printStackTrace();
		}
		
		StringBuilder stringBuild = new StringBuilder();
		String linesep = System.getProperty("line.seperator");
		String line = null;
		
		// Java
		if (this.ext.equals((".java")))
		{
			try
			{
				while( (line = buffread.readLine()) != null)
				{
					stringBuild.append( line );
					stringBuild.append( linesep );
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			Reader readInput = new StringReader (stringBuild.toString());
			tokstream = new TokenStream (readInput, LanguageTypes.JAVA);
		}
		
		// C++
		else
		{
			try
			{
				while( (line = buffread.readLine()) != null)
				{
					stringBuild.append(line);
					stringBuild.append(linesep);
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			Reader readInput = new StringReader(stringBuild.toString());
			tokstream = new TokenStream(readInput, LanguageTypes.CPLUSPLUS);
		}
		tokenstream = tokstream;
		tokenized = true;
		return;
	}
}