package com.jrodriguezuv.generatems.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jrodriguezuv.generatems.model.Tutorial;

import reactor.core.publisher.Flux;



@Repository
public interface TutorialRepository extends ReactiveCrudRepository<Tutorial, Integer>{
  Flux<Tutorial> findByTitleContaining(String title);
  

}
