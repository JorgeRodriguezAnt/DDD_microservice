package Model;

import java.util.Set;

public class Operation {
    
  public String Name;
  public String Visibility;
  public String ReturnType;
  public Set<Parameter> Parameters;

  public String getName() {
    return Name;
  } 

  public void setName(String name) {
    Name = name;
  }

  public String getVisibility() {
    return Visibility;
  }

  public void setVisibility(String visibility) {
    Visibility = visibility;
  }

  public String getReturnType() {
    return ReturnType;
  }

  public void setReturnType(String returnType) {
    ReturnType = returnType;
  }

  public Set<Parameter> getParameters() {
    return Parameters;
  }

  @Override
  public String toString() {
    return "Operation [Name=" + Name + ", Visibility=" + Visibility + ", ReturnType=" + ReturnType + ", Parameters="
            + Parameters + "]";
  }

  



  
}
