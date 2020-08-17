package edu.odu.cs.codeAnnotation;

import java.io.*;

%%

%public
%class ListingScanner

%unicode

%line
%column

%{


private class Yytoken {
   int placeholder;
}

private int saved_state;
private BufferedReader input;
public BufferedWriter output;
public String graphicsPath;




private void emit (String s)
{
  try {
    output.write (s);
  } catch (IOException ex) {}
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
  if (w > 1)
    {
      for (int i = 0; i < w; ++i)
	emit("&nbsp;");
    }
  else
    emit (" ");
}


   
%}

/* regular definitions */

delim =		[ \t\r]
eol =		[\r]?[\n]
return =		[\r]
whitespace =	{delim}
digit =		[0-9]
alpha =		[a-zA-Z]
alphanum =	[a-zA-Z0-9]


%state COMMENT, LINECOMMENT, STRING, PREPROC, CALLOUT

%%

<YYINITIAL> {
"&"                     {emit ("&amp;");}
"\^"                    {emit ("&#94;");}
"<"                     {emit ("&lt;");}
">"                     {emit ("&gt;");}
"//..."                 {emit ("&#x22ee;");}
"/\*...\*/"             {emit ("&#x22ee;");}
"<:\\ensuremath{\\vdots}:>"             {emit ("&#x22ee;");}
{alphanum}+               {emit (yytext());}
}

{whitespace}+                    {wsp(yytext());}

<YYINITIAL> {
{eol}                   {emit ("<br/>\n");}

"/"\*+co                 {//emit ("<img src='" + graphicsPath + "co");
                         saved_state = YYINITIAL; yybegin(CALLOUT);}
"<:\\conum{"                 {//emit ("<img src='" + graphicsPath + "co");
                         saved_state = YYINITIAL; yybegin(CALLOUT);}
                                  
}

"/*+*/"                          {emit("<span class='highlighted1'>");}
"/*-*/"                          {emit("</span>");}
"/*+1*/"                          {emit("<span class='highlighted1'>");}
"/*-1*/"                          {emit("</span>");}
"/*+2*/"                          {emit("<span class='highlighted2'>");}
"/*-2*/"                          {emit("</span>");}
"/*+3*/"                          {emit("<span class='highlighted3'>");}
"/*-3*/"                          {emit("</span>");}

"<+>"                          {emit("<span class='highlighted1'>");}
"<->"                          {emit("</span>");}
"<+1>"                          {emit("<span class='highlighted1'>");}
"<-1>"                          {emit("</span>");}
"<+2>"                          {emit("<span class='highlighted2'>");}
"<-2>"                          {emit("</span>");}
"<+3>"                          {emit("<span class='highlighted3'>");}
"<-3>"                          {emit("</span>");}




<CALLOUT> {
{digit}               {int k = 10101; k += new Integer(yytext()); emit("&#" + k + ";");}
"*/"                  {yybegin(saved_state);}
"}:>"                  {yybegin(saved_state);}
}



.                                 {emit (yytext());}
 


