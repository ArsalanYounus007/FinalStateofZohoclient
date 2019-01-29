# $Id$
cd $(dirname $0)

cd ..

echo $PWD
 
CLASSPATH=samples/classes:lib/commons-httpclient-3.1.jar:lib/commons-logging-api.jar:lib/commons-codec-1.7.jar:lib/ZohoReportAPIClient.jar

echo $CLASSPATH

javac -classpath  $CLASSPATH -d samples/classes samples/source/*.java 

cd samples
