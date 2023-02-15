package Generator;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.Attribute;
import Model.Entity;
import Model.EntityTransformer;
import Model.Operation;
import Model.Parameter;
import Model.TransformerClass;
import Model.ValueObject;
import Model.ValueObjectTransformer;


public class Validator {
    
  List<TransformerClass> Object;
    public Validator(){
      Object = new ArrayList<TransformerClass>();
    }

    public void JsonValidator(){
        JSONParser parser = new JSONParser();

        String msg = "Invalid JSON. Error in ";
      try {
        //Read Json
         Object obj = parser.parse(new FileReader("src/Diagram.json"));
         
         JSONObject jsonObject = (JSONObject)obj;


         JSONArray jsonArray = (JSONArray) jsonObject.get("Class");
         
         
         //Class
         for (int i = 0; i <jsonArray.size(); i++) {
           
           JSONObject att = (JSONObject) jsonArray.get(i);
           JSONArray arrayAtt= (JSONArray) att.get("class_attributes");
           JSONArray arrayOperation = (JSONArray) att.get("class_operations"); 
           


           List<Attribute> Attributes = new ArrayList<>();
           List<Operation> Operations = new ArrayList<>();
           List<Parameter> Parameters = new ArrayList<>();  

            String className = (String) ((JSONObject)jsonArray.get(i)).get("class_name");
            if(className==null || className.trim().isEmpty() ) {
                System.out.println(msg + "class_name");
                System.exit(0);
            }
        
            String classStereotype = (String) ((JSONObject)jsonArray.get(i)).get("class_stereotype");
            if(classStereotype==null || classStereotype.trim().isEmpty() ) {
              System.out.println(msg + "class_stereotype");
              System.exit(0);
            }

            String classVisibility = (String) ((JSONObject)jsonArray.get(i)).get("class_visibility");

            //Atributtes
            for (int j = 0; j < arrayAtt.size(); j++) {

              
              String attName = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_name");
              if(attName==null || attName.trim().isEmpty() ) {
                System.out.println(msg + "attribute_name");
                System.exit(0);
              }

              String attType = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_type");
              if(attType==null || attType.trim().isEmpty() ) {
                System.out.println(msg + "attribute_type");
                System.exit(0);
              }
              
              String attIdentifier = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_is_identifier");
              String attVisibility = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_visibility");
              String attMultiplicity = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_multiplicity");
              String attNull = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_allies_null");

              
              
              Attributes.add(new Attribute(attName, attType, attIdentifier, attVisibility, attMultiplicity,attNull ));
              
            }

            //operations

            for (int k = 0; k < arrayOperation.size(); k++) {
              String opName = (String) ((JSONObject)arrayOperation.get(k)).get("operation_name");
              String opVisibility = (String) ((JSONObject)arrayOperation.get(k)).get("operation_visibility");
              String opReturn = (String) ((JSONObject)arrayOperation.get(k)).get("return_type");
              
              
              // Parameter
              JSONObject param= (JSONObject) arrayOperation.get(k); 
              JSONArray arrayparam= (JSONArray) param.get("operation_parameters");
            
               for (int l = 0; l < arrayparam.size(); l++) {
                 String paramName = (String) ((JSONObject)arrayparam.get(l)).get("param_name"); 
                 String paramType = (String) ((JSONObject)arrayparam.get(l)).get("param_type");
                
                 Parameters.add(new Parameter(paramName, paramType));

              }  

              Operations.add(new Operation(opName, opVisibility, opReturn, Parameters));
            }
            
          
           Object.add(new Entity(className, classStereotype, classVisibility, Attributes, Operations));
          
           //Create file(s) entities
           Entity entities = new Entity( new EntityTransformer());
           if(Object.get(i).Stereotype.equals("Entity")){
            
            entities.invokeTransformation(Object.get(i).Name, Object.get(i).Stereotype, Object.get(i).Visibility, Object.get(i).getAttributes(), Object.get(i).getOperations());
          }

           //Create file(s) value objects
           ValueObject valueObjects = new ValueObject( new ValueObjectTransformer());
           if(Object.get(i).Stereotype.equals("Value Object")){

            valueObjects.invokeTransformation(Object.get(i).Name, Object.get(i).Stereotype, Object.get(i).Visibility, Object.get(i).getAttributes(), Object.get(i).getOperations());
          }
          
          
        

         }
         


      } catch(Exception e) {
         e.printStackTrace();
      }
    }


}
