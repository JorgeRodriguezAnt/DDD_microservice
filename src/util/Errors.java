package util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Errors {
    
    private List<String> errors;

    public void append(Exception e){
        System.out.println("Error in Json. The file must have the following structure:");
        toJSON();
            
       
            
    }
    
    public void toJSON(){
        try {
            File myObj = new File("src/util/ErrorJson.json");
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
        }  /*  System.out.println("generar json"); */
        //Generar JSON de salida
        /*  errors = new ArrayList<>();
        errors.add("{");
        errors.add("}");
        for (int i = 0; i < errors.size(); i++) {
            System.out.println(errors.get(i));
        }
		  */
        

    

}
