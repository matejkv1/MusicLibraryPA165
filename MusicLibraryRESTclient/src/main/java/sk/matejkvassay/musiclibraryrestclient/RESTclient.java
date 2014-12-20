package sk.matejkvassay.musiclibraryrestclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Marián Macik
 */
public class RESTclient {

    public static void main(String[] args) {
        try {
            System.out.println("\n----RestClient for MusicLibrary----\n");
            if (args.length == 0) {
                System.out.println("No parameters specified");
                System.exit(1);
            }

            String method = args[0];

            HttpResponse response = null;

            if ("GET".equalsIgnoreCase(method)) {
                if (args.length != 4 && args.length != 3) {
                    System.out.println("Wrong number of parameters");
                    System.exit(1);
                }
                if (args.length == 4) {
                    response = processGet(args[1], args[2], args[3]);
                } else {
                    response = processGet(args[1], args[2], "");
                }

            } else if ("POST".equalsIgnoreCase(method)) {
                if (args.length != 4) {
                    System.out.println("Wrong number of parameters");
                    System.exit(1);
                }
                response = processPost(args[1], args[2], args[3]);
            } else if ("PUT".equalsIgnoreCase(method)) {
                if (args.length != 5) {
                    System.out.println("Wrong number of parameters");
                    System.exit(1);
                }
                response = processPut(args[1], args[2], args[3], args[4]);
            } else if ("DELETE".equalsIgnoreCase(method)) {
                if (args.length != 3) {
                    System.out.println("Wrong number of parameters");
                    System.exit(1);
                }
                response = processDelete(args[1], args[2]);
            } else {
                System.out.println("HTTP method not recognized");
                System.exit(1);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String output;
            System.out.println("Output from Server .... \n");

            System.out.println(response.getStatusLine());
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            ((CloseableHttpResponse) response).close();

            
        } catch (ConnectException ex) {
            System.out.println("Could not connect to the host. Please check that"
                    + " host is available.");
            System.out.println("");
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static HttpResponse processGet(String format, String entity, String id) throws IOException {
        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();

            String url = "http://localhost:8080/pa165/rest/";

            if ("musician".equalsIgnoreCase(entity)) {
                url = url + "musicians/";
            } else if ("genre".equalsIgnoreCase(entity)) {
                url = url + "genres/";
            } else {
                System.out.println("Entity not recognized");
                System.exit(1);
            }

            url = url + id;

            URI uri = new URI(url);
            HttpGet getRequest = new HttpGet(uri);

            if ("text".equalsIgnoreCase(format)) {
                getRequest.addHeader("accept", "text/plain");
            } else if ("xml".equalsIgnoreCase(format)) {
                getRequest.addHeader("accept", "application/xml");
            } else if ("json".equalsIgnoreCase(format)) {
                getRequest.addHeader("accept", "application/json");
            } else {
                System.out.println("Format not recognized");
                System.exit(1);
            }

            CloseableHttpResponse response = httpClient.execute(getRequest);

            return response;

        } catch (URISyntaxException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Bad URL: " + ex.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static HttpResponse processPost(String format, String entity, String file) throws IOException {
        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();

            String url = "http://localhost:8080/pa165/rest/";

            if ("musician".equalsIgnoreCase(entity)) {
                url = url + "musicians/new";
            } else if ("genre".equalsIgnoreCase(entity)) {
                url = url + "genres/new";
            } else {
                System.out.println("Entity not recognized");
                System.exit(1);
            }

            URI uri = new URI(url);
            HttpPost postRequest = new HttpPost(uri);

            File fi = new File(file);

            if (!fi.exists() || fi.isDirectory()) {
                System.out.println("File with name " + file + " is either directory"
                        + " or does not exist");
                System.exit(1);
            }

            FileEntity input = new FileEntity(new File(file));

            if ("xml".equalsIgnoreCase(format)) {
                input.setContentType("application/xml");
            } else if ("json".equalsIgnoreCase(format)) {
                input.setContentType("application/json");
            } else {
                System.out.println("Format not recognized");
                System.exit(1);
            }

            postRequest.setEntity(input);

            CloseableHttpResponse response = httpClient.execute(postRequest);

            return response;

        } catch (URISyntaxException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Bad URL: " + ex.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static HttpResponse processPut(String format, String entity, String id, String file) throws IOException {
        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();

            String url = "http://localhost:8080/pa165/rest/";

            if ("musician".equalsIgnoreCase(entity)) {
                url = url + "musicians/";
            } else if ("genre".equalsIgnoreCase(entity)) {
                url = url + "genres/";
            } else {
                System.out.println("Entity not recognized");
                System.exit(1);
            }

            url = url + id;

            URI uri = new URI(url);
            HttpPut putRequest = new HttpPut(uri);

            File fi = new File(file);

            if (!fi.exists() || fi.isDirectory()) {
                System.out.println("File with name " + file + " is either directory"
                        + " or does not exist");
                System.exit(1);
            }

            FileEntity input = new FileEntity(new File(file));

            if ("xml".equalsIgnoreCase(format)) {
                input.setContentType("application/xml");
            } else if ("json".equalsIgnoreCase(format)) {
                input.setContentType("application/json");
            } else {
                System.out.println("Format not recognized");
                System.exit(1);
            }

            putRequest.setEntity(input);

            CloseableHttpResponse response = httpClient.execute(putRequest);

            return response;

        } catch (URISyntaxException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Bad URL: " + ex.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static HttpResponse processDelete(String entity, String id) throws IOException {
        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();

            String url = "http://localhost:8080/pa165/rest/";

            if ("musician".equalsIgnoreCase(entity)) {
                url = url + "musicians/";
            } else if ("genre".equalsIgnoreCase(entity)) {
                url = url + "genres/";
            } else {
                System.out.println("Entity not recognized");
                System.exit(1);
            }

            url = url + id;

            URI uri = new URI(url);
            HttpDelete deleteRequest = new HttpDelete(uri);

            CloseableHttpResponse response = httpClient.execute(deleteRequest);

            return response;

        } catch (URISyntaxException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Bad URL: " + ex.getMessage());
            System.exit(1);
        }
        return null;
    }
}
