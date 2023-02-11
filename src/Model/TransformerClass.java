package Model;

import java.util.Set;

public class TransformerClass {
    
    public String Name;
    public String Stereotype;
    public String Visibility;
    public Set<Attribute> Attributes;
    public Set<Operation> Operations;


    public void invokeTransformer(){

    }


    public String getName() {
        return Name;
    }


    public String getStereotype() {
        return Stereotype;
    }


    public String getVisibility() {
        return Visibility;
    }


    public Set<Attribute> getAttributes() {
        return Attributes;
    }


    public Set<Operation> getOperations() {
        return Operations;
    }


    @Override
    public String toString() {
        return "TransformerClass [Name=" + Name + ", Stereotype=" + Stereotype + ", Visibility=" + Visibility
                + ", Attributes=" + Attributes + ", Operations=" + Operations + "]";
    }

    
}
