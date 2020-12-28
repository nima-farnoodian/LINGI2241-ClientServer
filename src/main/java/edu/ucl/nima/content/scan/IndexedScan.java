package edu.ucl.nima.content.scan;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class IndexedScan implements Scan<List<String>> {

    Map<String, List<String>> dbData;

    public IndexedScan(String filePath) throws IOException {
        List<String[]> tempValue = DataLoader.loadData(filePath);

        this.dbData = new HashMap<>();
        
        for(int i=0; i<tempValue.size(); i++) {        
            String id=tempValue.get(i)[0];
            String txt=tempValue.get(i)[1];

            if (!this.dbData.containsKey(id)) {
                this.dbData.put(id, new ArrayList<String>()); // I changed it, should be checked again
            }

            this.dbData.get(id).add(txt);
        }
    }


    public List<String> inquire (String client_input) {
        String[] fragmented=client_input.split(";");

        String id=fragmented[0]; // types
        String reg_exp=fragmented[1];
        List<String> fetchGroup = this.dbData.get(id);

        ArrayList<String> output = new ArrayList<>();

        for(int i=0; i<fetchGroup.size(); i++) {
            if ( Pattern.matches(reg_exp, fetchGroup.get(i))) {
                output.add(id+"-"+fetchGroup.get(i)+"\n");  
            }
        }

        return output;
    }
}