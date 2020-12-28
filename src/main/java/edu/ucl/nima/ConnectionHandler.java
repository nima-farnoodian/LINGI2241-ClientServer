package edu.ucl.nima;

import java.net.*;
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
            System.out.println("In Connection Handler");
            //out.println(HANDSHAKE);
            if ((fromClient = in.readLine()) != null) {
                System.out.println("Generating content");
                //TODO Fix the call to inquire instead of generate
                //outputLine = new ContentGenerator().generate(fromClient);
                outputLine = contentGenerator.inquire(fromClient);
                out.println(outputLine);
            }
            out.println(HANGUP);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read connection");
            System.out.println(e.getMessage());
        }
    }
}