package ICS_460_Project_01;

import java.net.*;
import java.util.Arrays;
import java.io.*;

public class UDPSender {

   public static void main(String args[]) throws Exception {
       String filename = "input.txt"; //input file
       File file = new File(filename); //creating file object
       FileInputStream f = new FileInputStream(file); //creting input stream
       int BUFFER_SIZE = (int) (file.length() / 12); // getting approximate buffer size
      
       byte bufferout[] = new byte[BUFFER_SIZE]; // array to send
       byte bufferin[] = new byte[BUFFER_SIZE]; // array at receiving side

       DatagramSocket socketOut = new DatagramSocket(2000); //for sender port 2000
       DatagramSocket socketIn = new DatagramSocket(1000); //for receiver port 1000
       int read = 0;
       int count = 1;
       String temp ;
       while ((read = f.read(bufferout)) > 0) { //reading all the file
           temp = new String(bufferout, "UTF-8"); //for printing converting byte-> normal
           System.out.print("\n "+count+". "+temp+" --> "); //for printing
          
           //sending buffer to local host
           // port 1000
           socketOut .send(new DatagramPacket(bufferout, bufferout.length, InetAddress.getLocalHost(), 1000));
          
           //receiving buffer
           //collecting into byte array
           socketIn.receive(new DatagramPacket(bufferin, bufferin.length));
           temp = new String(bufferin, "UTF-8"); //converting UTF-8 format for printing
           System.out.println(temp); //printing
           count ++;
       }
       f.close();
       socketOut .close();
      
   }
}