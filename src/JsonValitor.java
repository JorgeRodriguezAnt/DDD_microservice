import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonValitor {
    public JsonValitor(){
      
    }

    public void Valitor(){

        JSONParser parser = new JSONParser();
        JsonTransformer jTransformer = new JsonTransformer();
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
                System.out.println("error");
                return;
            }
        
           
            for (int j = 0; j < arrayAtt.size(); j++) {
              String attName = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_name");
              if(attName==null || attName.trim().isEmpty() ) {
                System.out.println("error");
                return;
              }

              String attType = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_type");
              if(attType==null || attType.trim().isEmpty() ) {
                System.out.println("error");
                return;
              }
              
              jTransformer.JsonParser();
              
            }
           
           
           
            
         }

      } catch(Exception e) {
         e.printStackTrace();
      }
      }
    }

