
/**
 *@(#)Export.java
 *Copyright (c) 2008  AdventNet, Inc. All Rights Reserved.
 *$Id$
 */

package Zoho.ZohoReportClient.samples.source;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Properties;

import java.util.Map;

import java.util.Iterator;

import com.adventnet.zoho.client.report.*;
import java.util.Date;
import java.io.File;

/**
 * A sample code for exporting data,reports.
 *
 */

public class ExportData
{

    private ReportClient rc = Config.getReportClient();

    public void exportData()
        throws Exception
    {
        String uri = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME,"Sales");
        
        File xmlFile = new File("Test.xml");
        rc.exportData(uri,"XML",xmlFile,null,null);
        System.out.println("Data successfully exported in xml format to  " + xmlFile.getAbsolutePath());
        
        //Exports data with criteria.
        File csvFile = new File("Test.csv");
        rc.exportData(uri,"CSV",csvFile,getCriteria(),null);
        System.out.println("Data(filtered by criteria " + getCriteria() + ")\n successfully exported in csv format to  " + csvFile.getAbsolutePath());

        String imgURI = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME,"Cost vs Sales");

        File imgFile = new File("SalesInfo.png");
        rc.exportData(imgURI,"IMAGE",imgFile,null,null);
        System.out.println("The image successfully exported as png to   " + imgFile.getAbsolutePath());
    }

    private static String getCriteria()
    {
        return "(\"Region\" = 'Central') and (\"Product Category\" = 'Stationary')";
    }

    public static void main(String[] args)
        throws Exception
    {
        ExportData exp = new ExportData();

        //call EXPORT API with the same logged in session
        exp.exportData();
    }
}
