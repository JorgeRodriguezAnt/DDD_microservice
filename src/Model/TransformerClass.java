package Model;


import java.util.List;


public abstract class TransformerClass {
    
    public String Name;
    public String Stereotype;
    public String Visibility;
    public List<Attribute> Attributes;
    public List<Operation> Operations;
    
    public TransformerClass() {
    }

    public TransformerClass(String name, String stereotype, String visibility, List<Attribute> attributes,
            List<Operation> operations) {
        Name = name;
        Stereotype = stereotype;
        Visibility = visibility;
        Attributes = attributes;
        Operations = operations;
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


    public List<Attribute> getAttributes() {
        return Attributes;
    }


    public List<Operation> getOperations() {
        return Operations;
    }

    public void setAttributes(List<Attribute> attributes) {
        Attributes = attributes;
    }


    public void setOperations(List<Operation> operations) {
        Operations = operations;
    }


    @Override
    public String toString() {
        return "TransformerClass [Name=" + Name + ", Stereotype=" + Stereotype + ", Visibility=" + Visibility
                + ", Attributes=" + Attributes + ", Operations=" + Operations + "]";
    }

    
}
