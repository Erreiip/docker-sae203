package client;


import java.net.*;
import java.io.*;

public class Test{

    public static void main (String args[]){

        String m1, m2;
        Socket toClient;
        ServerSocket ss = null;
        PrintWriter   out = null;
        BufferedReader in = null;


        try {
            ss = new ServerSocket(6969);

            while (true) {

                System.out.println("attente");
                toClient = ss.accept();
                System.out.println("connecté");


                out = new PrintWriter(toClient.getOutputStream(), true);
                in  = new BufferedReader( new InputStreamReader(toClient.getInputStream()));

            
                m1 = in.readLine();

                out.println("004: Ok");

                System.out.println("a");

                in.close();
                out.close();
                toClient.close();
            }
    
        } catch (IOException e) {
            System.out.println(e);
        }
          
    }
        

        

}