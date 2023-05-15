package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.Operation;
import com.jrodriguezuv.generatems.service.model.Parameter;
import com.jrodriguezuv.generatems.service.model.TransformerClass;



public class CreateServiceImplementation {
    
    private static final String serviceClassName = "Service";
    private static final String serviceImplementClassName = "ServiceImplement";
    private final static String nameRepository = "Repository";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();

    public void invokeCreateImplementation(String nameService,List<TransformerClass> classesToTransform){

      createFile(nameService);
      writeFile(nameService, classesToTransform);
    }

    public void createFile(String nameService) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File( createStructureSpringBoot.dString+"\\service\\"+serviceImplementClassName + nameService+".java");  
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

    
    public void writeFile(String nameService,List<TransformerClass> classesToTransform) {
        
        try {
            
            try (FileWriter myWriter = new FileWriter(createStructureSpringBoot.dString+"\\service\\"+serviceImplementClassName+ nameService+ ".java")) {
                myWriter.write("public class " + serviceImplementClassName + nameService +" implements "+ serviceClassName + nameService +"{\n");
                
                myWriter.write("private " + nameRepository + " " + "repository;\n");

                //Method save generate
                myWriter.write("\npublic " + nameService + " save"+ nameService +"("+ nameService + " "+nameService + ") {\n");
                myWriter.write("return " + nameRepository + ".save(" + nameService + ");\n}");


                //Method delete generate
                myWriter.write("\n\npublic void " + "delete" + nameService + "ById(" );

                for (TransformerClass transformerClass : classesToTransform) {
                  if(nameService.equals(transformerClass.name)){
                    for (Attribute attClass : transformerClass.attributes){
                      if(attClass.Name.contains("id") || attClass.Name.contains("Id") || attClass.Name.contains("ID") || attClass.Name.contains("iD")){
                        myWriter.write(attClass.Type + " " + attClass.Name + "){\n");
                        myWriter.write(nameRepository + ".deleteById(" + attClass.Name + ");\n}\n");
                        break;
                      }
                    }

                  //Methods whitout logical
                    for (Operation operation: transformerClass.operations) {
                      myWriter.write("\n" + operation.Visibility + " " + operation.ReturnType + " " + operation.Name + "(");
                      for (Parameter param : operation.Parameters) {
                        myWriter.write(param.Type + " " + param.Name);
                        if(operation.Parameters.indexOf(param) != operation.Parameters.size()-1){
                          myWriter.write(", ");
                        }
                      }
                      myWriter.write("){}");
                    }
                  }
                  
                }

                myWriter.write("\n\n}\n");
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}
