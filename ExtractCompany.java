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
import java.util.ArrayList;
import java.util.List;
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
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class ExtractCompany {

    HttpClientContext hccContext;
    RequestBuilder reqBuilder;
    RequestConfig.Builder rcBuilder;
    HttpClientBuilder hcBuilder;
    static String apiEndPoint = "https://eagltd.freshdesk.com";

    public static void main(String args[]) {

            ExtractCompany ec = new ExtractCompany();

            try {
                ec.authenticate("XXXXXXXXXX", apiEndPoint);
                ec.extractCustomers();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            List < String > authPrefs = new ArrayList < > ();
            authPrefs.add(AuthSchemes.BASIC);
            rcBuilder.setTargetPreferredAuthSchemes(authPrefs);
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                new AuthScope(urlHost, urlPort, AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(apiToken, "XXXXXXXXX"));
            hcBuilder.setDefaultCredentialsProvider(credsProvider);
            AuthCache authCache = new BasicAuthCache();
            AuthSchemeBase authScheme = new BasicScheme();
            authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
            hccContext = HttpClientContext.create();
            hccContext.setAuthCache(authCache);

        } // end authenticate

    public void extractCustomers() throws ClientProtocolException, IOException {

            RequestConfig rc = rcBuilder.build();
            reqBuilder.setConfig(rc);

            for (int i = 1; i <= 3; i++) //there are 3 pages of companies
            {
                // send a JSON request for each ticket in the list   
                String appendedAddy = apiEndPoint + "/companies.json?page=" + Integer.toString(i);
                HttpGet request = new HttpGet(appendedAddy);
                System.out.println("Requesting " + request.toString() + "\nParsing page #: " + i);

                HttpClient hc = hcBuilder.build();
                HttpResponse response = hc.execute(request, hccContext);
                HttpEntity body = response.getEntity();
                InputStream is = body.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
                String line;
                StringBuilder sb = new StringBuilder();
                /*
    
                UNCOMMENT TO DUMP JSON RESPONSE TO TEXT FILE
                You will also need to uncomment the "out" PrintWriter object in the while loop */

                String outputFileName = "C:\\bruce\\CompanyOutput.txt";
                File file = new File(outputFileName);
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)), true);

                while ((line = br.readLine()) != null) { // convert JSON response to String and process the response

                    sb.append(line);

                    /*  */
                    out.println(line); // Uncomment to print to file

                }
                /* Uncomment to print to console
                out.close();
                 */

                try {
                    processJSON(sb.toString());
                } catch (Exception e) {
                    System.out.println("exception in processJSON method call from extractTickets method");
                }
            } // end for loop
        } // end extractTickets method

    private void processJSON(String line) throws JsonIOException, IOException {
            System.out.println(line);
            Gson gson = new Gson();

            try {
                Companies[] c = gson.fromJson(line, Companies[].class);
                for (int i = 0; i < c.length; i++) {
                    System.out.println(c[i].getCompany().getCreated_at());
                    c[i].getCompany().toData();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } // end processJSON
}