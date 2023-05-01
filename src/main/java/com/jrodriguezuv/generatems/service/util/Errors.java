package com.jrodriguezuv.generatems.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Errors {
    
  
    /* private List<String> errors; */

    public void append(Exception e){
        
        
        toJSON();
        
    }
    public void toJSON(){
      System.out.println("Error in Json. The file must have the following structure:");
        try {
            File myObj = new File("src/util/Error.json");
            Scanner myReader = new Scanner(myObj);  
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          } 
        } 
        

    

}
