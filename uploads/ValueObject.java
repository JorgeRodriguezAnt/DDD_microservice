package model;

import java.util.List;

public class ValueObject extends TransformerClass {

    public ValueObject() {
        this.transformationStrategy = new ValueObjectTransformer();
    }

    public ValueObject(String id, String name, String stereotype, String visibility, String pabstract, String pfather, List<Attribute> attributes,
            List<Operation> operations) {
        super(id, name, stereotype, visibility, pabstract, pfather, attributes, operations);
        //TODO Auto-generated constructor stub

        this.transformationStrategy = new ValueObjectTransformer();
            }
}
