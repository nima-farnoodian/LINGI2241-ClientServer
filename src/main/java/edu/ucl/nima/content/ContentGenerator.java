package edu.ucl.nima.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucl.nima.content.scan.FullScan;
import edu.ucl.nima.content.scan.IndexedScan;
import edu.ucl.nima.content.scan.Scan;

public class ContentGenerator implements Scan<String> {
    // We need a Map of Scan implementations whose keys will help specify which
    // Scan should be used to process the query
    Map<String, Scan<List<String>>> scans;

    public ContentGenerator() throws IOException {
        System.out.println("Loading Database. Please wait....");
        this.scans = new HashMap<>();
        String fileName = "dbdata.txt";
        FullScan fullScan=new FullScan(fileName);
        IndexedScan indexedScan=new IndexedScan(fileName);

        this.scans.put("simple", fullScan);
        this.scans.put("optimized", indexedScan);
        System.out.println("The server is currently listening.");
    }

    // Add a constructor that populates this.scans with instances for each supported Scan implementation
    

    @Override
    public String inquire(String query) {


        // use the query to determine which Scan should be used
        String[] fragmented=query.split(";");
        String clien_query=fragmented[1]+';'+fragmented[2];
        String Query_mode=fragmented[0];
        List<String> result;
        System.out.println("The query mode is " + Query_mode+ ".");
        result=this.scans.get(Query_mode).inquire(clien_query);
        
        String output="";

        for(int i=0; i<result.size(); i++) {
            output=output+result.get(i); 
        }


        // Old query format: id;regEx
        // New query format: scanType;id;regEx
        // Split the query

        // retrieve scan implementation reference from scans

        //execute the query "id;regEx" against the retrieved Scan implementation

        
        return output;
    }

   
}