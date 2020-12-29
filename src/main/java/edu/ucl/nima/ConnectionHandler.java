package edu.ucl.nima;

import java.net.*;
import java.sql.Time;
import java.io.*;
///import edu.ucl.nima.content.ContentGenerator;
import edu.ucl.nima.content.scan.Scan;

public class ConnectionHandler implements Runnable {
    public static String HANDSHAKE = "ready";
    public static String HANGUP = "done";

    private final Socket clientSocket;
    private final Scan<String> contentGenerator;

    public ConnectionHandler(Socket clientSocket, Scan<String> contentGenerator) {
        this.clientSocket = clientSocket;
        this.contentGenerator = contentGenerator;
    }

    public void run() {
        String outputLine;
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ){
            String fromClient;
            //System.out.println("In Connection Handler");
            //out.println(HANDSHAKE);
            if ((fromClient = in.readLine()) != null) {
                //System.out.println("Generating content");
                //TODO Fix the call to inquire instead of generate
                //outputLine = new ContentGenerator().generate(fromClient);
                
                long start = System.currentTimeMillis();

                outputLine = contentGenerator.inquire(fromClient);
               
                long end = System.currentTimeMillis();
                long respTime=end-start;
                Server.ClientNo=Server.ClientNo+1;
                Server.responseTime=Server.responseTime+respTime;
                Server.times.add(respTime);
                writeFile("serviceTime",respTime);
                // The following code sends the response to the client
                out.println(outputLine);


            }
            out.println(HANGUP);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read connection");
            System.out.println(e.getMessage());
        }
    }
    private static void writeFile(String filename,long data){
        try {
            File myObj = new File(filename+".csv");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              //System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

          try {
            FileWriter myWriter = new FileWriter(filename+".csv", true);
            myWriter.write(Long.toString(data) +"\n");
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}