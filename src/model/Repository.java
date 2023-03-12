package model;

import java.util.List;

public class Repository extends TransformerClass {

    public Repository(String pid, String pname, String pstereotype, String pvisibility, String pabstract, String pfather, List<Attribute> pattributes,
            List<Operation> poperations) {
        super(pid, pname, pstereotype, pvisibility, pabstract, pfather, pattributes, poperations);
        this.transformationStrategy = new RepositoryTransformer();
       
    }

    public Repository() {
        this.transformationStrategy = new RepositoryTransformer();
    }
    
}
