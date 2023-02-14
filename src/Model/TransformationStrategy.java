package Model;

import java.util.List;

public interface TransformationStrategy {
    //Create method for Create and Write files

    public void Create(String Classname);
    public void Write(String className, String classStereotype, String classVisibility, List<Attribute>Attributes);
}
