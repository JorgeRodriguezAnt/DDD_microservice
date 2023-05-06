package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateStructureSpringBoot {
    
    String view_name;
    public static String dString;
    public void createDirectories(String name, String json){
        JSONParser parser = new JSONParser();
      
      
    try {
        
      
   
        //Read Json
        /* Object obj = parser.parse(json); */
        Object object = parser.parse(json);
        
        // Validation: is a JSOn object and containds classess
         /* JSONObject jsonObject = (JSONObject)obj;  */
        //Not a Valid JSON object

        /* JSONArray jsonarreglo = (JSONArray) jsonObject.get("view_name");  */
        /* System.out.println(jsonarreglo); */

       
        JSONArray jsonArray2= (JSONArray) object;
        
        for (int z = 0; z < jsonArray2.size(); z++) {
          /* System.out.println(test.get(i)); */
          JSONArray viewArray = (JSONArray) jsonArray2;
          /* System.out.println(testArray.get(i)); */
          JSONObject jsonObject = (JSONObject) viewArray.get(z);
          view_name = (String) jsonObject.get("view_name");
        }
      } catch (Exception e) {
        // TODO: handle exception
      }

      /* String view = view_name; */
      //Create microservice directory 
      File theDirectory = new File("MS\\"+name+"\\" + view_name + "\\src\\main\\java\\com\\demo\\" + view_name );
      if (theDirectory.exists() || theDirectory.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File theDirectory1 = new File("MS\\"+name+"\\" + view_name + "\\src\\main\\java\\com\\demo\\" + view_name + "\\controller"  );
      if (theDirectory1.exists() || theDirectory1.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File  theDirectory2 = new File("MS\\"+name+"\\" + view_name + "\\src\\main\\java\\com\\demo\\" + view_name + "\\model"  );
      if (theDirectory2.exists() || theDirectory2.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

     dString = "MS\\"+name+"\\" + view_name + "\\src\\main\\java\\com\\demo\\" + view_name + "\\model";
      

      File theDirectory3 = new File("MS\\"+name+"\\" + view_name + "\\src\\main\\java\\com\\demo\\" + view_name + "\\repository"  );
      if (theDirectory3.exists() || theDirectory3.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File theDirectory4 = new File("MS\\"+name+"\\" + view_name + "\\src\\main\\java\\com\\demo\\" + view_name + "\\service"  );
      if (theDirectory4.exists() || theDirectory4.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File theDirectory5 = new File("MS\\"+name+"\\" + view_name + "\\src\\main\\resource"  );
      if (theDirectory5.exists() || theDirectory5.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }
    
      //Create ".mvn" directory with their files
      File srcDir = new File(".mvn");
      File destDir = new File("MS\\" +name+"\\" + view_name +"\\.mvn");
      try {
      FileUtils.copyDirectory(srcDir, destDir);
      } catch (IOException e) {
      e.printStackTrace();
      }

      //Create spring files
       File srcDir1 = new File("pom.xml");
       File destDir1 = new File("MS\\" + name+"\\" + view_name + "\\pom.xml");
       File srcDir2 = new File("mvnw.cmd");
       File destDir2 = new File("MS\\" + name+"\\" + view_name + "\\mvnw.cmd");
       File srcDir3 = new File("mvnw");
       File destDir3 = new File("MS\\" + name+"\\" + view_name + "\\mvnw");
       try {
         Files.copy(srcDir1.toPath(), destDir1.toPath()); 
        
      } catch (IOException e) {
      e.printStackTrace();
      }    

      try {
        Files.copy(srcDir2.toPath(), destDir2.toPath());
        } catch (IOException e) {
        e.printStackTrace();
        }  
     
     try {
      Files.copy(srcDir3.toPath(), destDir3.toPath()); 
      } catch (IOException e) {
      e.printStackTrace();
      }
   
    }
 

}
