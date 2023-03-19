package generator;

import java.io.File; 
import java.io.IOException;
import java.util.List;

import model.Operation;
import model.Parameter;

import java.io.FileWriter;

public class CreateService {

    private static final String serviceClassName = "Service";

    public void createFile(String nameService, List<Operation> operationService) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + serviceClassName + nameService+".java");  
            if (myObj.createNewFile()) {  
              System.out.println("File created: " + myObj.getName());  
              System.out.println("Absolute path: " + myObj.getAbsolutePath());  
            } else {  
              System.out.println("File already exists.");  
            }  
            writeFile(nameService, operationService);
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();  
          } 
    }

    
    public void writeFile(String nameService,  List<Operation> operationService) {
        
        try {
            
            try (FileWriter myWriter = new FileWriter("C:\\Users\\JoJa Morrison\\Documents\\Tesis\\Proximo semestre\\implement\\" + serviceClassName+ nameService+ ".java")) {
                myWriter.write("public interface " + serviceClassName + nameService +"{\n");
                for (Operation operation : operationService) {
                   myWriter.write("\n" + operation.Visibility + " " + operation.ReturnType + " " + operation.Name + "(");
                   for (Parameter param : operation.Parameters) {
                     myWriter.write(param.Type + " " + param.Name);
                     if(operation.Parameters.indexOf(param) != operation.Parameters.size()-1){
                      myWriter.write(", ");
                     }
                    }
                    myWriter.write(")");
                }
                myWriter.write("\n\n}\n");
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
         
        } 
       
        
        

}

