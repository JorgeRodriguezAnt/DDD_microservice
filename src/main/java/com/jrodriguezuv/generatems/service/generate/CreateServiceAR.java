package com.jrodriguezuv.generatems.service.generate;

import java.io.File; 
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.modelTransformation.Attribute;
import com.jrodriguezuv.generatems.service.modelTransformation.Operation;
import com.jrodriguezuv.generatems.service.modelTransformation.Parameter;
import com.jrodriguezuv.generatems.service.modelTransformation.TransformerClass;

import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Public;

import java.io.FileWriter;

public class CreateServiceAR {
    
    private static final String serviceClassName = "Service";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameService;
    String className;

    public void invokeCreateService(List<TransformerClass> classesToTransform) {
     for (TransformerClass  classes : classesToTransform) {
        if(classes.stereotype.equals("Aggregate Root")){
          createFile(classes.name);
          writeFile(classesToTransform); 
        }
     }
     
    } 
    
    public void createFile(String className) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File( "MS/" + createStructureSpringBoot.nameDir + "/src/main/java/com/demo/spring/jpa/msGenerate/service/"+ className+serviceClassName+".java");  
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

    
    public void writeFile(List<TransformerClass> classesToTransform) {
      
        try {
            
            for (TransformerClass transformerClass : classesToTransform) {
              if(transformerClass.stereotype.equals("Aggregate Root"))
              className = transformerClass.name;
            }

            try (FileWriter myWriter = new FileWriter( "MS/" + createStructureSpringBoot.nameDir + "/src/main/java/com/demo/spring/jpa/msGenerate/service/"+ className +serviceClassName+ ".java")) {
            
              //Package
              myWriter.write("package com.demo.spring.jpa.msGenerate.service;\n\n");

              //import
              myWriter.write("import java.util.List;\n");
              myWriter.write("import java.util.*;\n\n");

              myWriter.write("import org.springframework.beans.factory.annotation.Autowired;\n");
              myWriter.write("import org.springframework.stereotype.Service;\n\n");

              myWriter.write("import com.demo.spring.jpa.msGenerate.exception.ResourceNotFoundException;\n");
              myWriter.write("import com.demo.spring.jpa.msGenerate.model.*;\n\n");

              myWriter.write("import com.demo.spring.jpa.msGenerate.repository.*;\n\n");
              
              myWriter.write("@Service\n");
              myWriter.write("public class " + className+serviceClassName+"{\n\n");

              //var repositories
              for (TransformerClass transformerClass : classesToTransform) {
                if(!transformerClass.stereotype.equals("Value Object")){
                  myWriter.write("@Autowired\n");
                myWriter.write(transformerClass.name + "Repository " + transformerClass.name.toLowerCase() + "Repository;\n\n");
                }
                
              }
              
              

              //GetAll EndPoint

              for (TransformerClass transformerClass : classesToTransform) {
                if(!transformerClass.stereotype.equals("Value Object")){
                  myWriter.write("public List<" + transformerClass.name + "> getAll" + transformerClass.name + "s() {\n");
                myWriter.write("\treturn " + transformerClass.name.toLowerCase() + "Repository.findAll();\n}\n\n");
                }
                
              }
              
              //Post Entities EndPoint
              for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Entity")){
                  myWriter.write("public " + transformerClass.name + " create" + transformerClass.name + "(" + transformerClass.name + " " + transformerClass.name.toLowerCase() + ") {\n");
                  myWriter.write(transformerClass.name + " new" + transformerClass.name + " = new " + transformerClass.name + "(");
                  boolean isFirst = true;
                  for (Attribute att : transformerClass.attributes) {
                if (!att.Name.contains("id") && !att.Name.contains("ID")) {
                  if (!isFirst) {
                      myWriter.write(",");
                  } else {
                      isFirst = false;
                  }
                  myWriter.write(transformerClass.name.toLowerCase() + ".get" + att.Name.substring(0, 1).toUpperCase() + att.Name.substring(1).toLowerCase() +"()");
                  
                }
              }
              myWriter.write(");\n");
              myWriter.write("\treturn " + transformerClass.name.toLowerCase() + "Repository.save(new" + transformerClass.name + ");\n}\n\n");
                }
              }
             

              //GetPost
              /* */
    
               myWriter.write("public " + className + " create" + className + "(Long Id," + className + " " + className.toLowerCase() + "Request) {\n");
           
              

             
              myWriter.write("\treturn null;\n}\n\n"); 
              

              myWriter.write("}\n");
              
             
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        }

  
       
        
        

}

