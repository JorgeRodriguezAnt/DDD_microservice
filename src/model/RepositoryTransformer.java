package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RepositoryTransformer implements TransformationStrategy{

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
    public void writeFile(String className, String classStereotype, String classVisibility, String classAbstract,String classFather, List<Attribute>Attributes, List<Operation> operations) {
        // TODO Auto-generated method stub

        try {  
        FileWriter myWriter = new FileWriter("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + className + ".java");
        myWriter.write(classVisibility + " interface " + className + "{\n\n");

        for (int t = 0; t < operations.size(); t++) {
           
            myWriter.write("\n" + operations.get(t).Visibility + " " + operations.get(t).ReturnType +  " " + operations.get(t).Name+ "(" );
              /* myWriter.write( operations.get(t).Parameters.get(t).Type + " " + operations.get(t).Parameters.get(t).Name);    */
            for (int i = 0; i < operations.get(t).Parameters.size(); i++) {
                    myWriter.write( operations.get(t).Parameters.get(i).Type + " " + operations.get(t).Parameters.get(i).Name); 
                    if( i != operations.get(t).Parameters.size()-1){
                      myWriter.write(", ");
                    }
                    
            }   
            
             myWriter.write(" )" + "{ }\n");
             
         
        } 
          

        
        myWriter.write("\n}\n");
      
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      } 
    }
    
}
