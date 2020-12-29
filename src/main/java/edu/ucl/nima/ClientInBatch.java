import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClientInBatch {


    static Random rd;


    public static void main(String[] args) throws InterruptedException, IOException {

        if (args.length != 6) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number> <servermodel (e.g. simple or optimize)> <numberOfClient> <inputfilename.txt> <mean for Exp. Dist>");
            System.exit(1);
        }

        rd = new Random();
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String ServerModel=args[2];
        int nbClients = Integer.parseInt(args[3]);
        String inputfilename=args[4];
        Float mean=Float.parseFloat(args[5]);

        // Reading Examples
        List<String> examples = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(inputfilename));
        
        while ((line = reader.readLine()) != null) {
            examples.add(line);
        }
        
        reader.close();
        

        
        Thread[] threads = new Thread[nbClients + 1];

        for (int i = 1; i <= nbClients; i++) {
            int random = (int) (Math.random() * examples.size()-1);
            threads[i] = new Thread(new EachClient(hostName,portNumber, i + "", ServerModel+";"+examples.get(random)));
        }
        long start = System.currentTimeMillis();
        for (int i = 1; i <= nbClients; i++) {
            // Wating Time
            Long waitTime=(long) expGenerator(1/mean);
            System.out.println("Waiting Time in milliseconds:" + waitTime);
            Thread.sleep(waitTime);
            threads[i].start();
        }
        long end = System.currentTimeMillis();
        long timeElapsed= end-start;
        System.out.println("Arrival rate per mills.: " + timeElapsed/nbClients);
        System.out.println("Total time sending: " + timeElapsed);

    }

    public static double expGenerator(float lambda) {
        return Math.log(1-rd.nextDouble())/(-lambda);
    }
}