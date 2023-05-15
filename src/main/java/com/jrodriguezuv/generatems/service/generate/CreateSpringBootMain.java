package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.TransformerClass;

public class CreateSpringBootMain {
    private final static String nameRepository = "Controller";
    CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    String nameClassAR;
    public void createFile(List<TransformerClass> classesToTransform) {
        // TODO Auto-generated method stub
        try {  
            
            File myObj = new File( createStructureSpringBoot.dString+"\\SpringBootMain.java");  
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
            try (FileWriter myWriter = new FileWriter( createStructureSpringBoot.dString+"\\SpringBootMain.java")) {

                //Package
                myWriter.write("package com.demo." + createStructureSpringBoot.view_name + ";\n\n\n");


                //import

                myWriter.write("import org.springframework.boot.SpringApplication;\n");
                myWriter.write("import org.springframework.boot.autoconfigure.SpringBootApplication;\n");
                myWriter.write("import org.springframework.context.annotation.Bean;\n");
                myWriter.write("import org.springframework.core.io.ClassPathResource;\n");
                myWriter.write("import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;\n");
                myWriter.write("import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;\n");
                myWriter.write("import org.springframework.web.reactive.config.EnableWebFlux;\n");
                myWriter.write("import io.r2dbc.spi.ConnectionFactory;\n\n");



                myWriter.write("@EnableWebFlux\n");
                myWriter.write("@SpringBootApplication\n");
                myWriter.write("public class SpringBootMain {\n\n");
                myWriter.write("@Bean\n");
                myWriter.write("ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {\n\n");
                myWriter.write("ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();\n");
                myWriter.write("initializer.setConnectionFactory(connectionFactory);\n");
                myWriter.write("initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource(\"schema.sql\")));\n\n");
                myWriter.write("return initializer;\n}\n\n");
                myWriter.write("public static void main(String[] args) {\n");
                myWriter.write("SpringApplication.run(SpringBootMain.class, args);\n}\n}\n");

              
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}



