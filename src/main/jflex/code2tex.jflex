package edu.odu.cs.codeAnnotation;

import java.io.*;

%%

%public
%class CppJavaTeXScanner

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


void keyword (String word)
{
  emit ("\\lstkwd{" + word + "}");
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
^{whitespace}*"#"       {wsp(yytext().substring(0,yytext().length()-1));
                                  emit ("\\begin{lstdirective}#"); 
                                  yybegin(PREPROC);}
"\\"                     {emit ("\\\\");}
"{"                      {emit ("\\{");}
"}"                      {emit ("\\}");}
"//..."                  {emit ("\\smvdots{}");}
"/\*...\*/"              {emit ("\\smvdots{}");}
\/\*+co{digit}+\*+\/     {callout (yytext());}

"\""                     {emit ("\\begin{lststring}\""); yybegin(STRING);}
"/\*"                   {emit ("\\begin{lstcomment}/*"); yybegin(COMMENT);}
"//"                     {emit ("\\begin{lstcomment}//"); yybegin(LINECOMMENT);}
{eol}                    {eol ();}
}

<PREPROC> {
"\\"                     {emit ("\\\\");}
"{"                      {emit ("\\{");}
"}"                      {emit ("\\}");}
\/\*+co{digit}+\*+\/     {callout (yytext());}
{eol}                    {emit ("\\end{lstdirective}"); eol();
                          yybegin(YYINITIAL);}
}

<COMMENT> {
"\\"                     {emit ("\\\\");}
"{"                      {emit ("\\{");}
"}"                      {emit ("\\}");}
"//..."                  {emit ("\\smvdots{}");}
"/\*...\*/"              {emit ("\\smvdots{}");}
\/\*+co{digit}+\*+\/     {callout (yytext());}
{eol}                    {eol ();}
"\*/"                    {emit ("*/\\end{lstcomment}");
                          yybegin(YYINITIAL);}
}

<LINECOMMENT> {
"\\"                     {emit ("\\\\");}
"{"                      {emit ("\\{");}
"}"                      {emit ("\\}");}
"//..."                  {emit ("\\smvdots{}");}
"/\*...\*/"              {emit ("\\smvdots{}");}
\/\*+co{digit}+\*+\/     {callout (yytext());}
{eol}                    {emit ("\\end{lstcomment}"); eol();
                          yybegin(YYINITIAL);}
}


<STRING> {
"\\"                     {emit ("\\\\");}
"{"                      {emit ("\\{");}
"}"                      {emit ("\\}");}
\/\*+co{digit}+\*+\/     {callout (yytext());}
{eol}                    {eol();}
"\""                     {emit ("\"\\end{lststring}");
                          yybegin(YYINITIAL);}
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
 


