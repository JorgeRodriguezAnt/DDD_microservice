package generator;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.AggregateRoot;
import model.Attribute;
import model.Entity;
import model.Operation;
import model.Parameter;

import model.TransformerClass;
import model.ValueObject;
import util.Errors;



public class Validator {
    
  private static final String entityType = "Entity";
  private static final String valueObjectType = "Value Object";
  private static final String aggregateRootType = "Aggregate Root";
  
  AddAttributesAggregate addAttributes = new AddAttributesAggregate();

  List<TransformerClass> classesToTransform;
  
  public Validator(){
    classesToTransform = new ArrayList<TransformerClass>();
    }

  public List<TransformerClass> validateJSON(){
    JSONParser parser = new JSONParser();
    String msg = "Invalid JSON. Error in ";
    Errors errors = new Errors();
    try {
        //Read Json
        Object obj = parser.parse(new FileReader("src/Diagram.json"));
        
        
        // Validation: is a JSOn object and containds classess
        JSONObject jsonObject = (JSONObject)obj;
        //Not a Valid JSON object

        JSONArray jsonArray = (JSONArray) jsonObject.get("Class");
        //There are no Class objects in the JSON object
          
        //Class
        for (int i = 0; i <jsonArray.size(); i++) {

          // Check get class attributes and operations
          JSONObject att = (JSONObject) jsonArray.get(i);
          JSONArray arrayAtt= (JSONArray) att.get("class_attributes");
          JSONArray arrayOperation = (JSONArray) att.get("class_operations"); 
          // W: Class XX does not have any attributes and operations.
          
          List<Attribute> Attributes = new ArrayList<>();
          List<Operation> Operations = new ArrayList<>();
          List<Parameter> Parameters = new ArrayList<>();  


          String classId = (String) ((JSONObject) jsonArray.get(i)).get("class_id");

          // Validation: Class name is blank
          
          String className = (String) ((JSONObject)jsonArray.get(i)).get("class_name");
          if(className==null || className.trim().isEmpty() ) {
              System.out.println(msg + "class_name");
              System.exit(0);
          }
      
          // Validation: Class name is blank
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
                System.out.println(opName + ":" + paramName);
                
              }   
            
             Operations.add(new Operation(opName, opVisibility, opReturn, Parameters));
      
          }

          

          
         
          switch (classStereotype ) {
            case entityType:
              classesToTransform.add(new Entity(classId,className, classStereotype, classVisibility, Attributes, Operations));
              break;
            case valueObjectType:
              classesToTransform.add(new ValueObject(classId,className, classStereotype, classVisibility, Attributes, Operations));
            break;
            case aggregateRootType:
            
            classesToTransform.add(new AggregateRoot(classId, className, classStereotype, classVisibility, Attributes, Operations)); 
            addAttributes.addAttributes(Attributes);
            
              System.out.println(Operations); 
            
              
            
            break;
          }
    }

    
      
    } catch(Exception e) {
       /*  e.printStackTrace(); */
        System.out.println("este es el error");
        errors.append(e);
    }

    return this.classesToTransform;
  }


}
