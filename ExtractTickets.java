import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
//import java.sql.Date;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;




//import java.io.File;
//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.io.BufferedWriter;
import com.csvreader.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
// import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;




//import org.json.*;
import com.google.gson.*;

	
public class ExtractTickets {
	
	HttpClientContext hccContext;
	RequestBuilder reqBuilder;
	RequestConfig.Builder rcBuilder;
	HttpClientBuilder hcBuilder; 
	final static String apiEndPoint =  "https://eagltd.freshdesk.com";
	
	public static void main(String args[]) {

	ExtractTickets et = new ExtractTickets();

	try {
	  et.authenticate("XXXXXXXX",apiEndPoint);
	  et.extractTickets();	
		} 
	catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	} 
	catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	} 
	catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	} 
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
    } // end main
	
	private void authenticate(String apiToken, String apiEndpoint) throws MalformedURLException, URISyntaxException {
		

	 	hcBuilder = HttpClientBuilder.create();
	 	reqBuilder = RequestBuilder.post();
        rcBuilder = RequestConfig.custom();
          
        URL url = new URL(apiEndpoint);
        final String urlHost = url.getHost();
        final int urlPort = url.getPort();
        final String urlProtocol = url.getProtocol();
        
        reqBuilder.setUri(url.toURI());
        // Authentication:
        List<String> authPrefs = new ArrayList<>();
        authPrefs.add(AuthSchemes.BASIC);
        rcBuilder.setTargetPreferredAuthSchemes(authPrefs);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(urlHost, urlPort, AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(apiToken, "fsCFCxAZluDV8ZXsKc5M"));
        hcBuilder.setDefaultCredentialsProvider(credsProvider);
        AuthCache authCache = new BasicAuthCache();
        AuthSchemeBase authScheme = new BasicScheme();
        authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
        hccContext = HttpClientContext.create();
        hccContext.setAuthCache(authCache);

     } // end authenticate
	public ArrayList<String> getListOfTicketsNumbers()
	{
		   // This method is reading ticket numbers from a list that's already been generated by FreshDesk's 
		   // export utility in the Freshdesk web UI, and then returning them in an ArrayList

		   // Note that Freshdesk limits API calls to 1000 per hour
		
		 ArrayList<String> tickets = new ArrayList<String>(10);

		 try{
           CsvReader reader = new CsvReader("C:\\bruce\\tickets.csv");
           reader.readHeaders();
           while (reader.readRecord())
           {	
			 String ticketNo = reader.get("TicketId");
			 System.out.println(ticketNo);
			 tickets.add(ticketNo);
		    } // end while
		   reader.close();
		   }
		   catch (Exception e)
		   {
			 System.out.println("File not found: " + e);
		   }
		return tickets;
	} // end getListOfTicketNumbers()
	
	public void extractTickets() throws ClientProtocolException, IOException {

       ArrayList<String> ticketNos = this.getListOfTicketsNumbers(); // a list of ticket numbers, extracted from a CSV file
       RequestConfig rc = rcBuilder.build();
       reqBuilder.setConfig(rc);
       
       for(int i=0;i<ticketNos.size();i++){ 
         // send a JSON request for each ticket in the list   
         HttpGet request = new HttpGet(apiEndPoint + "/helpdesk/tickets/" + ticketNos.get(i) + ".json");
         System.out.println("Parsing ticket #: " + ticketNos.get(i));
         HttpClient hc = hcBuilder.build();
         HttpResponse response = hc.execute(request, hccContext);
         HttpEntity body = response.getEntity();
         InputStream is = body.getContent();
         BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
         String line;
         StringBuilder sb = new StringBuilder();
       /*
       
       UNCOMMENT TO DUMP JSON RESPONSE TO TEXT FILE
       You will also need to uncomment the "out" PrintWriter object in the while loop 

         String outputFileName = "C:\\bruce\\TicketOutput.txt";
         File file = new File(outputFileName);
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)), true);  
  */
         while((line=br.readLine())!=null) { // convert JSON response to String and process the response
           sb.append(line);
           /*  
           out.println(line); // Uncomment to print to file
           */
         } // end while
         
         /* Uncomment to print to file
         out.close();
          */ 
  
         try{
           processJSON(sb.toString(),ticketNos.get(i));
         }
         catch(Exception e){
    	   System.out.println("exception in processJSON method call from extractTickets method");
         }
       } // end for loop
	} // end extractTickets method
       

    private void processJSON(String resp, String ticketNo) throws JsonIOException
    {
        	   // Use Gson to create objects
      try{ 
        resp = resp.substring(19,(resp.length()-1)); // trim {"helpdesk_ticket":} wrapper from resp
        Helpdesk_ticket aTicket;
        Gson gson = new Gson();
        aTicket = gson.fromJson(resp, Helpdesk_ticket.class);
        aTicket.toCSV(aTicket.getDisplay_id(), aTicket.getId());
    	} // end try 
    	catch (Exception e){
    	  System.out.println("Exception in processJSON method: " + e);
    	}
      } // end processJSON
} // end class