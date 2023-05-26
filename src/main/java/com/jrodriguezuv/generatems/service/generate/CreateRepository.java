package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.TransformerClass;



public class CreateRepository {

    private final static String nameRepository = "Repository";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR;
    public void createFile(List<TransformerClass> classesToTransform) {
        // TODO Auto-generated method stub
        try {  
            for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Aggregate Root")){
                  nameClassAR = transformerClass.name;
                }
              }
            File myObj = new File("MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\example\\spring\\r2dbc\\mysql\\repository\\" +nameClassAR+nameRepository +".java");
               /* "MS\\tests\\src\\main\\java\\com\\example\\spring\\r2dbc\\mysql\\repository\\"+nameClassAR+nameRepository +".java");   */
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
            try (FileWriter myWriter = new FileWriter( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\example\\spring\\r2dbc\\mysql\\repository\\"+nameClassAR+nameRepository +".java")) {

                 //Package
                 myWriter.write("package com.example.spring.r2dbc.mysql.repository;\n\n\n");

                //import
                myWriter.write("import org.springframework.data.repository.reactive.ReactiveCrudRepository;\n");
                myWriter.write("import org.springframework.stereotype.Repository;\n");
                myWriter.write("import reactor.core.publisher.Flux;\n");
                //import model and service
                for (TransformerClass transformerClass : classesToTransform) {
                    if(transformerClass.stereotype.equals("Aggregate Root")){
                      myWriter.write("import com.example.spring.r2dbc.mysql.model." + transformerClass.name+";\n\n");
                    }
                  }


                for (TransformerClass transformerClass : classesToTransform) {
                    if(transformerClass.stereotype.equals("Aggregate Root")){
                        myWriter.write("@Repository\n");
                        myWriter.write("public interface " + transformerClass.name+ nameRepository + " extends ReactiveCrudRepository <");
                        
                        myWriter.write(transformerClass.name +", Integer>{}\n\n");
                        /* for (Attribute attClass : transformerClass.attributes) {
                            if(attClass.Name.contains("id") || attClass.Name.contains("Id") || attClass.Name.contains("ID") || attClass.Name.contains("iD")){
                                myWriter.write(attClass.Type + "> {} \n\n");
                                break;
                            }
                             
                        }*/

                         
                    }
                }
                
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}

