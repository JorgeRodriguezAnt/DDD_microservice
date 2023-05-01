package com.jrodriguezuv.generatems.service.model;

import java.util.List;


public class Entity extends TransformerClass {
    
    public Entity() {
        this.transformationStrategy = new EntityTransformer();
    }

    public Entity(String id,String name, String stereotype, String visibility, String pabstract, String pfather, List<Attribute> attributes,
            List<Operation> operations) {
        super(id,name, stereotype, visibility, pabstract, pfather, attributes, operations);
        //TODO Auto-generated constructor stub

        this.transformationStrategy = new EntityTransformer();
    }

   

    
    

    
}
