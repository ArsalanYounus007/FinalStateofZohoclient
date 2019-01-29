/**
 *@(#)DeleteData.java
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
 *The sample code for deleting data from a table.  
 *
 * In this example data matching the criteria
 * ("Region" = 'West') and ("Product Category" = 'Stationary')"
 *  are deleted from the Sales table.
 *
 * See SAMPLES_README.html
 */

public class DeleteData
{

    private ReportClient rc = Config.getReportClient();

    public void deleteData() throws Exception
    {
        String uri = rc.getURI(Config.LOGINEMAILID,Config.DATABASENAME,"Testing2");
        
        rc.deleteData(uri,getCriteria(),null);
        System.out.println("Data matching the criteria " + getCriteria() + " successfully deleted  ");
    }

    public static String getCriteria()
    {
        return "";
    }

    public static void main(String[] args)
        throws Exception
    {
        DeleteData del = new DeleteData();

        del.deleteData();
    }
}

