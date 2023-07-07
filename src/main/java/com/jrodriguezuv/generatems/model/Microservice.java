package com.jrodriguezuv.generatems.model;

import org.springframework.data.annotation.Id;

public class Microservice {
    
  @Id
  private int id;

  private String name;

  private String json;

  
public Microservice() {
}


public Microservice(String name, String json) {
    this.name = name;
    this.json = json;
}


public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getJson() {
    return json;
}

public void setJson(String json) {
    this.json = json;
}


@Override
public String toString() {
    return "Microservice [id=" + id + ", name=" + name + ", json=" + json + "]";
}

  
}
