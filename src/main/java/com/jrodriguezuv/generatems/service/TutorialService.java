package com.jrodriguezuv.generatems.service;

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
    return tutorialRepository.save(tutorial);
  }

  public Mono<Void> deleteById(int id) {
    return tutorialRepository.deleteById(id);
  }

  

  
}
