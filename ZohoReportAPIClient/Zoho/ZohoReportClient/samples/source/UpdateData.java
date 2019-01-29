/**
 *@(#)UpdateData.java
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


/**
 *The sample code for updating data in a table.
 *
 *In this example Product Category and Product columns in Sales table are updated for  
 * data matching the criteria
 *
 *("Region" = 'West') and ("Product Category" = 'Stationary')"
 *
 * See SAMPLES_README.html
 */


public class UpdateData 
{

    private ReportClient rc = Config.getReportClient();
    
    public void updateData() throws Exception
    {
        String uri = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME,"Sales");
        
        rc.updateData(uri,getValuesToUpdate(),getCriteria(),null);
        System.out.println("Data matching the criteria " + getCriteria() + " successfully updated.");

    }

    private static HashMap getValuesToUpdate()
    {
        SimpleDateFormat dtFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        HashMap rowValsMap = new HashMap();
        rowValsMap.put("Product Category","Sample_Grain");
        rowValsMap.put("Product","Sample_Wheat");
        return rowValsMap;
    }
    
    private static String getCriteria()
    {
        return "(\"Region\" = 'East') and (\"Product Category\" = 'Grocery')";
    }

    public static void main(String[] args) throws Exception
    {
        UpdateData up = new UpdateData();

        //call UPDATE API with the same logged in session
        up.updateData();
    }
}
