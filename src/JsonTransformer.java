import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JsonTransformer {
  // JSON String
   String jsonString = "[{\"value_object_name\":\"MoneytoryValue\",\"value_object_stereotype\":\"value_object\",\"value_object_attributes\":[{\"attribute_name\":\"total\",\"attribute_type\":\"double\"},{\"attribute_name\":\"unit\",\"attribute_type\":\"string\"}]},{\"value_object_name\":\"MoneytoryValue2\",\"value_object_stereotype\":\"value_object\",\"value_object_attributes\":[{\"attribute_name\":\"total2\",\"attribute_type\":\"double\"},{\"attribute_name\":\"unit2\",\"attribute_type\":\"string\"}]}]";
   
  public JsonTransformer(){

  }

  public JsonTransformer(String jsonString){
    this.jsonString = jsonString;
  }


  public void JsonParser() {

    CreateFile file = new CreateFile();
    JSONParser parser = new JSONParser();
   
    try {

        // Parse JSON string using JSON parser.
        Object object = parser.parse(jsonString);
        JSONArray array = (JSONArray) object;
      
        // Get JSON object from JSON array.
        for (int j = 0; j < array.size(); j++) {
          JSONObject jsonObject = (JSONObject) array.get(j); 
          
          ArrayList<String> arrAtt = new ArrayList<>();//name attribute
          ArrayList<String> arrTyp= new ArrayList<>();//type attribute
          JSONArray jsonArray = (JSONArray) jsonObject.get("value_object_attributes");
          for (int i = 0; i <jsonArray.size(); i++) {
             String nameAtt = (String) ((JSONObject)jsonArray.get(i)).get("attribute_name");
             String typeAtt = (String) ((JSONObject)jsonArray.get(i)).get("attribute_type");
             String att = typeAtt + " " + nameAtt;
             
             arrAtt.add(nameAtt);
             arrTyp.add(typeAtt);
             System.out.println(att);
 
          }
  
         
         String nameClass = (String) jsonObject.get("value_object_name");
        
         System.out.println("Name VO:" + nameClass);  
          
          file.Create(nameClass); 
          file.Write(nameClass,arrAtt, arrTyp); 
        }
         
         
      } catch (ParseException e) {
        e.printStackTrace();
      }
  }


}
