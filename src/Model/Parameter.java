package Model;

public class Parameter {
    
    public String Name;
    public String Type;

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
    
    @Override
    public String toString() {
        return "Parameter [Name=" + Name + ", Type=" + Type + "]";
    }

    
}
