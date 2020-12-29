import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class EachClient implements Runnable {
    private final String id;
    private final String regEx;
    private final String host;
    private final int port;

    public EachClient(String host, int port, String id, String regEx) {
        this.id = id;
        this.regEx = regEx;
        this.host=host;
        this.port=port;
    }

    // Méthode thread client qui envoie une requête au serveur
    @Override
    public void run(){
        System.out.println(regEx);

        long start = System.currentTimeMillis();

        try (Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        )
        {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;

            out.println(regEx);
            
            while ((fromServer = in.readLine()) != null) {
                // to show the result on the screen, please uncomment the following line
                //System.out.println("Response: "+fromServer); 

                if (fromServer.equals("done")) {
                    long duration = System.currentTimeMillis() - start;
                    //out.println(duration);
                    //System.out.println(duration);
                    writeFile("resAndComm",duration);
                    break;
                }
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
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