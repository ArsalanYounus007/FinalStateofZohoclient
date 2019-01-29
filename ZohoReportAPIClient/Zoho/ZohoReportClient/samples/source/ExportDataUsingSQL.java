
/**
 *@(#)ExportDataUsingSQL.java
 *Copyright (c) 2008  AdventNet, Inc. All Rights Reserved.
 *$Id$
 */

package Zoho.ZohoReportClient.samples.source;

import java.io.*;

import com.adventnet.zoho.client.report.*;

/**
 * A sample code for exporting data using SQL Query.
 *
 */

public class ExportDataUsingSQL
{

    private ReportClient rc = Config.getReportClient();

    public void exportData() throws Exception
    {
        String uri = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME);
        String sql = "select \"Customer Name\",Sales,Cost,Profit from Sales where Region = 'East'";
        File expFile = new File("ExportedDataUsingSQL.csv");
        System.out.println(" Exporting data using sql." + sql);
        rc.exportDataUsingSQL(uri,"CSV",expFile,sql,null);
        System.out.println("Data successfully exported to  " + expFile.getAbsolutePath());
    }

    public static void main(String[] args) throws Exception
    {
        ExportDataUsingSQL exp = new ExportDataUsingSQL();

        //call EXPORT API with the same logged in session
        exp.exportData();
    }
}
