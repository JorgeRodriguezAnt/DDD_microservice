package com.jrodriguezuv.generatems.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jrodriguezuv.generatems.model.Microservice;

@Repository
public interface MicroserviceRepository extends ReactiveCrudRepository<Microservice, Integer>{
    
}
