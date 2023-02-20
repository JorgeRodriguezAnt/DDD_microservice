package model;

import java.util.List;


public class Entity extends TransformerClass {
    
    public Entity() {
        this.transformationStrategy = new EntityTransformer();
    }

    public Entity(String id,String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        super(id,name, stereotype, visibility, attributes, operations);
        //TODO Auto-generated constructor stub

        this.transformationStrategy = new EntityTransformer();
    }

   

    
    

    
}
