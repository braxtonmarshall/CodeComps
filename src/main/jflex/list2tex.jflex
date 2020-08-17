package edu.odu.cs.codeAnnotation;

import java.io.*;

%%

%public
%class ListingTeXScanner

%unicode


%{


private class Yytoken {
   int placeholder;
}

private int saved_state;
private int highlighting = 0;
private BufferedReader input;
public BufferedWriter output;
public String graphicsPath;



StringBuffer outbuf = new StringBuffer();

private void emit (String s)
{
  outbuf.append(s);
}



void callout (String lexeme)
{
  int start = 0;
  while (!Character.isDigit(lexeme.charAt(start))) ++start;
  int stop = start;
  while (Character.isDigit(lexeme.charAt(stop))) ++stop;
  emit ("\\conum{" + lexeme.substring(start,stop) + "}");
}


public int tabSize = 4;

int wslen (String whitespace)
{
  int width = 0;
  int len = whitespace.length();
  for (int i = 0; i < len; ++i)
    {
      char c = whitespace.charAt(i);
      if (c == '\t')
	width = width / tabSize + tabSize;
      else
	++width;
    }
  return width;
}


void wsp (String whitespace)
{
  int w = wslen(whitespace);
  for (int i = 0; i < w; ++i)
	emit(" ");
}


void eol()
{
  String prefix = "\\lstln{}";
  if (highlighting > 0) {
     emit ("}");
  }
  try {
     output.write(prefix);
     output.write(outbuf.toString());
     output.write("\n");
  } catch (IOException e) {} 

  outbuf = new StringBuffer();
  if (highlighting > 0) {
     String roman = "i";
     for (int i = 1; i < highlighting; ++i)
        roman = roman + "i";
     emit ("\\hl" + roman + "{");
  }
}

void flush()
{
  String prefix = "\\lstln{}";
  if (highlighting > 0) {
     emit ("}");
  }
  if (outbuf.length() > 0) {
     try {
        output.write(prefix);
        output.write(outbuf.toString());
        output.write("\n");
     } catch (IOException e) {} 
  }
  outbuf = new StringBuffer();
}
   
%}

/* regular definitions */

delim =		[ \t\r]
eol =		[\r]?[\n]
digit = [0-9]
whitespace =	{delim}
alphanum =	[a-zA-Z0-9]


%state COMMENT, LINECOMMENT, STRING, PREPROC

%%

<YYINITIAL> {
"\\"                     {emit ("\\\\");}
"{"                      {emit ("\\{");}
"}"                      {emit ("\\}");}
"//..."                  {emit ("\\smvdots{}");}
"/\*...\*/"              {emit ("\\smvdots{}");}
\/\*+co{digit}+\*+\/     {callout (yytext());}

{eol}                    {eol ();}
}



<YYINITIAL> {
{alphanum}+               {emit (yytext());}
}

{whitespace}+            {wsp(yytext());}


"/*+*/"                          {highlighting=1; emit("\\hli{");}
"/*-*/"                          {highlighting=0; emit("}");}
"/*+1*/"                          {highlighting=1; emit("\\hli{");}
"/*-1*/"                          {highlighting=0; emit("}");}
"/*+2*/"                          {highlighting=2; emit("\\hlii{");}
"/*-2*/"                          {highlighting=0; emit("}");}
"/*+3*/"                          {highlighting=3; emit("\\hliii{");}
"/*-3*/"                          {highlighting=0; emit("}");}


.                                 {emit (yytext());}
 


