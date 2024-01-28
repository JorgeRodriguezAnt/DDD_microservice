package com.jrodriguezuv.generatems.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrodriguezuv.generatems.model.Microservice;
/* import com.jrodriguezuv.generatems.model.Tutorial; */
import com.jrodriguezuv.generatems.repository.MicroserviceRepository;
/* import com.jrodriguezuv.generatems.repository.TutorialRepository; */
import com.jrodriguezuv.generatems.service.generate.CreateStructureSpringBoot;
import com.jrodriguezuv.generatems.service.generate.Transformer;
import com.jrodriguezuv.generatems.service.generate.Validator;
import com.jrodriguezuv.generatems.service.generate.pushRepository;
import com.jrodriguezuv.generatems.service.modelTransformation.TransformerClass;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class msService {
  String view_name;


  @Autowired
  MicroserviceRepository microserviceRepository;

  public Flux<Microservice> findAll() {
    

   
    return microserviceRepository.findAll();
  }

  public Mono<Microservice> findById(int id) {
    
    return microserviceRepository.findById(id);
  }

  public Mono<Microservice> save(Microservice microservice) {

      
    
      
      
      CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
      createStructureSpringBoot.createDirectories(  microservice.getName(), microservice.getJson());

      String jsonEntrada = microservice.getJson();
     

       Validator json = new Validator();
        
        List<TransformerClass> classestoTransform = json.validateJSON(jsonEntrada);

        
        Transformer myTransfomer = new Transformer(classestoTransform);


        
        myTransfomer.transform() ; 

        pushRepository pushRepository = new pushRepository(); 


        pushRepository.push(); 
        /* pushRepository.delete();  */
    

    return microserviceRepository.save(microservice);
  } 

  public Mono<Void> deleteById(int id) {
    return microserviceRepository.deleteById(id);
  }


  
}
