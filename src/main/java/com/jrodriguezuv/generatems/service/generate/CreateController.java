package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.Operation;
import com.jrodriguezuv.generatems.service.model.TransformerClass;

public class CreateController {
    private final static String nameRepository = "Controller";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR;

  
    public void invokeController(String classId, String className, String classStereotype, String classVisibility,
        String classAbstract, String classFather, List<Attribute> attributes, List<Operation> operations) {
          createFile(className);
          writeFile(className,attributes);
    } 


    public void createFile(String className) {
        // TODO Auto-generated method stub
        try {  
            
            File myObj = new File( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\controller\\"+className+nameRepository +".java");  
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
        System.out.println("controller");
        try {
          
            try (FileWriter myWriter = new FileWriter( "MS\\" + createStructureSpringBoot.nameDir + "\\src\\main\\java\\com\\demo\\spring\\jpa\\msGenerate\\controller\\"+className+nameRepository +".java")) {

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

                //GetAll
                myWriter.write("@GetMapping(\"/" + className.toLowerCase() + "s\")\n");
                myWriter.write("public ResponseEntity<List<" + className + ">> getAll" + className + "() {\n");
                myWriter.write("List<" + className + "> " + className.toLowerCase() + " = " + "Service.getAll" + className + "s();\n" );
                myWriter.write("\treturn new ResponseEntity<>(" + className.toLowerCase() + ", HttpStatus.OK);\n}\n");
                
                //Post
                myWriter.write("@PostMapping(\"/" + className.toLowerCase() + "s\")\n");
                myWriter.write("public ResponseEntity<" + className + "> create" + className + "(@RequestBody " + className + " " + className.toLowerCase() + ") {\n");
                myWriter.write(className + " created" + className + " = " +"Service.create" + className + "(" + className.toLowerCase() + ");\n");
                myWriter.write("\treturn new ResponseEntity<>(created" + className + ", HttpStatus.CREATED);\n}\n\n");

              
                /*   @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
      Tutorial createdTutorial = tutorialService.createTutorial(tutorial);
      return new ResponseEntity<>(createdTutorial, HttpStatus.CREATED);
  }
    }*/
                
                /* for (TransformerClass transformerClass : classesToTransform) {

                    if(transformerClass.stereotype.equals("Entity")){ */
                       /*  myWriter.write(transformerClass.name + "Service " + transformerClass.name.toLowerCase() +"Service;\n" ); */
                      /*  myWriter.write("public class "+ transformerClass.name + "Controller {\n\n");
                       myWriter.write("@Autowired\n");
                       myWriter.write(transformerClass.name + "Service " + transformerClass.name.toLowerCase() + "Service;\n\n"); */
                        //Endpoint GetAll
                     /*   myWriter.write("@GetMapping(\"/" + transformerClass.name.toLowerCase() + "s\")\n" );
                       myWriter.write("public ResponseEntity<List<" + transformerClass.name + ">> getAll" + transformerClass.name + "s(@RequestParam(required = false)");
                       for (Attribute transformerClass2 : transformerClass.attributes) {
                          if (!transformerClass2.Name.contains("id") && !transformerClass2.Name.contains("ID")){
                            
                            if(transformerClass2.Type.equals("string") || transformerClass2.Type.equals("String") ){
                              myWriter.write(transformerClass2.Type.substring(0, 1).toUpperCase() + transformerClass2.Type.substring( 1).toLowerCase()+ " " + transformerClass2.Name + ") {\n\n");
                            }else{
                              myWriter.write(transformerClass2.Type.toLowerCase()+ " " + transformerClass2.Name + ") {\n\n");
                            }
                            
                            myWriter.write("List<" + transformerClass.name + "> " + transformerClass.name.toLowerCase() + "s = " + transformerClass.name.toLowerCase() + "Service.getAll" + transformerClass.name + "s(" + transformerClass2.Name + ");\n\n");
                            myWriter.write("if (" + transformerClass.name.toLowerCase() + "s.isEmpty()) {\n");
                            myWriter.write("\treturn new ResponseEntity<>(HttpStatus.NO_CONTENT);\n}\n\n");
                            myWriter.write("return new ResponseEntity<>(" + transformerClass.name.toLowerCase() + "s, HttpStatus.OK);\n}\n");
                            break;
                          }
                        } */
                        
                       
                      //Endpoint Getid
                      /* myWriter.write("@GetMapping(\"/{id}\")\n");
                      myWriter.write("public ResponseEntity<" + transformerClass.name + "> get" + transformerClass.name + "ById(@PathVariable(\"");
                      for (Attribute transformerClass2 : transformerClass.attributes) {
                        if (transformerClass2.Name.contains("id") || transformerClass2.Name.contains("ID") ){
                          myWriter.write(transformerClass2.Name + "\") " + transformerClass2.Type + " " + transformerClass2.Name + ") {\n");
                          myWriter.write(transformerClass.name + " " + transformerClass.name.toLowerCase() + " = " + transformerClass.name.toLowerCase() + "Service.get" + transformerClass.name + "ById(" + transformerClass2.Name + ");\n");
                          myWriter.write("return new ResponseEntity<>(" + transformerClass.name.toLowerCase() + ", HttpStatus.OK);\n}\n\n");
                          break;
                        }
                      }
 */
                      //EndPoint Post
                      /* myWriter.write("@PostMapping(\"/" + transformerClass.name.toLowerCase() + "s\")\n");
                      myWriter.write("public ResponseEntity<" + transformerClass.name + "> create" + transformerClass.name + "(@RequestBody " + transformerClass.name + " " + transformerClass.name.toLowerCase() + ") {\n");
                      myWriter.write(transformerClass.name + " created" + transformerClass.name + " = " + transformerClass.name.toLowerCase() + "Service.create" + transformerClass.name + "(" + transformerClass.name.toLowerCase() + ");\n");
                      myWriter.write("return new ResponseEntity<>(created" + transformerClass.name + ", HttpStatus.CREATED);\n}\n");

                    } 
                    if(transformerClass.stereotype.equals("Aggregate Root")){ */
                      /*  myWriter.write(transformerClass.name + "Service " + transformerClass.name.toLowerCase() +"Service;\n" ); */
                     /*  myWriter.write("public class "+ transformerClass.name + "Controller {\n\n");
                      myWriter.write("@Autowired\n");
                      myWriter.write(transformerClass.name + "Service " + transformerClass.name.toLowerCase() + "Service;\n\n"); */
                       //Endpoint GetAll
          
                     /*  myWriter.write("@GetMapping(\"/" + transformerClass.name.toLowerCase() + "s\")\n" );
                      myWriter.write("public ResponseEntity<List<" + transformerClass.name + ">> getAll" + transformerClass.name + "s(){\n");
                      myWriter.write("List<" + transformerClass.name + "> " + transformerClass.name.toLowerCase() + "Service.getAll" + transformerClass.name + "s();\n}\n\n"); */
                       
                      
                     //Endpoint Getid
              
                  /*    myWriter.write("@GetMapping({\"/{id}/"+ transformerClass.name +"\"})\n");
                     myWriter.write("public ResponseEntity<" + transformerClass.name + "> get" + transformerClass.name + "ById(@PathVariable(\"");
                     for (Attribute transformerClass2 : transformerClass.attributes) {
                       if (transformerClass2.Name.contains("id") || transformerClass2.Name.contains("ID") ){
                         myWriter.write(transformerClass2.Name + "\") " + transformerClass2.Type + " " + transformerClass2.Name + ") {\n");
                         myWriter.write(transformerClass.name + " " + transformerClass.name.toLowerCase() + " = " + transformerClass.name.toLowerCase() + "Service.get" + transformerClass.name + "ById(" + transformerClass2.Name + ");\n");
                         myWriter.write("return new ResponseEntity<>(" + transformerClass.name.toLowerCase() + ", HttpStatus.OK);\n}\n\n");
                         break;
                       }
                     } */

                     //EndPoint Post

                 /*     myWriter.write("@PostMapping(\"/{id}/" + transformerClass.name.toLowerCase() + "s\")\n");
                     myWriter.write("public ResponseEntity<" + transformerClass.name + "> create" + transformerClass.name + "(@PathVariable(value = \"id\") Long ident, @RequestBody " + transformerClass.name + " " + transformerClass.name.toLowerCase() + "Request) {\n");
                     myWriter.write(transformerClass.name + " created" + transformerClass.name + " = " + transformerClass.name.toLowerCase() + "Service.create" + transformerClass.name + "(ident," + transformerClass.name.toLowerCase() + "Request);\n");
                     myWriter.write("return new ResponseEntity<>(created" + transformerClass.name + ", HttpStatus.CREATED);\n}\n");

                   } 


                } */
              
        

    
                myWriter.write("\n}\n");
              
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        }

    
       
        
        

}



