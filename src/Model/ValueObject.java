package Model;

import java.util.List;

public class ValueObject extends TransformerClass {

    public ValueObject(String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        super(name, stereotype, visibility, attributes, operations);
        //TODO Auto-generated constructor stub
    }

    public void test(){
        System.out.println(Name);
    }
    

}
