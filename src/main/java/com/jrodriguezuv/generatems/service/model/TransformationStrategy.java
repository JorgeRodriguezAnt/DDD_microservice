package com.jrodriguezuv.generatems.service.model;

import java.util.List;

public interface TransformationStrategy {
    //Create method for Create and Write files

    public void createFile(String Classname);
    public void writeFile(String className, String classStereotype, String classVisibility, String classAbstract, String classFather, List<Attribute>Attributes, List<Operation> Operations);
}