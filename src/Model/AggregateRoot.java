package model;

import java.util.List;

public class AggregateRoot extends TransformerClass {

    public AggregateRoot(String pid, String pname, String pstereotype, String pvisibility, List<Attribute> pattributes,
            List<Operation> poperations) {
        super(pid, pname, pstereotype, pvisibility, pattributes, poperations);
        this.transformationStrategy = new AggregateRootTransformer();
       
    }

    public AggregateRoot() {
        this.transformationStrategy = new AggregateRootTransformer();
    }
    
}
