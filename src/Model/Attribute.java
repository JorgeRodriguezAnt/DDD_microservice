package Model;

public class Attribute {
   
    public String Name;
    public String Type;
    public String IsIdentifier;
    public String Multiplicity;
    public String IsNuliable;
    
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
