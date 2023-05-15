package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.TransformerClass;

public class CreateController {
    private final static String nameRepository = "Controller";
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
            File myObj = new File( createStructureSpringBoot.dString+"\\controller\\"+nameClassAR+nameRepository +".java");  
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
            try (FileWriter myWriter = new FileWriter( createStructureSpringBoot.dString+"\\controller\\"+nameClassAR+nameRepository +".java")) {

                //Package
                myWriter.write("package com.demo." + createStructureSpringBoot.view_name + ".controller;\n\n\n");


                //import

                myWriter.write("import org.springframework.beans.factory.annotation.Autowired;\n");
                myWriter.write("import org.springframework.web.bind.annotation.GetMapping;\n");
                myWriter.write("import org.springframework.web.bind.annotation.PostMapping;\n");
                myWriter.write("import org.springframework.web.bind.annotation.ResponseStatus;\n");
                myWriter.write("import org.springframework.http.HttpStatus;\n");
                myWriter.write("import reactor.core.publisher.Flux;\n");
                myWriter.write("import reactor.core.publisher.Mono;\n");
                myWriter.write("import org.springframework.web.bind.annotation.RequestBody;\n");
                myWriter.write("import org.springframework.web.bind.annotation.RequestMapping;\n");
                myWriter.write("import org.springframework.web.bind.annotation.RequestParam;\n");
                myWriter.write("import org.springframework.web.bind.annotation.RestController;\n");
                myWriter.write("import org.springframework.web.bind.annotation.PathVariable;\n");

                //import model and service
                for (TransformerClass transformerClass : classesToTransform) {
                  if(transformerClass.stereotype.equals("Aggregate Root")){
                    myWriter.write("import com.demo." + createStructureSpringBoot.view_name + ".model." + transformerClass.name+";\n\n");
                  }
                }

                
                myWriter.write("@RestController\n");
                myWriter.write("@RequestMapping(\"/api\")\n");
                myWriter.write("public class Controller{\n");
                myWriter.write("@Autowired\n");
                for (TransformerClass transformerClass : classesToTransform) {
                    if(transformerClass.stereotype.equals("Aggregate Root")){
                        myWriter.write(transformerClass.name + " " + transformerClass.name.toLowerCase() +"Service;\n" );
                    }
                }

                for (TransformerClass transformerClass : classesToTransform) {
                    if(transformerClass.stereotype.equals("Aggregate Root")){
                        myWriter.write("\t\n@GetMapping(\"/"+transformerClass.name+"\")\n" );
                        myWriter.write("\t@ResponseStatus(HttpStatus.OK)\n");
                        myWriter.write("\tpublic Flux<"+ transformerClass.name +"> getAll" + transformerClass.name +"s(@RequestParam(required = false)");
                        for (Attribute transformerClass2 : transformerClass.attributes) {
                          if(!transformerClass2.Name.contains("ID") || !transformerClass2.Name.contains("Id") || !transformerClass2.Name.contains("id") || !transformerClass2.Name.contains("iD")  ){
                            myWriter.write(" String " + transformerClass2.Name + ") {\n");
                          }
                          break;
                        }
                        myWriter.write("\t\treturn " + transformerClass.name.toLowerCase() +"service.findAll;\n}");

                        myWriter.write("\t\n@GetMapping(\""+transformerClass.name+"/{id}\")\n");
                        myWriter.write("\t@ResponseStatus(HttpStatus.OK)\n");
                        myWriter.write("\tpublic Mono<" + transformerClass.name + "> get"+ transformerClass.name + "ById(@PathVariable(\"id\") int id) {\n"  );
                        myWriter.write("\t\treturn " + transformerClass.name.toLowerCase() +"service.findById(id);\n}");

                        myWriter.write("\t\n@PostMapping(\""+transformerClass.name+"\")\n");
                        myWriter.write("\t@ResponseStatus(HttpStatus.CREATED)\n");
                        myWriter.write("\tpublic Mono<" + transformerClass.name + "> create"+ transformerClass.name + "(@RequestBody " + transformerClass.name + " "+ transformerClass.name.toLowerCase() + ") {\n" );
                        myWriter.write("\t\treturn " + transformerClass.name.toLowerCase() +"service.save(new " + transformerClass.name + "(");
                        for(Attribute att : transformerClass.attributes){
                          if(!att.Name.contains("ID") || !att.Name.contains("Id") || !att.Name.contains("id") || !att.Name.contains("iD")  ){
                            myWriter.write(" "+transformerClass.name.toLowerCase() + ".get" + att.Name + "()");
                            if(transformerClass.attributes.indexOf(att) != transformerClass.attributes.size()-1){
                              myWriter.write(", ");
                            }
                          }
                        }
                        myWriter.write("));\n}\n");
                    }
                }


                /*  for (Parameter param : operation.Parameters) {
                        myWriter.write(param.Type + " " + param.Name);
                        if(operation.Parameters.indexOf(param) != operation.Parameters.size()-1){
                          myWriter.write(", ");
                        }
                      } */
                myWriter.write("\n}\n");
              
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}



