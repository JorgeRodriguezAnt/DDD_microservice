package com.jrodriguezuv.generatems.service.generate;



import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jrodriguezuv.generatems.service.model.AggregateRoot;
import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.Entity;
import com.jrodriguezuv.generatems.service.model.Operation;
import com.jrodriguezuv.generatems.service.model.Parameter;
import com.jrodriguezuv.generatems.service.model.TransformerClass;
import com.jrodriguezuv.generatems.service.model.ValueObject;
import com.jrodriguezuv.generatems.service.util.Errors;




public class Validator  {
    
    private static final String entityType = "Entity";
    private static final String valueObjectType = "Value Object";
    private static final String aggregateRootType = "Aggregate Root";
    
    
  
    List<TransformerClass> classesToTransform;
  
    /* CreateService service = new CreateService(); */
  
    CreateRepository repository ;
  
    int count = 0;
    
    public Validator(){
      classesToTransform = new ArrayList<TransformerClass>();
      repository = new CreateRepository();
      }
  

 /*  public List<TransformerClass> validateJSON(){ */
    public List<TransformerClass> validateJSON( String json){
    JSONParser parser = new JSONParser();
    Errors errors = new Errors(); 
    try {
        //Read Json
        /* Object obj = parser.parse(json); */
        Object object = parser.parse(json);
        
        
        // Validation: is a JSOn object and containds classess
        JSONObject jsonObject = (JSONObject)object;
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
            
  
            List<Attribute> attributes = new ArrayList<>();
            List<Operation> operations = new ArrayList<>();
            
  
  
            String classId = (String) ((JSONObject) jsonArray.get(i)).get("class_id");
            String className = (String) ((JSONObject)jsonArray.get(i)).get("class_name");
            String classStereotype = (String) ((JSONObject)jsonArray.get(i)).get("class_stereotype");
           
  
            String classVisibility = (String) ((JSONObject)jsonArray.get(i)).get("class_visibility");
  
            String classAbstract = (String) ((JSONObject)jsonArray.get(i)).get("class_is_abstract");
  
            String classFather = (String) ((JSONObject)jsonArray.get(i)).get("class_father");
  
            
            //Error si VO or AR es abstract
            /* if(classAbstract.equals("yes") && (classAbstract.equals("Value Object") || classAbstract.equals("Aggregate Root"))){
              errors.toJSON();
              System.exit(0);
            } */
  
            //In case of missing essential data of the class, send an error
  
            /* if( (className==null || className.trim().isEmpty()) || (classStereotype==null || classStereotype.trim().isEmpty()) ){
              System.out.println("Empty, white or null spaces are not allowed in the json\n");
              errors.toJSON();
              System.exit(0);
            } */
  
            //Creation of attributes 
            for (int j = 0; j < arrayAtt.size(); j++) {
  
              
              String attName = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_name");
              String attType = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_type");
              String attIdentifier = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_is_identifier");
              String attVisibility = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_visibility");
              String attMultiplicity = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_multiplicity");
              String attNull = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_allies_null"); 
              
              attributes.add(new Attribute(attName, attType, attIdentifier, attVisibility, attMultiplicity,attNull ));
              
            }
  
            //Creation of Operations
  
            
            
            for (int k = 0; k < arrayOperation.size(); k++) {
  
              String opName = (String) ((JSONObject)arrayOperation.get(k)).get("operation_name");
              String opVisibility = (String) ((JSONObject)arrayOperation.get(k)).get("operation_visibility");
              String opReturn = (String) ((JSONObject)arrayOperation.get(k)).get("return_type");
              
              
              // Creation of parameters
  
                JSONObject param= (JSONObject) arrayOperation.get(k);  
                JSONArray arrayparam= (JSONArray) param.get("operation_parameters");
              
                List<Parameter> parameters = new ArrayList<>();  
                
                for (int l = 0; l < arrayparam.size(); l++) {
                  
                 String paramName = (String) ((JSONObject)arrayparam.get(l)).get("param_name");
                 String paramType = (String) ((JSONObject)arrayparam.get(l)).get("param_type");
  
                  parameters.add(new Parameter(paramName, paramType));
                }   
               
               operations.add(new Operation(opName, opVisibility, opReturn, parameters));
               
        
            }
           
            

            switch (classStereotype) {
              case entityType:
                classesToTransform.add(new Entity(classId,className, classStereotype, classVisibility, classAbstract, classFather, attributes, operations));
                break;
              case valueObjectType:
                System.out.println("a");
                classesToTransform.add(new ValueObject(classId,className, classStereotype, classVisibility, classAbstract, classFather, attributes, operations));
                break;
              case aggregateRootType:
              System.out.println("b");
                classesToTransform.add(new AggregateRoot(classId, className, classStereotype, classVisibility, classAbstract, classFather, attributes, operations)); 
                break;
            }
  
            
      }
      validateRelations validateRelations = new validateRelations();
      validateRelations.validate(classesToTransform,json);
      repository.createFile(classesToTransform);

        
      } catch(Exception e) {
        errors.toJSON();
        
          
      }
     
        
      return this.classesToTransform;
    }
  
  
  }
  