package Generator;

import java.util.List;

import Model.TransformerClass;

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
