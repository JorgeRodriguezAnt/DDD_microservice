package com.jrodriguezuv.generatems.service.modelTransformation;

import java.util.List;


public class Operation {
    
  public String Name;
  public String Visibility;
  public String ReturnType;
  public List<Parameter> Parameters;

  

  public Operation(String name, String visibility, String returnType, List<Parameter> parameters) {
    Name = name;
    Visibility = visibility;
    ReturnType = returnType;
    Parameters = parameters;
  }

  public String getName() {
    return Name;
  } 

  public void setName(String name) {
    Name = name;
  }

  public String getVisibility() {
    return Visibility;
  }

  public void setVisibility(String visibility) {
    Visibility = visibility;
  }

  public String getReturnType() {
    return ReturnType;
  }

  public void setReturnType(String returnType) {
    ReturnType = returnType;
  }

  public List<Parameter> getParameters() {
    return Parameters;
  }

  @Override
  public String toString() {
    return "Operation [Name=" + Name + ", Visibility=" + Visibility + ", ReturnType=" + ReturnType + ", Parameters="
            + Parameters + "]";
  }

  



  
}
