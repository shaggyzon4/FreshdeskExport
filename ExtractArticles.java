import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

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
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
// import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.*;



public class ExtractArticles {


    HttpClientContext hccContext;
    RequestBuilder reqBuilder;
    RequestConfig.Builder rcBuilder;
    HttpClientBuilder hcBuilder;
    final static String apiEndPoint = "https://eagltd.freshdesk.com";

    public static void main(String args[]) {
            ExtractArticles et = new ExtractArticles();
            try {
                et.authenticate("XXXXXXXXXX", apiEndPoint);
                et.extractKBs();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } // end main

    private void authenticate(String apiToken, String apiEndpoint) throws MalformedURLException, URISyntaxException {
            hcBuilder = HttpClientBuilder.create();
            reqBuilder = RequestBuilder.post();
            rcBuilder = RequestConfig.custom();

            // URL object from API endpoint:

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
                new UsernamePasswordCredentials(apiToken, "fsCFCxAZluDV8ZXsKc5M"));
            hcBuilder.setDefaultCredentialsProvider(credsProvider);
            AuthCache authCache = new BasicAuthCache();
            AuthSchemeBase authScheme = new BasicScheme();
            authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
            hccContext = HttpClientContext.create();
            hccContext.setAuthCache(authCache);

        } // end authenticate

    public void extractKBs() throws ClientProtocolException, IOException {

            RequestConfig rc = rcBuilder.build();
            reqBuilder.setConfig(rc);
            HttpGet request = new HttpGet(apiEndPoint + "/solution/folders/1000221505.json");
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
            */
            String outputFileName = "C:\\Users\\bruce.jenkins\\Desktop\\Case Files\\JSONoutput.txt";
            File file = new File(outputFileName);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)), true);




            while ((line = br.readLine()) != null) {

                sb.append(line);

                /**/
                out.println(line); // Uncomment to print to file

            }
            /* Uncomment to print to file and to console */
            out.close();
            System.out.println("Body:\n" + sb);



            try {
                processJSON(sb.toString());
            } catch (Exception e) {
                System.out.println("exception in processJSON method call");
            }
        } // end extractKBs method


    private void processJSON(String resp) throws JSONException {
        Article[] articles = new Article[50];

        try {

            JSONObject result = new JSONObject(resp).getJSONObject("folder");
            JSONArray entries = result.getJSONArray("articles");

            // System.out.println("Get test element: " + entries.getJSONObject(3));

            for (int j = 0; j < entries.length(); j++) {
                articles[j] = new Article();
                articles[j].setArt_type(entries.getJSONObject(j).getInt("art_type"));
                articles[j].setDesc_un_html(entries.getJSONObject(j).getString("desc_un_html"));
                articles[j].setDescription(entries.getJSONObject(j).getString("description"));
                articles[j].setTitle(entries.getJSONObject(j).getString("title"));
                System.out.println(articles[j].getTitle());


            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

} // end class