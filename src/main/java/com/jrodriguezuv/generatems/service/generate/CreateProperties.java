package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.TransformerClass;

public class CreateProperties {
    private final static String nameRepository = "Controller";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR;
    public void createFile(List<TransformerClass> classesToTransform) {
        // TODO Auto-generated method stub
        try {  
            
            File myObj = new File( createStructureSpringBoot.resource+"\\application.properties");  
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
          for (TransformerClass transformerClass : classesToTransform) {
            if(transformerClass.stereotype.equals("Aggregate Root")){
              nameClassAR = transformerClass.name;
            }
          }
            try (FileWriter myWriter = new FileWriter( createStructureSpringBoot.resource+"\\application.properties")) {

             
                myWriter.write("server.port=8091\n");
                myWriter.write("spring.r2dbc.url=r2dbc:mysql://localhost:3306/testdb_spring\n");
                myWriter.write("spring.r2dbc.username=root\n");
                myWriter.write("spring.r2dbc.password=mentira654\n");
        
              
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}



