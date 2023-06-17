package com.jrodriguezuv.generatems.service.modelTransformation;

public class Attribute {
   
    public String Name;
    public String Type;
    public String IsIdentifier;
    public String Visibility;
    public String Multiplicity;
    public String IsNuliable;
     

    public Attribute(String name, String type, String isIdentifier, String visibility, String multiplicity,
            String isNuliable) {
        Name = name;
        Type = type;
        IsIdentifier = isIdentifier;
        Visibility = visibility;
        Multiplicity = multiplicity;
        IsNuliable = isNuliable;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getIsIdentifier() {
        return IsIdentifier;
    }

    public void setIsIdentifier(String isIdentifier) {
        IsIdentifier = isIdentifier;
    }

    public String getVisibility() {
        return Visibility;
    }

    public void setVisibility(String visibility) {
        Visibility = visibility;
    }
    public String getMultiplicity() {
        return Multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        Multiplicity = multiplicity;
    }

    public String getIsNuliable() {
        return IsNuliable;
    }

    public void setIsNuliable(String isNuliable) {
        IsNuliable = isNuliable;
    }

    @Override
    public String toString() {
        return "Attribute [Name=" + Name + ", Type=" + Type + ", IsIdentifier=" + IsIdentifier + ", Multiplicity="
                + Multiplicity + ", IsNuliable=" + IsNuliable + "]";
    }


    
}
