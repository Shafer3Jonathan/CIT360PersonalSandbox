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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This function generates a date and timestamp for the generated passwords
 * @author Jonathan
 */
public class DGenerator implements Runnable {
    //Declare session variables
    private Date datetime;
    //what the multithreading function will utilize
    @Override
    public void run() {
        //basically executes the dgenerator function which uses HTTPURL Connection to get a date and set it to a variable
        try {
            this.DGenerator();
        } catch (IOException ex) {
            Logger.getLogger(DGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Uses HTTPURL connection to get a date and set it to a variable that gets called on at a later time
    private void DGenerator() throws MalformedURLException, IOException{
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
    }
    //getter for the datetime variable
    public Date getDatetime() {
        return datetime;
    }
    
}
