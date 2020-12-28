package edu.ucl.nima.content.scan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {


/*
InputStream is = getClass().getResourceAsStream(filename);
  InputStreamReader isr = new InputStreamReader(is);
  BufferedReader br = new BufferedReader(isr);
  StringBuffer sb = new StringBuffer();
  String line;
  while ((line = br.readLine()) != null) 
  {
    sb.append(line);
  }
  br.close();
  isr.close();
  is.close();
  return sb.toString();
*/

    public static List<String[]> loadData(String fileName) throws IOException {
        List<String[]> dbdata = new ArrayList<>();
        String line;

        InputStream is = Thread.currentThread()
            .getContextClassLoader()
            .getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        
        try {
            while ((line = reader.readLine()) != null) {
                dbdata.add(line.split("@@@"));
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return dbdata;
    }
}