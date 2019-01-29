package client;
import java.io.BufferedOutputStream;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;

import msharpmodel.*;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.engine.Engine;
import org.restlet.engine.util.Base64;
import org.restlet.ext.odata.Query;
import org.restlet.test.ext.odata.deepexpand.model.Email;
import org.restlet.util.Series;
import Zoho.ZohoReportClient.samples.source.AddRow;
import Zoho.ZohoReportClient.samples.source.DeleteData;


public class MarketSharpClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine.getInstance().getRegisteredAuthenticators().add(new CustomAuthenticationHelper());
        MarketSharpCrmApiService service = new MarketSharpCrmApiService();
        service.setCredentials(new ChallengeResponse(ChallengeScheme.CUSTOM));
        try {
        	DeleteData del = new DeleteData();
        	del.deleteData();} catch (Exception ex) {
                Logger.getLogger(MarketSharpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
          try {
        	  //testLeadPaintAttachment(service);
       //testAppointmentQuery(service);
            //testAppointmentQuery2(service);
            //testJob(service);
      //testContact(service);
            //testContact2(service);
            //testContactType(service);
   //testProductType(service);
            //testProductDetail(service);
            //testInquirySourcePrimary(service);
            //testInquirySourceSecondary(service);
            //testNoteQuery(service);
    main_queury(service);       
            //testService(service);
        	  //testing(service);
      System.out.println("Done");
          } catch (Exception ex) {
              Logger.getLogger(MarketSharpClient.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    private static void testLeadPaintAttachment(MarketSharpCrmApiService service)
    {
        Query<LeadPaintAttachment> leadPaintAttachmentQuery = service.createLeadPaintAttachmentQuery("/LeadPaintAttachments").top(10);
        
        for (LeadPaintAttachment leadPaintAttachment : leadPaintAttachmentQuery)
        {
            System.out.println("Attachment");
            System.out.println("ID:" + leadPaintAttachment.getId());
            System.out.println("ATTACHEMENT PATH:" + leadPaintAttachment.getAttachmentPath());
            System.out.println("ATTACHEMENT FILENAME:" + leadPaintAttachment.getAttachmentFileName());
        }
    }
    
    private static byte[] getLeadPaintFile(MarketSharpCrmApiService service, String guid) throws Exception
    {
        Series<Parameter> fargs = new Form();
        fargs.add("id", guid);
        return Base64.decode(service.invokeSimple("GetLeadPaintFile", fargs));
    }
    
    private static void testLeadPaintFileAttachment(MarketSharpCrmApiService service, String filename, String guidLeadPaintAttId)
    {
        BufferedOutputStream fout = null;
        try {
            fout = new BufferedOutputStream(new FileOutputStream(filename));
            fout.write(getLeadPaintFile(service, guidLeadPaintAttId));
            fout.close();
        } catch (Exception ex) {
            Logger.getLogger(MarketSharpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(MarketSharpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void testContact2(MarketSharpCrmApiService service)
    {
        Query<Contact> contactQuery = service.createContactQuery("/Contacts")
                .expand("Address")
                .filter("ContactPhone/homePhone eq 'Smith'");
        
        for (Contact contact : contactQuery)
        {
            System.out.println("Contact");
            System.out.println("ID:" + contact.getId());
            System.out.println("FIRST NAME:" + contact.getFirstName());
            System.out.println("LAST NAME:" + contact.getLastName());
           
        }
    }
    
    private static void testContacting(MarketSharpCrmApiService service)
	{
	    //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
	    Query<Contact> contactQuery = service.createContactQuery("/Contacts").top(20);
	
	    //ENTITY VARIABLES
	    List<CustomField> customFields;
	    
	    //LOOP THROUGH ITERATOR
	    //for (Contact contact : contactQuery)
	    Iterator<Contact> con = contactQuery.iterator();
	    Contact contact = con.next();
	    for(int x = 0;x<10;x++)
	    {
	        try {
				System.out.println("ID"+contact.getId());
				//System.out.println("First Name"+ contact.getFirstName());
				System.out.println("First Name" + contact.getFirstName());
				System.out.println("Business name"+ contact.getBusinessName());
				//System.out.println("Email Adress"+ contact.getSource());
				//ContactPhone c = contact.getContactPhone().getCellPhone();
				//System.out.println("Phone No:"+c.getCellPhone());
				//AddRow.addRow("Address", contact.getPrimaryAddressId());
				//AddRow.addRow("Source", contact.getSource());
				System.out.println("Last Update"+ contact.getLastUpdate().toString());
				
				customFields = contact.getCustomField();
	            for (CustomField customField : customFields)
	            {   System.out.println("Name"+ customField.getName());
	            
	            }
	        } catch (Exception NullPointerException) {
				// TODO Auto-generated catch block
				NullPointerException.printStackTrace();
		
	        }
	       contact = con.next();
	    }
	}

	private static void testJob(MarketSharpCrmApiService service)
    {
        Query<Job> jobQuery = service.createJobQuery("/Jobs")
                .expand("Contact")
                .filter("completedDate gt datetime'2011-01-01T00:00:00'");
        
        Contact contact;
        
        for (Job job : jobQuery)
        {
            contact = job.getContact();
            
            System.out.println("Contact");
            System.out.println("ID:" + contact.getId());
            System.out.println("FIRST NAME:" + contact.getFirstName());
            System.out.println("LAST NAME:" + contact.getLastName());
            
            System.out.println("Job");
            System.out.println("ID:" + job.getId());
            System.out.println("NAME:" + job.getName());
        }
    }
    
    private static void testContactType(MarketSharpCrmApiService service)
    {
        //CREATE QUERY OBJECT
        Query<ContactType> contactQuery = service.createContactTypeQuery("/ContactTypes").top(10);
        contactQuery = contactQuery.expand("Contact");
        contactQuery = contactQuery.filter("contactType eq '3' and Contact/isActive");
        
        //LOOP THROUGH ITERATOR
        for (ContactType contactType : contactQuery)
        {
            System.out.println("Contact");
            System.out.println("ID:" + contactType.getId());
            System.out.println("FIRST NAME:" + contactType.getContact().getFirstName());
            System.out.println("LAST NAME:" + contactType.getContact().getFirstName());
        }
        }
    
        
    private static void testProductType(MarketSharpCrmApiService service) throws Exception
    {
        //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
        Query<ProductType> productTypeQuery = service.createProductTypeQuery("/ProductTypes").expand("Company").top(30);

        //ENTITY VARIABLES
        Company company;

        //LOOP THROUGH ITERATOR
        for (ProductType productType : productTypeQuery)
        {//productType.get
            //company = productType.getCompany();
            String[] id = {"Product ID","Company Add","ProductNAME:","Email"};
            String getid = productType.getId();
            String getname =productType.getName();
            String getfutureinterest = productType.getCompany().getAddressCity();
            //String ids = company.getEmail();
            
            //String[] data = {getid,getfutureinterest,getname,ids};
            
            //AddRow.addRow(id, data);
           
        }
    }
    
    private static void testProductDetail(MarketSharpCrmApiService service)
    {
        //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
        Query<ProductDetail> productDetailQuery = service.createProductDetailQuery("/ProductDetails").expand("Company");

        //ENTITY VARIABLES
        Company company;

        //LOOP THROUGH ITERATOR
        for (ProductDetail productDetail : productDetailQuery)
        {
            company = productDetail.getCompany();
            System.out.println("Product Detail");
            System.out.println("ID:" + productDetail.getId());
            System.out.println("NAME:" + productDetail.getName());
            
            System.out.println("Company");
            System.out.println("ID:" + company.getId());
            System.out.println("NAME:" + company.getName());
        }
    }
    
    private static void testInquirySourcePrimary(MarketSharpCrmApiService service)
    {
        //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
        Query<InquirySourcePrimary> inquirySourcePrimaryQuery = service.createInquirySourcePrimaryQuery("/InquirySourcePrimaries").expand("Company").top(10);

        //ENTITY VARIABLES
        Company company;

        //LOOP THROUGH ITERATOR
        for (InquirySourcePrimary inquirySource : inquirySourcePrimaryQuery)
        {
        	try {
        		System.out.println("Inquiry_source_ID"+inquirySource.getId());
System.out.println("First Name"+ inquirySource.getName());
System.out.println("City"+inquirySource.getCompany().getAddressCity());
System.out.println("Email"+ inquirySource.getCompany().getEmail());
				
				      
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    private static void testInquirySourceSecondary(MarketSharpCrmApiService service)
    {
        //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
        Query<InquirySourceSecondary> inquirySourceSecondaryQuery = service.createInquirySourceSecondaryQuery("/InquirySourceSecondaries").expand("Company");

        //ENTITY VARIABLES
        Company company;

        //LOOP THROUGH ITERATOR
        for (InquirySourceSecondary inquirySource : inquirySourceSecondaryQuery)
        {
            company = inquirySource.getCompany();
            System.out.println("Inquiry Source Secondary");
            System.out.println("ID:" + inquirySource.getId());
            System.out.println("NAME:" + inquirySource.getName());
            
            System.out.println("Company");
            System.out.println("ID:" + company.getId());
            System.out.println("NAME:" + company.getName());
        }
    }
    
    private static void testAppointmentQuery2(MarketSharpCrmApiService service) throws Exception
    {
        Query<Appointment> appointmentsQuery = service.createAppointmentQuery("/Appointments");
        appointmentsQuery = appointmentsQuery.expand("Inquiry");
        appointmentsQuery = appointmentsQuery.filter("lastUpdate gt datetime'2011-12-01T15:45:01'").top(30);
        System.out.println("Pulling appointments from 2011-12-01T04:45:01.");
        appointmentsQuery = appointmentsQuery.orderBy("lastUpdate");
        
        //LOOP THROUGH ITERATOR
        Inquiry inquiry;
        int count = 0;
        for (Appointment appointment : appointmentsQuery)
        {
            count++;
            //LOAD NAVIGATION OBJECTS
            inquiry = appointment.getInquiry();

            //PRINT OUT RESULTS
            try {
            	System.out.println("Appointment ID"+appointment.getId().toString());
				System.out.println("Appointment Date"+ appointment.getSetDate().toString());
				System.out.println("Salesperson1"+ appointment.getSalesperson1().getName());
				System.out.println("Subject"+ appointment.getSubject());
				System.out.println("LastUpdate"+ appointment.getLastUpdate().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            if (inquiry != null) {
            	System.out.println("-->INQDT:"+inquiry.getInquiryDate().toString());
            	System.out.println("-->NOTE:" + inquiry.getNote().toString());
            }
        }
        
        System.out.println("Record Count: " + count);
    }

    private static void testAppointmentQuery(MarketSharpCrmApiService service) {

        //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
        Query<Appointment> soldAppointmentsQuery = service.createAppointmentQuery("/Appointments")
                .expand("Inquiry,Inquiry/InquirySourcePrimary,Inquiry/InquirySourceSecondary,AppointmentResult,Salesperson1")
                .filter("lastUpdate gt datetime'2017-12-19T07:00:00'and AppointmentResult/sold").top(25);

        //ENTITY VARIABLES
        Inquiry inquiry;
        AppointmentResult appointmentResult;
        Employee salesperson1;
        
        //LOOP THROUGH ITERATOR
        int count = 0;
        for (Appointment appointment : soldAppointmentsQuery)
        {
            count++;
            //LOAD NAVIGATION OBJECTS
            inquiry = appointment.getInquiry();
            appointmentResult = appointment.getAppointmentResult();
            salesperson1 = appointment.getSalesperson1();
            //appointment.getsa

            //PRINT OUT RESULTS
            //System.out.println();
            System.out.println("Appointment");
            System.out.println("ID:" + appointment.getId());
            System.out.println("TYPE:" + appointment.getType());
            System.out.println("RSLTRSN:" + appointment.getResultReason());
            System.out.println("SETDT:" + appointment.getSetDate());
            System.out.println("SUBJ:" + appointment.getSubject());
            System.out.println("APPTDT:" + appointment.getAppointmentDate());
            System.out.println("INQID:" + appointment.getInquiryId());
            System.out.println("LASTUPDATE:" + appointment.getLastUpdate().toString());

            if (inquiry != null) {
                System.out.println();
                System.out.println("-->Inquiry");
                System.out.println("-->ID:" + inquiry.getId());
                System.out.println("-->CONTACTID:" + inquiry.getContactId());
                System.out.println("-->DESC:" + inquiry.getDescription());
                if (inquiry.getInquirySourcePrimary() != null) {
                    System.out.println("-->INQSRCPRI:" + inquiry.getInquirySourcePrimary().getName());
                }
                if (inquiry.getInquirySourceSecondary() != null){
                    System.out.println("-->INQSRCSEC:" + inquiry.getInquirySourceSecondary().getName());
                }
                System.out.println("-->INQDT:" + inquiry.getInquiryDate());
                System.out.println("-->NOTE:" + inquiry.getNote());
            }

            if (appointmentResult != null) {
                System.out.println();
                System.out.println("-->AppointmentResult");
                System.out.println("-->ID:" + appointmentResult.getId());
                System.out.println("-->NAME:" + appointmentResult.getName());
                System.out.println("-->PRES:" + appointmentResult.getPresentation());
                System.out.println("-->SOLD:" + appointmentResult.getSold());
            }

            if (salesperson1 != null) {
                System.out.println();
                System.out.println("-->Salesperson1");
                System.out.println("-->ID:" + salesperson1.getId());
                System.out.println("-->COMPANYID:" + salesperson1.getCompanyId());
                System.out.println("-->NAME:" + salesperson1.getName());
            }
        }
        
        System.out.println("Record Count: " + count);
    }
    
    private static void testNoteQuery(MarketSharpCrmApiService service)
    {
         Query<Note> noteQuery = service.createNoteQuery("/Notes").top(10);
         
         for(Note note : noteQuery)
         {
             System.out.println("-->Note:" + note.getNote());
         }
    }

    private static void testService(MarketSharpCrmApiService service) {
        try {
            service.createActivityQuery("/Activities").top(10).execute();
            service.createActivityReferenceQuery("/ActivityReferences").top(10).execute();
            service.createActivityResultQuery("/ActivityResults").top(10).execute();
            service.createAdditionalContactQuery("/AdditionalContacts").top(10).execute();
            service.createAddressQuery("/Addresses").top(10).execute();
            service.createAppointmentQuery("/Appointments").top(10).execute();
            service.createC800responseLeadQuery("/C800ResponseLead").top(10).execute();
            service.createCompanyQuery("/Companies").top(10).execute();
            service.createContactPhoneQuery("/ContactPhones").top(10).execute();
            service.createContactQuery("/Contacts").top(10).execute();
            service.createContactTypeQuery("/ContactTypes").top(10).execute();
            service.createContractQuery("/Contracts").top(10).execute();
            service.createCustomFieldQuery("/CustomFields").top(10).execute();
            service.createEmployeeQuery("/Employees").top(10).execute();
            service.createEmployeeInfoQuery("/EmployeeInfoes").top(10).execute();
            service.createFutureInterestQuery("/FutureInterests").top(10).execute();
            service.createInquiryQuery("/Inquiries").top(10).execute();
            service.createInquiryStatusQuery("/InquiryStatuses").top(10).execute();
            service.createInquirySourcePrimaryQuery("/InquirySourcePrimaries").expand("Company").top(10).execute();
            service.createInquirySourceSecondaryQuery("/InquirySourceSecondaries").expand("Company").top(10).execute();
            service.createJobProductCommissionPaymentsQuery("/JobProductCommissionPayments").top(10).execute();
            service.createJobProductCommissionQuery("/JobProductCommissions").top(10).execute();
            service.createJobProductCostQuery("JobProductCosts").top(10).execute();
            service.createJobProductCostTypeQuery("JobProductCostTypes").top(10).execute();
            service.createJobProductDetailQuery("/JobProductDetails").top(10).execute();
            service.createJobProductQuery("/JobProducts").top(10).execute();
            service.createJobQuery("/Jobs").top(10).execute();
            service.createLeadPaintAttachmentQuery("/LeadPaintAttachments").top(10).execute();
            service.createLeadPaintFirmQuery("/LeadPaintFirms").top(10).execute();
            service.createLeadPaintQuery("/LeadPaints").top(10).execute();
            service.createLeadPaintRenovatorQuery("/LeadPaintRenovators").top(10).execute();
            service.createLeadPaintToFirmQuery("/LeadPaintToFirms").top(10).execute();
            service.createLeadPaintToRenovatorQuery("/LeadPaintToRenovators").top(10).execute();
            service.createLeadPaintToWorkerQuery("/LeadPaintToWorkers").top(10).execute();
            service.createLeadPaintWorkerQuery("/LeadPaintWorkers").top(10).execute();
            service.createLoanQuery("/Loans").top(10).execute();
            service.createNoteQuery("/Notes").top(10).execute();
            service.createPaymentHistoryQuery("/PaymentHistories").top(10).execute();
            service.createProcessStepQuery("/ProcessSteps").top(10).execute();
            service.createProductInterestQuery("/ProductInterests").top(10).execute();
            service.createProductDetailQuery("/ProductDetails").expand("Company").top(10).execute();
            service.createProductTypeQuery("/ProductTypes").expand("Company").top(10).execute();
            service.createProposalQuery("/Proposals").top(10).execute();
            service.createServiceOrderQuery("/ServiceOrders").top(10).execute();
            service.createSurveyQuery("/Surveys").top(10).execute();
            service.createWorkCrewQuery("/WorkCrews").top(10).execute();
        } catch (Exception ex) {
            Logger.getLogger(MarketSharpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private static void main_queury(MarketSharpCrmApiService service) throws Exception
{	String FullName=""; String FirstName=""; String LastName=""; String Company=""; String Address=""; String City=""; String State=""; String Zip=""; String County=""; String PhoneNumber=""; String EmailAddress=""; String 
	Primaryleadsource=""; String secondaryleadsource=""; String Promoter=""; String cansser=""; String Telemarketing=""; String inquerytakenby=""; String inquirydate=""; String inquirystatus=""; String 
	InquiryDivision=""; String Inquirypricequoted="0.0"; String Salesperson1=""; String Salespersn2=""; String AppointmentSetBy=""; String AppointmentSetDate=""; String AppointmentDate=""; String 
	AppointmentType=""; String APP_result=""; String AppointmentResultReason=""; String contractdate=""; String ContractStatus=""; String ContractTotal ="0.0"; String ContractBalanceDue="0.0"; String jobname=""; String jobnumber=""; String JobSaleDate=""; String Jobcompletetiondate=""; String jobstatus=""; String jobtype=""; String productinterestsummary=""; String ProductInterestSummaryw$=""; String 
	Itempurchasedsummary=""; String Itempurchasedsummaryw$=""; String Itempurchasedsummary_all="";
    String[] id = {"Full Name","First Name","Last Name","Company","Address","City","State","Zip","County","Phone Number","Email Address"
    		,"Primary Lead Source","Secondary Lead Source","Promoter","Canvesser","Telemarketing","Inquiry Taken By","Inquiry Date/Time",
    		"Inquiry Status","Inquiry Division","Inquiry Price Quoted","Salesperson 1","Salesperson 2","Appointment Set By",
    		"Appointment Set Date","Appointment Date","Appointment Type","Appointment Result","Appointment Result Reason",
    		"Contract Date","Contract Status","Contract Total","Contract Balance Due","Job Name","Job Number","Job Sale Date",
    		"Job Completion Date","Job Status","Job Type","Product Interest Summary","Product Interest Summary w/$","Item Purchased Summary"
    		,"Item Purchased Summary w/$","Item Purchased Summary-All"};
    //CREATE QUERY OBJECT TO RETRIEVE APPOINTMENTS
	Query<Contact> contactQuery = service.createContactQuery("/Contacts").expand("ContactPhone,Inquiry,Address,Activity,FutureInterest"
			+ ",Inquiry/Appointment,Inquiry/Appointment/Job,Inquiry/Canvasser,Inquiry/Promoter,Inquiry/Telemarketer,Inquiry/Appointment/Salesperson1,Inquiry/Appointment/Salesperson2,"			
			+ "Inquiry/ProductInterest/ProductType,Inquiry/Appointment/SetBy,Inquiry/Appointment/Proposal,Inquiry/InquirySourcePrimary,Inquiry/InquirySourceSecondary,"
			+ "Inquiry/Appointment/Job/Contract,Inquiry/InquiryStatus,Inquiry/Appointment/AppointmentResult,Inquiry/SetBy").top(50);
    //ENTITY VARIABLES
	Address add ;
	Employee app_set;
	Employee salesp1;
	Employee salesp2;
	Employee can;
	List<Appointment> appointment;
	Employee tele;
	List<Proposal> proposal;
	Employee prom;
	ProductType pt;
	Employee setby;
	AppointmentResult app_res;
	ContactPhone contactphone;
	if(contactQuery!=null) {
    for (Contact c:contactQuery)
    {  FullName="";  FirstName="";  LastName="";  Company="";  Address="";  City="";  State="";  Zip="";  County="";  PhoneNumber="";  EmailAddress="";  
	Primaryleadsource="";  secondaryleadsource="";  Promoter="";  cansser="";  Telemarketing="";  inquerytakenby="";  inquirydate="";  inquirystatus="";  
	InquiryDivision="";  Inquirypricequoted="0.0";  Salesperson1="";  Salespersn2="";  AppointmentSetBy="";  AppointmentSetDate="";  AppointmentDate="";  
	AppointmentType="";  APP_result="";  AppointmentResultReason="";  contractdate="";  ContractStatus="";  ContractTotal ="0.0";  ContractBalanceDue="0.0";  jobname="";  jobnumber="";  JobSaleDate="";  Jobcompletetiondate="";  jobstatus="";  jobtype="";  productinterestsummary="";  ProductInterestSummaryw$="";  
	Itempurchasedsummary="";  Itempurchasedsummaryw$="";  Itempurchasedsummary_all="";
    	try { 
    		//String ID = contact.getId();
    		
			List<Inquiry> Inquiry = c.getInquiry();
			if(Inquiry!=null)
			{	FirstName =  c.getFirstName();
			 LastName = c.getLastName();
			 FullName = FirstName +" "+ LastName;
			 Company = c.getBusinessName();
			add = c.getAddress();
			if(add!=null) {
				 Address = add.getLine1();		
				 City= add.getCity();
				 State= add.getState();
				 Zip= add.getZip();
				 County= add.getCoounty();}
			contactphone = c.getContactPhone();
			if(contactphone!=null) {
			 PhoneNumber = contactphone.getCellPhone();
			if(PhoneNumber == "") {PhoneNumber = contactphone.getCellPhone2();}
			}
			 EmailAddress= c.getEmail1();
				for(Inquiry i : Inquiry) {
				
				InquirySourcePrimary is = i.getInquirySourcePrimary();
				if(is!=null) { 
				Primaryleadsource = is.getName();}
		    	InquirySourceSecondary ss = i.getInquirySourceSecondary();
				 if(ss!=null) {secondaryleadsource = ss.getName();}
				can = i.getCanvasser();
				 if(can!=null) {cansser=can.getName();}
				prom = i.getPromoter();
				 if(prom!=null) {Promoter = prom.getName();}
				 setby = i.getSetBy();
				 if(setby!=null) {
				 inquerytakenby = setby.getName(); }
				tele = i.getTelemarketer();
				if(tele!=null) {
				Telemarketing = tele.getName();}
				if(i.getInquiryDate()!=null) {inquirydate = i.getInquiryDate().toString();}
				InquiryStatus status = i.getInquiryStatus();
				if(status!=null) {
				inquirystatus = status.getName();}
				if(i.getDivision()!=null) {InquiryDivision = i.getDivision();}
				
				List<ProductInterest> product_interest = i.getProductInterest();
				if(product_interest!=null) {
					for(ProductInterest pi : product_interest) {
						pt = pi.getProductType();
						 productinterestsummary = pt.getName();
						 Inquirypricequoted =String.valueOf( pi.getPriceQuoted());
					}
				}
				appointment = i.getAppointment();
				if(appointment!=null) {
				for(Appointment a:appointment) {
					proposal = a.getProposal();
					salesp1 = a.getSalesperson1();
					if(salesp1!=null) {
					 Salesperson1 = salesp1.getName();}
					salesp2 = a.getSalesperson2();
					if(salesp2!=null) {
					 Salespersn2 = salesp2.getName();}
					app_set = a.getSetBy();
					if(app_set!=null) { 
					AppointmentSetBy = app_set.getName();}
					if(a.getSetDate()!=null) {
					 AppointmentSetDate = a.getSetDate().toString();}
					if(a.getAppointmentDate()!=null) { 
					AppointmentDate = a.getAppointmentDate().toString();}
					if(a.getType()!=null) {
					AppointmentType = a.getType();}
					
					
					app_res = a.getAppointmentResult();
					if(app_res!=null) {
					APP_result = app_res.getName();}
					 AppointmentResultReason = a.getResultReason();
					 List<Job> joblist = a.getJob();
				    	if(joblist!=null) {
				    		int count = 0;
				    	for(Job j : joblist) {
				    		count++;
				    		List<Contract> contract = j.getContract();
				    		if(contract!=null) {
				    		for(Contract con:contract) { 
				    			contractdate = con.getContractDate().toString();
				    			 ContractStatus = con.getStatus();
				    			ContractTotal = String.valueOf(con.getCashTotal());
				    			ContractBalanceDue = String.valueOf(con.getBalanceDue());
				    			
				    		}}
				    		if(j.getCompletedDate()!=null) {
				    		 Jobcompletetiondate = j.getCompletedDate().toString();}
				    		if(j.getSaleDate()!=null) { 
				    		JobSaleDate = j.getSaleDate().toString();}
				    		 jobname=	j.getName();
				    		 jobstatus = j.getStatus();
				    		 jobtype = j.getType();
				    		 jobnumber=j.getNumber();
				    		
				    		}
				    }
				
				}
				
				
			}
				if(productinterestsummary!="") {
				Itempurchasedsummary = productinterestsummary; 
		    	ProductInterestSummaryw$ = productinterestsummary +" -$"+ContractTotal;
				Itempurchasedsummaryw$ = ProductInterestSummaryw$;}
				 Itempurchasedsummary_all = Salesperson1+"-"+Salespersn2+"-"+ProductInterestSummaryw$;
				 String[] data = {FullName,FirstName,LastName,Company,Address,City,State,Zip,County,PhoneNumber,EmailAddress,
						Primaryleadsource,secondaryleadsource,Promoter,cansser,Telemarketing,inquerytakenby,inquirydate,inquirystatus,
						InquiryDivision,Inquirypricequoted,Salesperson1,Salespersn2,AppointmentSetBy,AppointmentSetDate,AppointmentDate,
						AppointmentType,APP_result,AppointmentResultReason,contractdate,ContractStatus,ContractTotal.toString(),ContractBalanceDue.toString()
						,jobname,jobnumber,JobSaleDate,Jobcompletetiondate,jobstatus,jobtype,productinterestsummary,ProductInterestSummaryw$,
						Itempurchasedsummary,Itempurchasedsummaryw$,Itempurchasedsummary_all};
				 AddRow.addRow(id, data);
				   }}
    	
    	 
        
        
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();}}}
}
private static void testing(MarketSharpCrmApiService service) throws Exception
{	
   Query<Contact> contactQuery = service.createContactQuery("/Contacts").expand("Job,Inquiry,Address").top(30);
	//ENTITY VARIABLES
	List<Job> job;
	List<Inquiry> inquiry;
	for (Contact contact :contactQuery)
    {	System.out.println("First Name: "+contact.getFirstName());
    System.out.println("Last Name: "+contact.getLastName());
    	inquiry = contact.getInquiry();
		job = contact.getJob();
		if(job!=null) {for(Job j : job) {
			System.out.println("Addressline1: "+j.getAddressLine1());
			System.out.println("City: "+j.getCity());
			System.out.println("Job name: "+j.getName());
			System.out.println("Zip: "+j.getZip());
			System.out.println("Description: "+j.getDescription());
		}}
		Address address = contact.getAddress();
		if(address !=null) {
			System.out.println("Address City: "+ address.getCity());
			System.out.println("Address Country"+address.getCountry());
			System.out.println("Address County: "+address.getCoounty());
			System.out.println("Address Zip: "+address.getZip());
			System.out.println("Address line : "+address.getLine1());
		}
		if(inquiry!=null) {
		for(Inquiry i : inquiry) {
			System.out.println("Division: "+i.getDivision());
			System.out.println("Job Site City: "+i.getJobSiteCity());
			System.out.println("Job Description: "+i.getDescription());
		}}
    }//for end
	
	
    }}
