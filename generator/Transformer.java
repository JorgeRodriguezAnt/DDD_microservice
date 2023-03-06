package generator;

import java.util.List;

import model.TransformerClass;

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
