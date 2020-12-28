package edu.ucl.nima;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import edu.ucl.nima.content.ContentGenerator;
import edu.ucl.nima.content.scan.Scan;

public class Server 
{
    private static ArrayList<ConnectionHandler> clientlist=new ArrayList<>();
    //private static ExecutorService pool= Executors.newFixedThreadPool(5);

    public static void main( String[] args ) throws IOException 
    {
        //TODO create a new ContentGenerator instance
        Scan<String> cg = new ContentGenerator();
        System.out.println(cg.getClass());
        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                //TODO update the creation of ConnectionHandler with a reference to the content generator
                ConnectionHandler ClientThread=new ConnectionHandler(clientSocket, cg);
                clientlist.add(ClientThread);
                //pool.execute(ClientThread);
                new Thread(ClientThread).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
