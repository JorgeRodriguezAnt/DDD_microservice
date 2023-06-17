package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.modelTransformation.Attribute;
import com.jrodriguezuv.generatems.service.modelTransformation.Operation;
import com.jrodriguezuv.generatems.service.modelTransformation.TransformerClass;



public class CreateRepository {

    private final static String nameRepository = "Repository";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR ;

    public void invokeRepository(String classId, String className, String classStereotype, String classVisibility,
        String classAbstract, String classFather, List<Attribute> attributes, List<Operation> operations) {
          createFile(className);
          writeFile(className,attributes);
    } 

    public void createFile(String className) {
        // TODO Auto-generated method stub
        try {  

            
            File myObj = new File("MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\repository\\" +className+nameRepository +".java");
               /* "MS\\tests\\src\\main\\java\\com\\example\\spring\\r2dbc\\mysql\\repository\\"+nameClassAR+nameRepository +".java");   */
            if (myObj.createNewFile()) {  
              System.out.println("File created: " + myObj.getName());  
              System.out.println("Absolute path: " + myObj.getAbsolutePath());  
            } else {  
              System.out.println("File already exists.");  
            }  
            
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();  
          } 
    }

    
    public void writeFile(String className, List<Attribute> attributes) {
        
        try {
            
            try (FileWriter myWriter = new FileWriter( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\repository\\"+className+nameRepository +".java")) {

                 //Package
                 myWriter.write("package com.demo.spring.jpa.msGenerate.repository;\n\n");

                //import
                myWriter.write("import org.springframework.data.jpa.repository.JpaRepository;\n");
                myWriter.write("import org.springframework.stereotype.Repository;\n");
                myWriter.write("import com.demo.spring.jpa.msGenerate.model.*;\n\n");

                myWriter.write("@Repository\n");
                myWriter.write("public interface " + className + "Repository extends JpaRepository<" + className + ", ");
                for (Attribute attribute : attributes) {
                  if(attribute.Name.contains("id") || attribute.Name.contains("id")){
                    myWriter.write(attribute.Type.substring(0, 1).toUpperCase() + attribute.Type.substring( 1).toLowerCase());
                  }
                }
                myWriter.write(">{}\n");
                
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        }

    
       
        
        

}

