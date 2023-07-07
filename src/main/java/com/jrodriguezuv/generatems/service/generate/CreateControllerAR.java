package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.modelTransformation.Attribute;
import com.jrodriguezuv.generatems.service.modelTransformation.Operation;
import com.jrodriguezuv.generatems.service.modelTransformation.TransformerClass;


public class CreateControllerAR {
    
    private final static String nameRepository = "Controller";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR;
    String className;

  
    public void invokeController(List<TransformerClass> classesToTransform) {
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
            
            File myObj = new File(  "MS/"+createStructureSpringBoot.nameDir+ "/src/main/java/com/demo/spring/jpa/msGenerate/controller/"+className+nameRepository +".java");  
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
        System.out.println("controller");
        try {
            for (TransformerClass transformerClass : classesToTransform) {
                if(transformerClass.stereotype.equals("Aggregate Root"))
                className = transformerClass.name;
              }
          
            try (FileWriter myWriter = new FileWriter( "MS/"+createStructureSpringBoot.nameDir+ "/src/main/java/com/demo/spring/jpa/msGenerate/controller/"+className+nameRepository +".java")) {

                //Package
                myWriter.write("package com.demo.spring.jpa.msGenerate.controller;\n\n");

                //import
                myWriter.write("import java.util.List;\n\n");

                myWriter.write("import org.springframework.beans.factory.annotation.Autowired;\n");
                myWriter.write("import org.springframework.http.HttpStatus;\n");
                myWriter.write("import org.springframework.http.ResponseEntity;\n");
                myWriter.write("import org.springframework.web.bind.annotation.*;\n\n");
                
                myWriter.write("import com.demo.spring.jpa.msGenerate.model.*;\n");
                myWriter.write("import com.demo.spring.jpa.msGenerate.repository.*;\n");
                myWriter.write("import com.demo.spring.jpa.msGenerate.service.*;\n\n");
  
                myWriter.write("@CrossOrigin(origins = \"*\")\n");
                myWriter.write("@RestController\n");
                myWriter.write("@RequestMapping(\"/api\")\n");
                myWriter.write("public class " + className + "Controller {\n\n");
                myWriter.write("@Autowired\n");
                myWriter.write(className + "Service "  + "Service;\n\n");



                //GetAll Entity
                for (TransformerClass transformerClass : classesToTransform) {
                  if(transformerClass.stereotype.equals("Entity")){
                    myWriter.write("//GetAll Entity" + transformerClass.name + "\n");
                    myWriter.write("@GetMapping(\"/"+ transformerClass.name.toLowerCase() + "s\")\n");
                    myWriter.write("public ResponseEntity<List<" + transformerClass.name + ">> getAll" + transformerClass.name + "() {\n");
                    myWriter.write("List<" + transformerClass.name + "> " + transformerClass.name.toLowerCase() + " = Service.getAll" + transformerClass.name + "s();\n");
                    myWriter.write("return new ResponseEntity<>(" + transformerClass.name.toLowerCase() + ", HttpStatus.OK);\n}\n\n");
                  }
                }

                //Post Entity
                for (TransformerClass transformerClass : classesToTransform) {
                  if(transformerClass.stereotype.equals("Entity")){
                    myWriter.write("//Post Entity" + transformerClass.name + "\n");
                    myWriter.write("@PostMapping(\"/"+ transformerClass.name.toLowerCase() + "s\")\n");
                    myWriter.write("public ResponseEntity<" + transformerClass.name + "> create" + transformerClass.name + "(@RequestBody " + transformerClass.name + " " + transformerClass.name.toLowerCase() + ") {\n");

                    myWriter.write( transformerClass.name + " created" + transformerClass.name.toLowerCase() + " = Service.create" + transformerClass.name + "(" + transformerClass.name.toLowerCase() + ");\n");
                    myWriter.write("return new ResponseEntity<>(created" + transformerClass.name.toLowerCase() + ", HttpStatus.CREATED);\n}\n\n");
                  }
                }
        


                //GetAll

                myWriter.write("@GetMapping(\"/" + className.toLowerCase() + "s\")\n");
                myWriter.write("public ResponseEntity<List<" + className + ">> getAll" + className + "() {\n");
                myWriter.write("List<" + className + "> " + className.toLowerCase() + " = " + "Service.getAll" + className + "s();\n" );
                myWriter.write("\treturn new ResponseEntity<>(" + className.toLowerCase() + ", HttpStatus.OK);\n}\n");
                
                //Post
                /* @PostMapping("/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
            @RequestBody TutorialDetails detailsRequest) {
        TutorialDetails createdDetails = tutorialDetailsService.createDetails(tutorialId, detailsRequest);
        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
    } */
            
                myWriter.write("@PostMapping(\"/{Id}/" + className.toLowerCase() + "s\")\n");
                myWriter.write("public ResponseEntity<" + className + "> create" + className + "(@PathVariable(value = \"Id\") Long Id, @RequestBody " + className + " " + className.toLowerCase() + ") {\n");
                myWriter.write(className + " created" + className + " = "  + "Service.create" + className + "(Id," + className.toLowerCase() + ");\n");
                myWriter.write("\treturn new ResponseEntity<>(created" + className + ", HttpStatus.CREATED);\n}\n\n");

              
               
              
        

    
                myWriter.write("\n}\n");
              
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        }


    

    
       
        
        

}



