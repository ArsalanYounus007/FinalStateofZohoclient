#$Id$
 
cd $(dirname $0)

cd ..

if [ "$1" = "0" ]
then
    CLASSNAME=AddRow
elif [ "$1" = "1" ]
then
    CLASSNAME=UpdateData
elif [ "$1" = "2" ]
then
    CLASSNAME=DeleteData
elif [ "$1" = "3" ]
then
    CLASSNAME=ImportData
elif [ "$1" = "4" ]
then
    CLASSNAME=ExportData
elif [ "$1" = "5" ]
then
    CLASSNAME=ExportDataUsingSQL
else
echo "Usage: $0 <option>"
echo "Options:(Specify the number)"
echo -e "0 - AddRow\n1 - UpdateData\n2 - DeleteData \n3 - ImportData\n4 - ExportData\n5 - ExportDataUsingSQL"
exit -1
fi


java -classpath samples/classes:lib/commons-logging-api.jar:lib/commons-codec-1.7.jar:lib/commons-httpclient-3.1.jar:lib/ZohoReportAPIClient.jar:lib/ZohoReportUploadTool.jar com.adventnet.zoho.client.report.samples.$CLASSNAME $*
