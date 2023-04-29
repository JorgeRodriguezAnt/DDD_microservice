package com.jrodriguezuv.generatems.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrodriguezuv.generatems.model.Tutorial;
import com.jrodriguezuv.generatems.repository.TutorialRepository;

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

    
      File theDirectory = new File("MS\\"+tutorial.getTitle());
      if (theDirectory.exists() || theDirectory.mkdirs()){
        System.out.println("The folder has been created or already exists");
      }
    copy();

    /* try {
      File myObj = new File("MS\\" + tutorial.getTitle() +".java");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }*/
    return tutorialRepository.save(tutorial);
  } 

  public Mono<Void> deleteById(int id) {
    return tutorialRepository.deleteById(id);
  }

  public static void copyDir(Path src, Path dest) throws IOException {
    Files.walk(src)
            .forEach(source -> {
                try {
                    Files.copy(source, dest.resolve(src.relativize(source)),
                                    StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
}

public static void copy()
{
    File from = new File(".mvn");
    File to = new File("MS\\HOLA");

    try {
        copyDir(from.toPath(), to.toPath());
        System.out.println("Copied whole directory successfully.");
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
}

  
}
