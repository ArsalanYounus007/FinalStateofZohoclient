package Zoho.ZohoReportClient.samples.source;

import com.adventnet.zoho.client.report.ReportClient; 
/**
 * 
 * This file contains the common configuration that in the samples.
 * 
 *  Update the necessary configurations below to try out the samples.
 * 
 * 
 **/
public class Config 
{
    static final String DATABASENAME="demoWorkspace";
    static final String LOGINEMAILID="reports@spotlead.com";
    static final String AUTHTOKEN="7b65986d3e7774dd44c48d570595f719";


    //Set USEPROXY to true if proxy is present.
    static final boolean USEPROXY=false;

    static final String PROXYSERVER="your proxy server ip or host name";
    static final int PROXYPORT=80;    
    static final String PROXYUSERNAME="your proxy user name";    
    static final String PROXYPASSWORD="your proxy password";    
    

    public static ReportClient getReportClient()
    {
        ReportClient rc = new ReportClient(Config.AUTHTOKEN);
        if (USEPROXY)
        {
            rc.setProxy(PROXYSERVER,PROXYPORT,
                        PROXYUSERNAME,PROXYPASSWORD,"BOTH");
        }
        System.out.println("ReportClient created successfully");
        return rc;
    }
}
