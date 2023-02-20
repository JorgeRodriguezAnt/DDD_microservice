package model;

import java.util.List;

public class ValueObject extends TransformerClass {

    public ValueObject() {
        this.transformationStrategy = new ValueObjectTransformer();
    }

    public ValueObject(String id, String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        super(id, name, stereotype, visibility, attributes, operations);
        //TODO Auto-generated constructor stub

        this.transformationStrategy = new ValueObjectTransformer();
            }
}
