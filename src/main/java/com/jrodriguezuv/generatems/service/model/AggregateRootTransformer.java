package com.jrodriguezuv.generatems.service.model;

import java.io.File; 
import java.io.IOException;
import java.util.List;


import com.jrodriguezuv.generatems.service.generate.CreateStructureSpringBoot;
import java.io.FileWriter;

public class AggregateRootTransformer implements TransformationStrategy {

  CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
    @Override
    public void createFile(String className) {
        // TODO Auto-generated method stub
        try {  
            File myObj = new File(createStructureSpringBoot.dString+"\\model\\"+ className + ".java");  
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
    public void writeFile(String className, String classStereotype, String classVisibility, String classAbstract,String classFather,List<Attribute>Attributes, List<Operation> operations) {
        // TODO Auto-generated method stub

        try {  
        FileWriter myWriter = new FileWriter(createStructureSpringBoot.dString+"\\model\\"+ className + ".java");

        //Package
        myWriter.write("package com.demo." + createStructureSpringBoot.view_name + ".model;\n\n\n");

        //import
        myWriter.write("import org.springframework.data.annotation.Id;\n");

        myWriter.write(classVisibility + " class " + className + "{\n\n");

        //Attributes
         for (int i = 0; i <  Attributes.size(); i++) {
          if(Attributes.get(i).IsIdentifier.equals("yes")){
            myWriter.write("@Id\n" + Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0, 1).toUpperCase() + Attributes.get(i).Type.substring( 1).toLowerCase() + " " +  Attributes.get(i).Name +";\n" );
          }else{
            if(Attributes.get(i).Multiplicity.equals("yes")){
              myWriter.write(Attributes.get(i).Visibility + " " +Attributes.get(i).Type  + " " +  Attributes.get(i).Name +";\n");
            }else{
              myWriter.write(Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0, 1).toUpperCase() + Attributes.get(i).Type.substring( 1).toLowerCase() + " " +  Attributes.get(i).Name +";\n");
            } 
          }
          
          /* myWriter.write(Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0, 1).toUpperCase() + Attributes.get(i).Type.substring( 1).toLowerCase() + " " +  Attributes.get(i).Name +";\n"); */
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

        //Methods
        
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
       
        
        

        //getter
       
        for (int i = 0; i < Attributes.size(); i++) {

            myWriter.write( "\n" + Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0,1).toUpperCase() + Attributes.get(i).Type.substring(1).toLowerCase() +" get" +  Attributes.get(i).Name.substring(0, 1).toUpperCase() +  Attributes.get(i).Name.substring( 1).toLowerCase() + "() {\n");
            myWriter.write("\treturn this." +  Attributes.get(i).Name + ";\n");
            myWriter.write("}\n");
            myWriter.write("\n"); 
        }
        //setter
        
          for (int i = 0; i < Attributes.size(); i++) {

            myWriter.write( "\n" + Attributes.get(i).Visibility + " " + Attributes.get(i).Type.substring(0,1).toUpperCase() + Attributes.get(i).Type.substring(1).toLowerCase() +" set" +  Attributes.get(i).Name.substring(0, 1).toUpperCase() +  Attributes.get(i).Name.substring( 1).toLowerCase() + "(" + Attributes.get(i).Type.substring(0,1).toUpperCase() + Attributes.get(i).Type.substring(1).toLowerCase() + " " + Attributes.get(i).Name +  ") {\n");
            myWriter.write("\treturn this." +  Attributes.get(i).Name + " = " + Attributes.get(i).Name  + ";\n");
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
