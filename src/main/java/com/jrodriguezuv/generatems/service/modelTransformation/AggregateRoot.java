package com.jrodriguezuv.generatems.service.modelTransformation;

import java.util.List;

public class AggregateRoot extends TransformerClass {

    public AggregateRoot(String pid, String pname, String pstereotype, String pvisibility, String pabstract, String pfather, List<Attribute> pattributes,
            List<Operation> poperations) {
        super(pid, pname, pstereotype, pvisibility, pabstract, pfather, pattributes, poperations);
        this.transformationStrategy = new AggregateRootTransformer();
       
    }

    public AggregateRoot() {
        this.transformationStrategy = new AggregateRootTransformer();
    }
    
}
