package Model;

import java.util.List;

public class ValueObject extends TransformerClass {

    public ValueObject() {
        this.transformationStrategy = new ValueObjectTransformer();
    }

    public ValueObject(String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        super(name, stereotype, visibility, attributes, operations);
        //TODO Auto-generated constructor stub

        this.transformationStrategy = new ValueObjectTransformer();
            }
}
