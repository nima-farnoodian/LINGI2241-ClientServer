package edu.ucl.nima;

import java.io.*;
import java.net.*;
 
public class Client {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 3) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number> <query>");
            System.exit(1);
        }
    
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String query = args[2];
        long startTime = System.nanoTime();
        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        ) {
            out.println(query);
            long start = System.currentTimeMillis();
            String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Response: "+fromServer);
                if (fromServer.equals("done")) {
                    long duration = System.currentTimeMillis() - start;
                    //out.println(duration);
                    System.out.println(duration);
                    break;
                }
            }
            
            /*
            while (!(fromServer = in.readLine()).equals(ConnectionHandler.HANGUP)) {
                if (fromServer.equals(ConnectionHandler.HANDSHAKE)) {
                    out.println(query);
                } else {
                    System.out.println("Response: " + fromServer);
                }
            }
            */
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.exit(1);
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total response time in millis: " + elapsedTime / 1000000);

    }
}