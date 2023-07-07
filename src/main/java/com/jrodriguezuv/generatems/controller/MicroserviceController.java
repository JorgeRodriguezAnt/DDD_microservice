package com.jrodriguezuv.generatems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrodriguezuv.generatems.model.Microservice;
/* import com.jrodriguezuv.generatems.model.Tutorial; */
import com.jrodriguezuv.generatems.service.msService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MicroserviceController {
  @Autowired
  msService microserviceService;

  
  @GetMapping("/msGenerates")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Microservice> getAllMicroservices(@RequestParam(required = false) String name) {
    if (name == null)
      return microserviceService.findAll();
    else
      return microserviceService.findAll();
  }

  @GetMapping("/msGenerates/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Microservice> getMicroserviceById(@PathVariable("id") int id) {
    return microserviceService.findById(id);
  }

  @PostMapping("/msGenerates")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Microservice> createMicroservice(@RequestBody Microservice microservice) {
    /* return microserviceService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription())); */
    return microserviceService.save(new Microservice(microservice.getName(), microservice.getJson()));
  }


  @DeleteMapping("/msGenerates/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteMicroservice(@PathVariable("id") int id) {
    return microserviceService.deleteById(id);
  }



 
}