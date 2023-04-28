package com.jrodriguezuv.generatems.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.stream.Stream;



import org.springframework.util.StringUtils;

import com.jrodriguezuv.generatems.model.FileDB;
import com.jrodriguezuv.generatems.repository.FileDBRepository;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path path = Paths.get("uploads"); 
  
  @Autowired
  private FileDBRepository fileDBRepository;


  public FileDB store(MultipartFile file) throws IOException {
  String fileName = StringUtils.cleanPath(file.getOriginalFilename());
  FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
  Files.copy(file.getInputStream(), this.path.resolve(file.getOriginalFilename()));
 
  try {  
    
    File myObj = new File( path.toAbsolutePath()+ "\\test.java");  
    if (myObj.createNewFile()) {  
      System.out.println("File created: " + myObj.getName());  
      System.out.println("Absolute path: " + myObj.getAbsolutePath());  
    } else {  
      System.out.println("File already exists.");  
    }  
    
  } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();  
  } 

    return fileDBRepository.save(FileDB);
  }

  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }

/* 
  @Override
  public void init() {
    try {
      Files.createDirectories(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
      }

      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  } */
}