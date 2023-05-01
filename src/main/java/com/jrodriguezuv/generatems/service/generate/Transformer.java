package com.jrodriguezuv.generatems.service.generate;

import java.util.List;

import com.jrodriguezuv.generatems.service.model.TransformerClass;

public class Transformer{
    
    private List<TransformerClass> classesToTransform;
    
    public Transformer(List<TransformerClass> pClasses){
        this.classesToTransform = pClasses;
    }
    
    public void transform(){
    
    for (TransformerClass classToTransform : this.classesToTransform) {
        
             classToTransform.invokeTransformation(); 
            
        }
    }
}
