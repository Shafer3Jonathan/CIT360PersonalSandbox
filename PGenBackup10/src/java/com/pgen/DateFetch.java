/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
/**
 *
 * @author Jonathan
 */
public class DateFetch{ 
    private Date datetime;
    public Date DateFetch() throws MalformedURLException, IOException{
        URL url = new URL("http://www.google.com");
        HttpURLConnection httpCon = 
        (HttpURLConnection) url.openConnection();
        long date = httpCon.getDate();
        if (date == 0) {
            datetime = null;
            System.out.println("No date information.");
        } else {
            datetime = new Date(date);
            System.out.println("Date: " + datetime);
        }
      
        return datetime;
    }
}
