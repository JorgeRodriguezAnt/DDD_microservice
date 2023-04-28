package com.jrodriguezuv.generatems.service;

import org.springframework.web.multipart.MultipartFile;

import com.jrodriguezuv.generatems.model.FileDB;

import java.io.IOException;

import java.util.stream.Stream;


public interface FilesStorageService {

  public FileDB store(MultipartFile file) throws IOException;

  public FileDB getFile(String id);

  public Stream<FileDB> getAllFiles();

  

}
