rem $Id$
echo off
 
set TOOL_HOME=%~dp0%\..
cd %TOOL_HOME%

set CLASSPATH=samples\classes;lib\commons-httpclient-3.1.jar;lib\commons-logging-api.jar;lib\commons-codec-1.3.jar;lib\ZohoReportAPIClient.jar

"%JAVA_HOME%\bin\javac" -classpath  %CLASSPATH% -d samples/classes samples/source/*.java 

cd samples
