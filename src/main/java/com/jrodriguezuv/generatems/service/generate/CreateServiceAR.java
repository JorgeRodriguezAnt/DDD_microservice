package com.jrodriguezuv.generatems.service.generate;

import java.io.File; 
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.Operation;
import com.jrodriguezuv.generatems.service.model.Parameter;
import com.jrodriguezuv.generatems.service.model.TransformerClass;

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
            File myObj = new File( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\service\\"+ className+serviceClassName+".java");  
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

            try (FileWriter myWriter = new FileWriter( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\service\\"+ className +serviceClassName+ ".java")) {
            
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

              myWriter.write("@Autowired\n");
              myWriter.write(className + "Repository " + className.toLowerCase() + "Repository;\n\n");
              
              for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Entity")){
                  myWriter.write("@Autowired\n");
                  myWriter.write(transformerClass.name + "Repository " + transformerClass.name.toLowerCase() + "Repository;\n\n");
                }
              }

              /* TutorialRepository tutorialRepository; */
              
              //GetAll
              myWriter.write("public List<" + className + "> getAll" + className + "s() {\n");
              myWriter.write("\treturn " + className.toLowerCase() + "Repository.findAll();\n}\n\n");

              //GetPost
              /* */
    
               myWriter.write("public " + className + " create" + className + "(Long Id," + className + " " + className.toLowerCase() + "Request) {\n");
              for (TransformerClass  classes : classesToTransform) {
                System.out.println( "nombre de clases:"+ classes.name);
                if(classes.stereotype.equals("Entity")){
                    myWriter.write(classes.name + " " +"setter" + " = " + classes.name.toLowerCase() + "Repository.findById(Id)\n" );
                    myWriter.write("\t\t\t.orElseThrow(() -> new ResourceNotFoundException(\"Not found with id = \" + Id));\n\n");
                }
              }
              for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Aggregate Root")){
                  for(Attribute  att : transformerClass.attributes){
                    if(!att.getType().toLowerCase().contains("string") && !att.getName().toLowerCase().contains("string") &&
                    !att.getType().toLowerCase().contains("long") && !att.getName().toLowerCase().contains("long")){
                      myWriter.write(className.toLowerCase() + "Request.set" + att.Name.substring(0, 1).toUpperCase() + att.Name.substring(1).toLowerCase() + "(setter);\n");
                    }
                    
                  }
                }
              }
              

             
              myWriter.write("\treturn " + className.toLowerCase() + "Repository.save(" + className.toLowerCase() + "Request);\n}\n\n"); 
              

              myWriter.write("}\n");
              
             
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        }

  
       
        
        

}

