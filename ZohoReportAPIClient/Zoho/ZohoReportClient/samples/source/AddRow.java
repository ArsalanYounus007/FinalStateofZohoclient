package Zoho.ZohoReportClient.samples.source;


/**
 *@(#)AddRow.java
 *Copyright (c) 2008  AdventNet, Inc. All Rights Reserved.
 *$Id$
 */
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Properties;

import java.util.Map;

import java.util.Iterator;

import com.adventnet.zoho.client.report.*;
import java.util.Date;

/**
 * The sample code for adding a row to a table. 
 *
 * In this example a row is added to the table "Sales".
 *
 * See SAMPLES_README.html
 *
 */

public class AddRow 
{
    private static ReportClient rc = Config.getReportClient();

    public static < ID,Data> void addRow( ID[] inputArray,Data[] inputArray1) throws Exception
    {
        String uri = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME,"Testing2");
        Map result = rc.addRow(uri,getRowValues(inputArray,inputArray1),null);
        //System.out.println("Successfully added the row to " + uri);
        System.out.println(" The response is " + result);
    }

    private static < ID,Data> HashMap getRowValues(ID[] inputArray,Data[] inputArray1)
    {
        SimpleDateFormat dtFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        HashMap rowValsMap = new HashMap();
        //rowValsMap.put("Date",dtFmt.format(new Date()));
        for(int x =0;x<inputArray.length;x++) {
        	rowValsMap.put(inputArray[x],inputArray1[x]);
        }
        //rowValsMap.put(ID[0],Data[0]);
        //rowValsMap.put(ID[1],Data[1]);
        //rowValsMap.put(ID[2],Data[2]);
        //rowValsMap.put(ID[3],Data[3]);
        //rowValsMap.put(ID[4],Data[4]);
        //rowValsMap.put(ID[5],Data[5]);
        System.out.print(rowValsMap);
        return rowValsMap;
    }


}
