import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;



public class FileEntity {
    public void Create(String className){
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

    public void Write(String className, String classStereotype, String classVisibility, ArrayList<String> arrAttName, ArrayList<String> arrAttType, ArrayList<String> arrAttIdentifier, ArrayList<String> arrAttVisibility){
      try {  
        FileWriter myWriter = new FileWriter("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + className + ".java");
        myWriter.write(classVisibility + " class " + className + "{\n\n");

        //Attributes
        for (int i = 0; i <  arrAttName.size(); i++) {
          myWriter.write(arrAttVisibility.get(i) + " " + arrAttType.get(i).substring(0, 1).toUpperCase() + arrAttType.get(i).substring( 1).toLowerCase() + " " +  arrAttName.get(i) +";\n" );
        }
        
        //Constructor
        myWriter.write("\n" + classVisibility + " "+ className +"(");
        for (int i = 0; i <  arrAttName.size(); i++) {
          myWriter.write(arrAttType.get(i).substring(0, 1).toUpperCase() + arrAttType.get(i).substring( 1).toLowerCase() + " " +  arrAttName.get(i));
          if(i !=  arrAttName.size()-1){
            myWriter.write(", ");
          }
          }
         myWriter.write(") {\n"); 
         for (int j = 0; j <  arrAttName.size(); j++) {
          myWriter.write("\tthis." +  arrAttName.get(j) + " = " +  arrAttName.get(j) +";\n");
        }
        myWriter.write("}\n");
        
        //getter
        for (int i = 0; i < arrAttType.size(); i++) {

          myWriter.write( "\n" + arrAttVisibility.get(i) + " " + arrAttType.get(i).substring(0,1).toUpperCase() + arrAttType.get(i).substring(1).toLowerCase() +" get" +  arrAttName.get(i).substring(0, 1).toUpperCase() +  arrAttName.get(i).substring( 1).toLowerCase() + "() {\n");
          myWriter.write("\treturn this." +  arrAttName.get(i) + ";\n");
          myWriter.write("}\n");
          myWriter.write("\n"); 
       
        }

        //setter
        for (int i = 0; i < arrAttType.size(); i++) {

            myWriter.write( "\n" + arrAttVisibility.get(i) + " " + arrAttType.get(i).substring(0,1).toUpperCase() + arrAttType.get(i).substring(1).toLowerCase() +" set" +  arrAttName.get(i).substring(0, 1).toUpperCase() +  arrAttName.get(i).substring( 1).toLowerCase() + "(" + arrAttType.get(i).substring(0,1).toUpperCase() + arrAttType.get(i).substring(1).toLowerCase() + " " + arrAttName.get(i) +  ") {\n");
            myWriter.write("\treturn this." +  arrAttName.get(i) + " = " + arrAttName.get(i) + ";\n");
            myWriter.write("}\n");
            myWriter.write("\n"); 
         
          }





        //toString
        myWriter.write("\n@Override\n");
        myWriter.write("public String toString() {\n");
        myWriter.write("\treturn " + "\"" );
        myWriter.write(className + " [");
        for (int i = 0; i < arrAttName.size(); i++) {
          myWriter.write(" "+ arrAttName.get(i) + "=\" + "+  arrAttName.get(i) + " + \" ");
          if(i !=  arrAttName.size()-1){
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
