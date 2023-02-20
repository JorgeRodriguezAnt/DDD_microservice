package model;

import java.util.List;
import java.io.File; 
import java.io.IOException;

import java.io.FileWriter;
public class ValueObjectTransformer implements TransformationStrategy{
    //Create file(s) Value Object(s)
    @Override
    public void createFile(String className) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + className + ".java");  
            if (myObj.createNewFile()) {  
              System.out.println("File created: " + myObj.getName());  
              System.out.println("Absolute path: " + myObj.getAbsolutePath());  
            } else {  
              System.out.println("File already exists.");  
            }  
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();  
          } 
    }

    @Override
    public void writeFile(String className, String classStereotype, String classVisibility, List<Attribute>Attributes, List<Operation> operations) {
        // TODO Auto-generated method stub

        try {  
        FileWriter myWriter = new FileWriter("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + className + ".java");
        myWriter.write(classVisibility + " class " + className + "{\n\n");

        //Attributes
         for (int i = 0; i <  Attributes.size(); i++) {
          myWriter.write(Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0, 1).toUpperCase() + Attributes.get(i).Type.substring( 1).toLowerCase() + " " +  Attributes.get(i).Name +";\n");
        } 
        
        //Constructor
        myWriter.write("\n" + classVisibility + " "+ className +"(");
        
        for (int i = 0; i <  Attributes.size(); i++) {
            myWriter.write(Attributes.get(i).Type.substring(0, 1).toUpperCase() + Attributes.get(i).Type.substring( 1).toLowerCase() + " " +  Attributes.get(i).Name);
            if(i !=  Attributes.size()-1){
              myWriter.write(", ");
            }
            } 
            myWriter.write(") {\n"); 
           for (int j = 0; j <  Attributes.size(); j++) {
            myWriter.write("\tthis." +  Attributes.get(j).Name + " = " +  Attributes.get(j).Name +";\n");
          }
          myWriter.write("}\n");

        //getter
       
        for (int i = 0; i < Attributes.size(); i++) {

            myWriter.write( "\n" + Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0,1).toUpperCase() + Attributes.get(i).Type.substring(1).toLowerCase() +" get" +  Attributes.get(i).Name.substring(0, 1).toUpperCase() +  Attributes.get(i).Name.substring( 1).toLowerCase() + "() {\n");
            myWriter.write("\treturn this." +  Attributes.get(i).Name + ";\n");
            myWriter.write("}\n");
            myWriter.write("\n"); 
        }
        
        //toString
        myWriter.write("\n@Override\n");
        myWriter.write("public String toString() {\n");
        myWriter.write("\treturn " + "\"" );
        myWriter.write(className + " [");
         for (int i = 0; i < Attributes.size(); i++) {
          myWriter.write(" "+ Attributes.get(i).Name + "=\" + "+  Attributes.get(i).Name + " + \" ");
          if(i !=  Attributes.size()-1){
            myWriter.write(", ");
          }
        } 
        
        myWriter.write("]\";\n");
        myWriter.write("}\n");
        myWriter.write("\n}\n");
        
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      } 
    }
    
}
