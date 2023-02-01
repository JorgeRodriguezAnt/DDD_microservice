import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class CreateFile {

    /* public CreateFile() {
    } */
    public void Create(String name){
        try {  
            File myObj = new File("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + name + ".java");  
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

    public void Write(String name, ArrayList<String> arrAtt, ArrayList<String> arrTyp){
      try {  
        FileWriter myWriter = new FileWriter("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + name + ".java");
        myWriter.write("public class " + name + "{\n\n");

        //Attributes
        for (int i = 0; i < arrAtt.size(); i++) {
          myWriter.write("private " + arrTyp.get(i).substring(0, 1).toUpperCase() + arrTyp.get(i).substring( 1).toLowerCase() + " " + arrAtt.get(i) +";\n" );
        }
        
        //Constructor
        myWriter.write("\npublic " + name +"(");
        for (int i = 0; i < arrAtt.size(); i++) {
          myWriter.write(arrTyp.get(i).substring(0, 1).toUpperCase() + arrTyp.get(i).substring( 1).toLowerCase() + " " + arrAtt.get(i));
          if(i != arrAtt.size()-1){
            myWriter.write(", ");
          }
          }
         myWriter.write(") {\n"); 
         for (int j = 0; j < arrAtt.size(); j++) {
          myWriter.write("\tthis." + arrAtt.get(j) + " = " + arrAtt.get(j) +";\n");
        }
        myWriter.write("}\n");
        
        //getter
        for (int i = 0; i < arrTyp.size(); i++) {
          
          myWriter.write("\npublic " + arrTyp.get(i).substring(0, 1).toUpperCase() + arrTyp.get(i).substring( 1).toLowerCase() +" get" + arrAtt.get(i).substring(0, 1).toUpperCase() + arrAtt.get(i).substring( 1).toLowerCase() + "() {\n");
          myWriter.write("\treturn this." + arrAtt.get(i) + ";\n");
          myWriter.write("}\n");
          myWriter.write("\n"); 
       
        }
        
        //toString
        myWriter.write("\n@Override\n");
        myWriter.write("public String toString() {\n");
        myWriter.write("\treturn " + "\"" );
        myWriter.write(name + " [");
        for (int i = 0; i <arrAtt.size(); i++) {
          myWriter.write(" "+arrAtt.get(i) + "=\" + "+ arrAtt.get(i) + " + \" ");
          if(i != arrAtt.size()-1){
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
