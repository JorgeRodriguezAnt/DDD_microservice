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

public class CreateService {

    private static final String serviceClassName = "Service";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameService;

    public void invokeCreateService(String classId, String className, String classStereotype, String classVisibility,
        String classAbstract, String classFather, List<Attribute> attributes, List<Operation> operations) {
      createFile(className);
      writeFile(className,attributes,operations);
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

    
    public void writeFile(String className, List<Attribute> attributes, List<Operation> operations) {
        
        try {
            
            try (FileWriter myWriter = new FileWriter( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\service\\"+ className+serviceClassName+ ".java")) {

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

              /* TutorialRepository tutorialRepository; */
              
              //GetAll
              myWriter.write("public List<" + className + "> getAll" + className + "s() {\n");
              myWriter.write("\treturn " + className.toLowerCase() + "Repository.findAll();\n}\n\n");

              //GetPost
              myWriter.write("public " + className + " create" + className + "(" + className + " " + className.toLowerCase() + ") {\n");
              myWriter.write(className + " new" + className + " = new " + className + "("); 
              boolean isFirst = true;
              for (Attribute att : attributes) {
                if (!att.Name.contains("id") && !att.Name.contains("ID")) {
                  if (!isFirst) {
                      myWriter.write(",");
                  } else {
                      isFirst = false;
                  }
                  myWriter.write(className.toLowerCase() + ".get" + att.Name.substring(0, 1).toUpperCase() + att.Name.substring(1).toLowerCase() +"()");
                  
                }
              }
              myWriter.write(");\n");
              myWriter.write("\treturn " + className.toLowerCase() + "Repository.save(new" + className + ");\n}\n\n");

              myWriter.write("}\n");
              /* public Tutorial createTutorial(Tutorial tutorial) {
        Tutorial newTutorial = new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true);
        return tutorialRepository.save(newTutorial);
    }
    } */
              /* for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Entity")){
                  myWriter.write("@Autowired\n");
                  myWriter.write(transformerClass.name + "Repository " + transformerClass.name.toLowerCase()+ "Repository;\n\n" );

                  //constructor
                  myWriter.write("public " + transformerClass.name +"Service(" + transformerClass.name + "Repository " + transformerClass.name.toLowerCase()+ "Repository) {\n");
                  myWriter.write("this." + transformerClass.name.toLowerCase()+ "Repository = " +transformerClass.name.toLowerCase()+ "Repository;\n}\n");


                  myWriter.write("public List<" + transformerClass.name + "> getAll" + transformerClass.name + "s(");
                  for (Attribute transformerClass2 : transformerClass.attributes) {
                    if (!transformerClass2.Name.contains("id") && !transformerClass2.Name.contains("ID")){
                      myWriter.write(transformerClass2.Type + " " + transformerClass2.Name + ") {\n");
                      myWriter.write("List<" + transformerClass.name + "> " + transformerClass.name.toLowerCase() + "s = new ArrayList<>();\n\n");
                      myWriter.write("return " + transformerClass.name.toLowerCase() +"s;\n}\n");
                      break;
                    }
                  }

                  for (Attribute transformerClass2 : transformerClass.attributes) {
                    if (transformerClass2.Name.contains("id") || transformerClass2.Name.contains("ID")){
                      myWriter.write("public " + transformerClass.name + " get" + transformerClass.name + "ById(" + transformerClass2.Type + " " + transformerClass2.Name + ") {\n");
                      myWriter.write("return " + transformerClass.name.toLowerCase() + "Repository.findById(" + transformerClass2.Name + ")\n");
                      myWriter.write("\t.orElseThrow(() -> new ResourceNotFoundException(\"Not found with id = \" + " + transformerClass2.Name + "));\n}\n\n");
                      break;
                    }
                  }


                  myWriter.write("public " + transformerClass.name + " create" + transformerClass.name + "(" + transformerClass.name + " " + transformerClass.name.toLowerCase() + ") {\n");
                  myWriter.write(transformerClass.name + " new" + transformerClass.name + " = new " + transformerClass.name + "(");
                  for (Attribute transformerClass2 : transformerClass.attributes) {
                    if(!transformerClass2.Name.contains("id") && !transformerClass2.Name.contains("ID") ){
                      myWriter.write(transformerClass.name.toLowerCase() + ".get" + transformerClass2.Name.substring(0, 1).toUpperCase() + transformerClass2.Name.substring( 1).toLowerCase() + "()");
                    if(transformerClass.attributes.indexOf(transformerClass2) != transformerClass.attributes.size()-1){
                      myWriter.write(", ");
                    }
                    }
                  }
                  myWriter.write(");\n");
                  myWriter.write("return " + transformerClass.name.toLowerCase() + "Repository.save(new" + transformerClass.name + ");\n}\n");

                  myWriter.write("}\n");
              }

              if(transformerClass.stereotype.equals("Aggregate Root")){
                myWriter.write("@Autowired\n");
                myWriter.write(transformerClass.name + "Repository " + transformerClass.name.toLowerCase()+ "Repository;\n\n" );
              
                //Constructor
                myWriter.write("public " + transformerClass.name +"Service(");
              }  

              for (TransformerClass transformerClass2 : classesToTransform) {
                myWriter.write( transformerClass2.name + "Repository " + transformerClass2.name.toLowerCase() + "Repository");
                if(classesToTransform.indexOf(transformerClass2) != classesToTransform.size()-1){
                  myWriter.write(", ");
                }
              }
              myWriter.write(") {\n");
              for (TransformerClass transformerClass2 : classesToTransform) {
                myWriter.write("this." + transformerClass2.name.toLowerCase() + "Repository = " +transformerClass.name.toLowerCase()+ "Repository;\n");
              }
              myWriter.write("}\n");
         
                if(transformerClass.stereotype.equals("Aggregate Root")){
                  //GetAll
                myWriter.write("public List<" + transformerClass.name + "> getAll" + transformerClass.name + "s() {\n");
                myWriter.write("return " + transformerClass.name + "Repository.findAll();\n}\n\n");


               
                  //GetById
                 for (Attribute transformerClass2 : transformerClass.attributes) {
                  if (transformerClass2.Name.contains("id") || transformerClass2.Name.contains("ID")){
                    myWriter.write("public " + transformerClass.name + " get" + transformerClass.name + "ById(" + transformerClass2.Type + " " + transformerClass2.Name + ") {\n");
                    myWriter.write("return " + transformerClass.name.toLowerCase() + "Repository.findById(" + transformerClass2.Name + ")\n");
                    myWriter.write("\t.orElseThrow(() -> new ResourceNotFoundException(\"Not found with id = \" + " + transformerClass2.Name + "));\n}\n\n");
                    break;
                  }
                } 

                //Post
                myWriter.write("public " + transformerClass.name + " create" + transformerClass.name + "(Long Id," + transformerClass.name + " " + transformerClass.name.toLowerCase() + "Request) {\n");
                for (TransformerClass transformerClass2 : classesToTransform) {
                  if(transformerClass2.stereotype.equals("Entity")){
                    myWriter.write(transformerClass2.name + " " + transformerClass2.name.toLowerCase() + " = " + transformerClass2.name.toLowerCase() + "Repository.findById(Id)");
                    myWriter.write("\t.orElseThrow(() -> new ResourceNotFoundException(\"Not found with id = \" + Id)); \n\n");
                  }
                  if(transformerClass2.stereotype.equals("Entity")){
                    myWriter.write(transformerClass.name.toLowerCase() + "Request.set" + transformerClass2.name + "(" + transformerClass2.name.toLowerCase()+ ");\n");
                  }
                  myWriter.write("return " + transformerClass.name + "Repository.save(" + transformerClass.name.toLowerCase() + "Request);\n}\n");
                }

                myWriter.write("}\n");
            }
            } */
             
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        }

  
       
        
        

}

