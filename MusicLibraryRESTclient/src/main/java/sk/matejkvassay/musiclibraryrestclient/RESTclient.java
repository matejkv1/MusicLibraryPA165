/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryrestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Majo
 */
public class RESTclient {

    public static void main(String[] args) {
        try {

            for (String arg : args) {
                System.out.println(arg);
            }
            System.out.println("HELLOOOOO");

            String method = args[0];

            HttpResponse response = null;

            switch (method) {
                case "GET":
                    response = processGet(args[1], args[2]);
                    break;

            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String output;
            System.out.println("Output from Server .... \n");

            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            
            ((CloseableHttpResponse) response).close();
            
        } catch (IOException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static HttpResponse processGet(String format, String url) {
        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet(
                    url);

            switch (format) {
                case "XML":
                    getRequest.addHeader("accept", "application/xml");
                    break;

            }

            CloseableHttpResponse response = httpClient.execute(getRequest);

//            if (response.getStatusLine().getStatusCode() != 200) {
//                
//            
//            }
            
            
            return response;

        } catch (IOException ex) {
            Logger.getLogger(RESTclient.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
}
