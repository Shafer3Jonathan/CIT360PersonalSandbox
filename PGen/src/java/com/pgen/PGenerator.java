/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.net.*;
import java.io.*;

/**
 * This is basically a simple password generator it generates one password this can be further improved at a later time
 * @author Jonathan
 */
public class PGenerator implements Runnable{
    //declare session variables
    private String aPass; 
    //Threading implementation
    @Override
    public void run() {
        //aPass = "fhqgwads";
        this.PasswordIOGet();
    }
    
    private void PasswordIOGet(){
      String serverName = "127.0.0.1";
      int port = Integer.parseInt("6066");
      try
      {
        //System.out.println("Connecting to " + serverName +
	//	 " on port " + port);
         Socket client = new Socket(serverName, port);
        //System.out.println("Just connected to " 
	//	 + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF("Hello from "
                      + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =
                        new DataInputStream(inFromServer);
         aPass = in.readUTF();
         //System.out.println("Server says " + in.readUTF());
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
    }
    //getter for the password
    public String getaPass() {
        return aPass;
    }
}
