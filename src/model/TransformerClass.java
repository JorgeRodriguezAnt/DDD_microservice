package model;


import java.util.List;


public abstract class TransformerClass {
    
    protected TransformationStrategy transformationStrategy;

    public String id;
    public String name;
    public String stereotype;
    public String visibility;
    public String abstractClass;
    public String fatherClass;
    public List<Attribute> attributes;
    public List<Operation> operations;
    


    
    public String getFatherClass() {
        return fatherClass;
    }

    public void setFatherClass(String fatherClass) {
        this.fatherClass = fatherClass;
    }

    public TransformerClass() {
    }

    public TransformerClass(String pid,String pname, String pstereotype, String pvisibility, String pabstract, String pfather, List<Attribute> pattributes,
            List<Operation> poperations) {
        id = pid;
        name = pname;
        stereotype = pstereotype;
        visibility = pvisibility;
        abstractClass = pabstract;
        fatherClass = pfather;
        attributes = pattributes;
        operations = poperations;
    }


    

    public String getAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(String abstractClass) {
        this.abstractClass = abstractClass;
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

    public void setAttributes(String relationRoleNameEnd, String typeAtt, String string, String string2, String string3, List<Attribute> pattributes) {
        attributes = pattributes;
    }


    public void setOperations(List<Operation> poperations) {
        operations = poperations;
    }


   

    public void invokeTransformation(){
        transformationStrategy.createFile(this.name);
        transformationStrategy.writeFile(this.name, this.stereotype, this.visibility, this.abstractClass, this.fatherClass, this.attributes, this.operations);
    }

    
}