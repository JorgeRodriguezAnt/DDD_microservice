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
  int count = 0;
  
  public Validator(){
    classesToTransform = new ArrayList<TransformerClass>();
    }

  public List<TransformerClass> validateJSON(){
    JSONParser parser = new JSONParser();
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
          


          String classId = (String) ((JSONObject) jsonArray.get(i)).get("class_id");
          String className = (String) ((JSONObject)jsonArray.get(i)).get("class_name");
          String classStereotype = (String) ((JSONObject)jsonArray.get(i)).get("class_stereotype");
         

          String classVisibility = (String) ((JSONObject)jsonArray.get(i)).get("class_visibility");

          //In case of missing essential data of the class, send an error

          if( (className==null || className.trim().isEmpty()) || (classStereotype==null || classStereotype.trim().isEmpty()) ){
            System.out.println("Empty, white or null spaces are not allowed in the json\n");
            errors.toJSON();
            System.exit(0);
          }

          //Creation of attributes 
          for (int j = 0; j < arrayAtt.size(); j++) {

            
            String attName = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_name");
            String attType = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_type");
            String attIdentifier = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_is_identifier");
            String attVisibility = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_visibility");
            String attMultiplicity = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_multiplicity");
            String attNull = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_allies_null"); 
            
            Attributes.add(new Attribute(attName, attType, attIdentifier, attVisibility, attMultiplicity,attNull ));
            
          }

          //Creation of Operations

          
          
          for (int k = 0; k < arrayOperation.size(); k++) {

            String opName = (String) ((JSONObject)arrayOperation.get(k)).get("operation_name");
            String opVisibility = (String) ((JSONObject)arrayOperation.get(k)).get("operation_visibility");
            String opReturn = (String) ((JSONObject)arrayOperation.get(k)).get("return_type");
            
            
            // Creation of parameters

              JSONObject param= (JSONObject) arrayOperation.get(k);  
              JSONArray arrayparam= (JSONArray) param.get("operation_parameters");
              System.out.println("parametros: " + arrayparam);
              List<Parameter> Parameters = new ArrayList<>();  
              
              for (int l = 0; l < arrayparam.size(); l++) {
                
               String paramName = (String) ((JSONObject)arrayparam.get(l)).get("param_name");
               String paramType = (String) ((JSONObject)arrayparam.get(l)).get("param_type");

                Parameters.add(new Parameter(paramName, paramType));
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
              addAttributes.addAttributes(Attributes, classesToTransform);
              break;
          }
    }

    
      
    } catch(Exception e) {
       /*  e.printStackTrace(); */
        
        errors.append(e);
    }

    return this.classesToTransform;
  }


}
