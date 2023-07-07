package com.jrodriguezuv.generatems.service.generate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateStructureSpringBoot {
    
    public static String view_name;
    public static String dString;
    public static String resource;
    public static String test;
    public static String nameDir;
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
      /* File theDirectory0 = new File("MS" );
    
      if (theDirectory0.exists() || theDirectory0.mkdirs()){
        System.out.println("The folder has been created or already exists");
      } */

      String repoUrl = "https://github.com/jorgeRodriguezAntiquera/MSDemo.git";
    String cloneDirectoryPath = "MS/"+name; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
    try {
        System.out.println("Cloning "+repoUrl+" into "+repoUrl);
        Git.cloneRepository()
            .setURI(repoUrl)
            .setDirectory(Paths.get(cloneDirectoryPath).toFile())
            .call();
        System.out.println("Completed Cloning");
    } catch (GitAPIException e) {
        System.out.println("Exception occurred while cloning repo");
        e.printStackTrace();
    }

    nameDir = name;
    File theDirectory1 = new File("MS/"+name+ "/src/main/java/com/demo/spring/jpa/msGenerate/controller/" );
    
      if (theDirectory1.exists() || theDirectory1.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File  theDirectory2 = new File("MS/"+name+ "/src/main/java/com/demo/spring/jpa/msGenerate/model"  );
      if (theDirectory2.exists() || theDirectory2.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File theDirectory3 = new File("MS/"+name+ "/src/main/java/com/demo/spring/jpa/msGenerate/repository"  );
      if (theDirectory3.exists() || theDirectory3.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      File theDirectory4 = new File("MS/"+name+ "/src/main/java/com/demo/spring/jpa/msGenerate/service"  );
      if (theDirectory4.exists() || theDirectory4.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

    }
 

}
