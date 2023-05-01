package com.jrodriguezuv.generatems.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrodriguezuv.generatems.model.Tutorial;
import com.jrodriguezuv.generatems.repository.TutorialRepository;
import com.jrodriguezuv.generatems.service.generate.Transformer;
import com.jrodriguezuv.generatems.service.generate.Validator;
import com.jrodriguezuv.generatems.service.model.TransformerClass;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

  @Autowired
  TutorialRepository tutorialRepository;

  public Flux<Tutorial> findAll() {
    

    return tutorialRepository.findAll();
  }

  public Mono<Tutorial> findById(int id) {
    return tutorialRepository.findById(id);
  }

  public Mono<Tutorial> save(Tutorial tutorial) {

      //Create microservice directory 
      File theDirectory = new File("MS\\"+tutorial.getTitle());
      if (theDirectory.exists() || theDirectory.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }

      //Create ".mvn" directory with their files
      File srcDir = new File(".mvn");
      File destDir = new File("MS\\" +tutorial.getTitle() +"\\.mvn");
      try {
      FileUtils.copyDirectory(srcDir, destDir);
      } catch (IOException e) {
      e.printStackTrace();
      }

      //Create spring files
       File srcDir1 = new File("pom.xml");
       File destDir1 = new File("MS\\" + tutorial.getTitle() + "\\pom.xml");
       File srcDir2 = new File("mvnw.cmd");
       File destDir2 = new File("MS\\" + tutorial.getTitle() + "\\mvnw.cmd");
       File srcDir3 = new File("mvnw");
       File destDir3 = new File("MS\\" + tutorial.getTitle() + "\\mvnw");
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
      
      

      String jsonEntrada = tutorial.getDescription();
      /* Validator validator = new Validator();
      validator.validateJSON( json); */

      Validator json = new Validator();
        
        List<TransformerClass> classestoTransform = json.validateJSON(jsonEntrada);
        
        Transformer myTransfomer = new Transformer(classestoTransform);
        
        myTransfomer.transform();

    
    return tutorialRepository.save(tutorial);
  } 

  public Mono<Void> deleteById(int id) {
    return tutorialRepository.deleteById(id);
  }


  
}
