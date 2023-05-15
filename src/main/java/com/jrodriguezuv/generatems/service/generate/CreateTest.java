package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.TransformerClass;

public class CreateTest {
    private final static String nameRepository = "Controller";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR;
    public void createFile(List<TransformerClass> classesToTransform) {
        // TODO Auto-generated method stub
        try {  
            
            File myObj = new File( createStructureSpringBoot.test+"\\SpringBootMainTest.java");  
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
            try (FileWriter myWriter = new FileWriter( createStructureSpringBoot.test+"\\SpringBootMainTest.java")) {

                //Package
                myWriter.write("package com.demo." + createStructureSpringBoot.view_name + ";\n\n\n");


                //import

                myWriter.write("import org.junit.jupiter.api.Test;\n");
                myWriter.write("import org.springframework.boot.test.context.SpringBootTest;\n\n");
                myWriter.write("@SpringBootTest\n");
                myWriter.write("class SpringBootMainTest {\n\n");
                myWriter.write("@Test\n");
                myWriter.write("void contextLoads() {\n}\n}");
                
              
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}



