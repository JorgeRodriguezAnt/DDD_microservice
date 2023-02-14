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


public class Validator {
    

    public Validator(){
        
    }

    public void JsonValidator(){
        JSONParser parser = new JSONParser();


        /* List<TransformerClass> Classes = new ArrayList<>();
 */     
        List<Entity> EntityObject = new ArrayList<>();
            
        EntityTransformer entityTransformer = new EntityTransformer();
        String msg = "Invalid JSON. Error in ";
      try {
        //Read Json
         Object obj = parser.parse(new FileReader("src/Diagram.json"));
         
         JSONObject jsonObject = (JSONObject)obj;


         JSONArray jsonArray = (JSONArray) jsonObject.get("Class");
         
         
         for (int i = 0; i <jsonArray.size(); i++) {
           
           JSONObject att = (JSONObject) jsonArray.get(i);
           JSONArray arrayAtt= (JSONArray) att.get("class_attributes");
           /* JSONArray arrayOperation = (JSONArray) att.get("class_operations"); */
           /* JSONObject param = (JSONObject) arrayOperation.get(i);
 */


           List<Attribute> Attributes = new ArrayList<>();
          /*  List<Operation> Operations = new ArrayList<>();
           List<Parameter> Parameters = new ArrayList<>(); */

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

            
            for (int j = 0; j < arrayAtt.size(); j++) {

              //Atributtes
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

            //Parameter

            



            
            if(classStereotype.equals("Entity"));{
           EntityObject.add(new Entity(className, classStereotype, classVisibility, Attributes, null));
           
           
          } 

         }
         
         
         
         System.out.println(EntityObject);
         System.out.println(EntityObject.get(0).getAttributes());
          entityTransformer.Create(EntityObject.get(0).Name); 
         entityTransformer.Write(EntityObject.get(0).Name, EntityObject.get(0).Stereotype, EntityObject.get(0).Visibility, EntityObject.get(0).getAttributes());



      } catch(Exception e) {
         e.printStackTrace();
      }
    }


}
