package Model;

import java.util.List;

import Generator.Validator;


public class Entity extends TransformerClass {

    
    
   private TransformationStrategy transformationStrategy;

    public Entity() {
    }

    public Entity(String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        super(name, stereotype, visibility, attributes, operations);
        //TODO Auto-generated constructor stub
    }

    public Entity(TransformationStrategy transformationStrategy){
        this.transformationStrategy = transformationStrategy;
    }

    
    public void invokeTransformation(String name, String stereotype, String visibility, List<Attribute> attributes,
    List<Operation> operations){

        transformationStrategy.Create(name);
        transformationStrategy.Write(name, stereotype, visibility, attributes);
    }

   

    
    

    
}
