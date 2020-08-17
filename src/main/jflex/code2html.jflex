package edu.odu.cs.codeAnnotation;

import java.io.*;

%%

%public
%class CppJavaScanner

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


void keyword (String word)
{
  emit ("<span class='keyword'>" + word + "</span>");
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
^{whitespace}*"#"       {wsp(yytext());
                                  emit ("<span class='directive'>#"); 
                                  yybegin(PREPROC);}
"&"                     {emit ("&amp;");}
"\^"                    {emit ("&#94;");}
"<"                     {emit ("&lt;");}
">"                     {emit ("&gt;");}
"//..."                 {emit ("&#x22ee;");}
"/\*...\*/"             {emit ("&#x22ee;");}
"<:\\ensuremath{\\vdots}:>"             {emit ("&#x22ee;");}
}

<PREPROC> {
"&"                     {emit ("&amp;");}
"\^"                    {emit ("&#94;");}
"<"                     {emit ("&lt;");}
">"                     {emit ("&gt;");}
}

<COMMENT> {
"&"                     {emit ("&amp;");}
"\^"                    {emit ("&#94;");}
"<"                     {emit ("&lt;");}
">"                     {emit ("&gt;");}
"/\*...\*/"             {emit ("&#x22ee;");}
}

<LINECOMMENT> {
"&"                     {emit ("&amp;");}
"\^"                    {emit ("&#94;");}
"<"                     {emit ("&lt;");}
">"                     {emit ("&gt;");}
"/\*...\*/"             {emit ("&#x22ee;");}
}

<STRING> {
"&"                     {emit ("&amp;");}
"\^"                    {emit ("&#94;");}
"<"                     {emit ("&lt;");}
">"                     {emit ("&gt;");}
}


<YYINITIAL> {
"asm"                   {keyword(yytext());}
"auto"                  {keyword(yytext());}
"break"                  {keyword(yytext());}
"case"                  {keyword(yytext());}
"catch"                  {keyword(yytext());}
"char"                  {keyword(yytext());}
"class"                  {keyword(yytext());}
"const"                  {keyword(yytext());}
"continue"                  {keyword(yytext());}
"default"                  {keyword(yytext());}
"delete"                  {keyword(yytext());}
"do"                  {keyword(yytext());}
"double"                  {keyword(yytext());}
"else"                  {keyword(yytext());}
"enum"                  {keyword(yytext());}
"extends"                  {keyword(yytext());}
"extern"                  {keyword(yytext());}
"float"                  {keyword(yytext());}
"for"                  {keyword(yytext());}
"friend"                  {keyword(yytext());}
"if"                  {keyword(yytext());}
"implements"                  {keyword(yytext());}
"inline"                  {keyword(yytext());}
"int"                  {keyword(yytext());}
"long"                  {keyword(yytext());}
"new"                  {keyword(yytext());}
"operator"                  {keyword(yytext());}
"private"                  {keyword(yytext());}
"public"                  {keyword(yytext());}
"register"                  {keyword(yytext());}
"return"                  {keyword(yytext());}
"short"                  {keyword(yytext());}
"signed"                  {keyword(yytext());}
"sizeof"                  {keyword(yytext());}
"static"                  {keyword(yytext());}
"struct"                  {keyword(yytext());}
"switch"                  {keyword(yytext());}
"template"                  {keyword(yytext());}
"this"                  {keyword(yytext());}
"throw"                  {keyword(yytext());}
"throws"                  {keyword(yytext());}
"try"                  {keyword(yytext());}
"typedef"                  {keyword(yytext());}
"typename"                  {keyword(yytext());}
"union"                  {keyword(yytext());}
"unsigned"                  {keyword(yytext());}
"virtual"                  {keyword(yytext());}
"void"                  {keyword(yytext());}
"volatile"                  {keyword(yytext());}
"while"                  {keyword(yytext());}
{alphanum}+               {emit (yytext());}
}

{whitespace}+                    {wsp(yytext());}

<YYINITIAL> {
{eol}                   {emit ("<br/>\n");}

"\""                    {emit ("<span class='quote'>&quot;"); 
                                  yybegin(STRING);}
"/\*"                   {emit ("<span class='comment'>/*"); 
                                  yybegin(COMMENT);}
"//"                    {emit ("<span class='comment'>//"); 
                                  yybegin(LINECOMMENT);}
"/**co"                 {//emit ("<img src='" + graphicsPath + "co");
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



<COMMENT> {
{eol}                   {emit ("<br/>\n");}
"\""                    {emit ("&quot;");}
"\*/"                   {emit ("*/</span>");
                                  yybegin(YYINITIAL);}
}

<LINECOMMENT> {
"\""                    {emit ("&quot;");}
{eol}                   {emit ("</span><br/>\n");
                                      yybegin(YYINITIAL);}
}

<PREPROC> {
"\""                    {emit ("&quot;");}
{eol}                   {emit ("</span><br>\n");
                                      yybegin(YYINITIAL);}
}

<STRING> {
{eol}                   {emit ("<br>\n");}
"\""                   {emit ("&quot;</span>");
                                  yybegin(YYINITIAL);}
}

<CALLOUT> {
{digit}               {int k = 10101; k += new Integer(yytext()); emit("&#" + k + ";");}
"*/"                  {yybegin(saved_state);}
"}:>"                  {yybegin(saved_state);}
}



.                                 {emit (yytext());}
 


