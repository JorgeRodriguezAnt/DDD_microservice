package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.TransformerClass;



public class CreateRepository {

    private final static String nameRepository = "Repository";
    
    public void createFile(List<TransformerClass> classesToTransform) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File( nameRepository +".java");  
            if (myObj.createNewFile()) {  
              System.out.println("File created: " + myObj.getName());  
              System.out.println("Absolute path: " + myObj.getAbsolutePath());  
            } else {  
              System.out.println("File already exists.");  
            }  
            writeFile(classesToTransform);
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();  
          } 
    }

    
    public void writeFile(List<TransformerClass> classesToTransform) {
        
        try {
            
            try (FileWriter myWriter = new FileWriter( nameRepository +".java")) {

                for (TransformerClass transformerClass : classesToTransform) {
                    if(transformerClass.stereotype.equals("Aggregate Root")){
                        myWriter.write("public interface " + nameRepository + " extends CrudRepository <");
                        myWriter.write(transformerClass.name +", ");
                        for (Attribute attClass : transformerClass.attributes) {
                            if(attClass.Name.contains("id") || attClass.Name.contains("Id") || attClass.Name.contains("ID") || attClass.Name.contains("iD")){
                                myWriter.write(attClass.Type + "> {} \n\n");
                                break;
                            }
                            
                        }

                         
                    }
                }
                
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}

