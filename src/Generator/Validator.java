package Generator;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Validator {
    

    public Validator(){

    }

    public void JsonValidator(){
        JSONParser parser = new JSONParser();
        
        String msg = "Invalid JSON. Error in ";
      try {
        //Read Json
         Object obj = parser.parse(new FileReader("src/Diagram.json"));
         
         JSONObject jsonObject = (JSONObject)obj;


         JSONArray jsonArray = (JSONArray) jsonObject.get("Class");
         
         
         for (int i = 0; i <jsonArray.size(); i++) {
           
           JSONObject att = (JSONObject) jsonArray.get(i);
           JSONArray arrayAtt= (JSONArray) att.get("class_attributes");

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
              
              
              
            }
           
           
           
            
         }
         

      } catch(Exception e) {
         e.printStackTrace();
      }
    }

}
