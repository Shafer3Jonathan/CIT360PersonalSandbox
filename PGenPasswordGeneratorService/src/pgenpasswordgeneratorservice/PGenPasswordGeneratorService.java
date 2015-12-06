/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgenpasswordgeneratorservice;

import java.io.*;
import java.net.*;

/**
 *
 * @author Jonathan
 */
public class PGenPasswordGeneratorService extends Thread{

    private ServerSocket serverSocket;
   
   public PGenPasswordGeneratorService(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }
 
   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            out.writeUTF("asdoifjwe");
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt("6066");
      try
      {
         Thread t = new PGenPasswordGeneratorService(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
    
}
