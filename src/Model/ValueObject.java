package Model;

import java.util.List;

public class ValueObject extends TransformerClass {


    private TransformationStrategy transformationStrategy;
    public ValueObject() {
    }

    public ValueObject(String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        super(name, stereotype, visibility, attributes, operations);
        //TODO Auto-generated constructor stub
    }

    public ValueObject(TransformationStrategy transformationStrategy){
        this.transformationStrategy = transformationStrategy;
    }

    
    public void invokeTransformation(String name, String stereotype, String visibility, List<Attribute> attributes,
    List<Operation> operations){

        transformationStrategy.Create(name);
        transformationStrategy.Write(name, stereotype, visibility, attributes);
    }
    

}
