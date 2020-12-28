/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private final Socket server;
    private final BufferedReader in;
    private final PrintWriter out;

    public ServerConnection(Socket s) throws IOException{
        this.server=s;
        this.in=new BufferedReader(new InputStreamReader(this.server.getInputStream()));
        this.out=new PrintWriter (server.getOutputStream(),true);

    }

    @override
    public void run(){
        out.println("test");
    }
    
}
*/