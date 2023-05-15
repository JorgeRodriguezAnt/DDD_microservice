package com.jrodriguezuv.generatems.service.generate;

import java.io.File; 
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.Operation;
import com.jrodriguezuv.generatems.service.model.Parameter;
import com.jrodriguezuv.generatems.service.model.TransformerClass;

import java.io.FileWriter;

public class CreateService {

    private static final String serviceClassName = "Service";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameService;

    public void invokeCreateService( List<TransformerClass> classesToTransform){
      for (TransformerClass transformerClass : classesToTransform) {
        if(transformerClass.stereotype.equals("Aggregate Root")){
          nameService = transformerClass.name;
        }
      }
      createFile(classesToTransform);
      writeFile( classesToTransform);
    }

    public void createFile(List<TransformerClass> classesToTransform) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File( createStructureSpringBoot.dString+"\\service\\"+ nameService+serviceClassName+".java");  
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
            
            try (FileWriter myWriter = new FileWriter( createStructureSpringBoot.dString+"\\service\\"+ nameService+serviceClassName+ ".java")) {

              //Package
              myWriter.write("package com.demo." + createStructureSpringBoot.view_name + ".service;\n\n\n");

              //import
              myWriter.write("import java.util.List;\n");
              myWriter.write("import org.springframework.beans.factory.annotation.Autowired;\n");
              myWriter.write("import org.springframework.stereotype.Service;\n");
              myWriter.write("import reactor.core.publisher.Flux;\n");
              myWriter.write("import reactor.core.publisher.Mono;\n");
              //import model and service
              for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Aggregate Root")){
                  myWriter.write("import com.demo." + createStructureSpringBoot.view_name + ".model." + transformerClass.name+";\n\n");
                  myWriter.write("import com.demo." + createStructureSpringBoot.view_name + ".repository." + transformerClass.name.toLowerCase()+"Repository;\n\n");
                }
              }
              
                myWriter.write("@Service\n");
                myWriter.write("public class " + nameService+serviceClassName+"{\n");
                myWriter.write("@Autowired\n");
                
                for (TransformerClass transformerClass : classesToTransform) {
                  if(transformerClass.stereotype.equals("Aggregate Root")){
                    myWriter.write(transformerClass.name + "Repository " + transformerClass.name.toLowerCase()+ "Repository;\n\n" );

                    myWriter.write("public Flux<" + transformerClass.name +"> findAll() {\n");
                    myWriter.write("\treturn " + transformerClass.name.toLowerCase() + "Repository.findAll();\n}\n");

                    myWriter.write("public Mono<" + transformerClass.name +"> findById(");
                    for (Attribute transformerClass2 : transformerClass.attributes) {
                      if(transformerClass2.IsIdentifier.equals("yes")){
                        myWriter.write(transformerClass2.Type + " " + transformerClass2.Name + ") {\n");
                        myWriter.write("\treturn " + transformerClass.name.toLowerCase() + "Repository.findByID(" + transformerClass2.Name +");\n}\n");
                      }
                    }
                    
                    myWriter.write("public Mono<" + transformerClass.name + "> save(" + transformerClass.name + " " + transformerClass.name.toLowerCase() + ") {\n");
                    myWriter.write("\treturn " + transformerClass.name.toLowerCase() + "Repository.save(" + transformerClass.name.toLowerCase()+");\n}\n");
/*  public Mono<Tutorial> save(Tutorial tutorial) {
 * return tutorialRepository.save(tutorial);
 * } 
 */


                    for (Operation transformerClass2 : transformerClass.operations) {
                      myWriter.write("\n" + transformerClass2.Visibility + " " + transformerClass2.ReturnType + " " + transformerClass2.Name + "(");
                      for (Parameter transformerClass3 : transformerClass2.Parameters) {
                        myWriter.write(transformerClass3.Type + " " + transformerClass3.Name);
                        if(transformerClass2.Parameters.indexOf(transformerClass3) != transformerClass2.Parameters.size()-1){
                         myWriter.write(", ");
                        }
                      }
                      myWriter.write("){}");
                    }
                /* for (Operation operation : operationService) {
                   myWriter.write("\n" + operation.Visibility + " " + operation.ReturnType + " " + operation.Name + "(");
                   for (Parameter param : operation.Parameters) {
                     myWriter.write(param.Type + " " + param.Name);
                     if(operation.Parameters.indexOf(param) != operation.Parameters.size()-1){
                      myWriter.write(", ");
                     }
                    }
                    myWriter.write(");");
                } */
              }
            }
                myWriter.write("\n\n}\n");
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}

