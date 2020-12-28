package edu.ucl.nima.content.scan;

import static edu.ucl.nima.content.scan.DataLoader.loadData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

public class FullScan implements Scan<List<String>> {

    // The default constructor in java will create a method with the same name of
    // the class
    // In this class the method will be named "FullScan".
    // The default constructor takes no arguments and has an empty body
    List<String[]> dbData;

    public FullScan(String filePath) throws IOException {
        this.dbData = loadData(filePath);
    }

 
    public List<String> inquire (String client_input) {


        //System.out.println("The output is given from another class");

        String[] updated_client_input=client_input.split(";"); //splitting the input according to;
        String id=updated_client_input[0]; // types
        String reg_exp=updated_client_input[1];  //regex

        System.out.println(reg_exp);
        
        ArrayList<String> output = new ArrayList<>();
        for(int i=0; i<this.dbData.size(); i++) {
            if (this.dbData.get(i)[0].equals(id) && Pattern.matches(reg_exp, this.dbData.get(i)[1])) {
                output.add(this.dbData.get(i)[0]+"-"+this.dbData.get(i)[1]+"\n");  
            }
        }

        return output;

    }
}
