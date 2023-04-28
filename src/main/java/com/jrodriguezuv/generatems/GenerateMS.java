package com.jrodriguezuv.generatems;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrodriguezuv.generatems.service.FilesStorageService;

@SpringBootApplication
public class GenerateMS {

	public static void main(String[] args) {
		SpringApplication.run(GenerateMS.class, args);
	}

}
