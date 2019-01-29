rem $Id$
echo off
 
set TOOL_HOME=%~dp0%\..
cd %TOOL_HOME%

set CLASSPATH=samples\classes;lib\commons-httpclient-3.1.jar;lib\commons-logging-api.jar;lib\commons-codec-1.4.jar;lib\ZohoReportAPIClient.jar

if (%1) == (0) set CLASSNAME=AddRow
if (%1) == (1) set CLASSNAME=UpdateData
if (%1) == (2) set CLASSNAME=DeleteData
if (%1) == (3) set CLASSNAME=ImportData
if (%1) == (4) set CLASSNAME=ExportData
if (%1) == (5) set CLASSNAME=ExportDataUsingSQL

if (%1) == () goto usage_error


"%JAVA_HOME%\bin\java" -classpath  %CLASSPATH% com.adventnet.zoho.client.report.samples.%CLASSNAME% %*
cd samples

goto end_of_page

:usage_error
echo "Specify option number"
echo "0 - AddRow, 1 - UpdateData,2 - DeleteData , 3 - ImportData, 4 - ExportData, 5 - ExportDataUsingSQL "
cd samples

:end_of_page
