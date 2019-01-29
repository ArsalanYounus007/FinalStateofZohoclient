/**
 *@(#)ImportData.java
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
 *The sample code for importing a csv file into a table.
 *
 * In this example,  the data in the file "StoreSales.csv" present local(/client) machine  
 * is append to the table  "Sales".
 *
 * See SAMPLES_README.html
 */

public class ImportData
{

    private ReportClient rc = Config.getReportClient();

    public void importData(File csvFile) throws Exception
    {
        String uri = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME,"Sales");
        System.out.println("Appending data in file : \n"+ csvFile.getAbsolutePath());
        rc.importData(uri,"APPEND",csvFile,getImportConfig(),false);
        System.out.println("Successfully appended data to the table : \n"+ uri);
    }

    private static Map getImportConfig()
    {
        HashMap config = new HashMap();
        config.put("ZOHO_AUTO_IDENTIFY","true");
        config.put("ZOHO_ON_IMPORT_ERROR","ABORT");
        config.put("ZOHO_CREATE_TABLE","false");
        return config;
    }

    public static void main(String[] args) throws Exception
    {
        ImportData imp = new ImportData();
        File csvFile = new File("samples/StoreSales.csv");
        if (!csvFile.exists())
        {
            throw new RuntimeException(" File " + csvFile.getAbsolutePath() + " does not exist."
                                       + "Update the csvFile variable to point to the proper location.");
        }

        //call IMPORT API with the same logged in session
        imp.importData(csvFile);
    }
}
