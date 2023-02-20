package model;


import java.util.List;


public abstract class TransformerClass {
    
    protected TransformationStrategy transformationStrategy;

    public String id;
    public String name;
    public String stereotype;
    public String visibility;
    public List<Attribute> attributes;
    public List<Operation> operations;


    
    public TransformerClass() {
    }

    public TransformerClass(String pid,String pname, String pstereotype, String pvisibility, List<Attribute> pattributes,
            List<Operation> poperations) {
        id = pid;
        name = pname;
        stereotype = pstereotype;
        visibility = pvisibility;
        attributes = pattributes;
        operations = poperations;
    }


    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getStereotype() {
        return stereotype;
    }


    public String getVisibility() {
        return visibility;
    }


    public List<Attribute> getAttributes() {
        return attributes;
    }


    public List<Operation> getOperations() {
        return operations;
    }

    public void setAttributes(List<Attribute> pattributes) {
        attributes = pattributes;
    }


    public void setOperations(List<Operation> poperations) {
        operations = poperations;
    }


   

    public void invokeTransformation(){
        transformationStrategy.createFile(this.name);
        transformationStrategy.writeFile(this.name, this.stereotype, this.visibility, this.attributes, this.operations);
    }

    
}
