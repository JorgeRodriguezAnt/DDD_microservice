import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/* import org.json.simple.parser.ParseException; */



public class JsonTransformer {
  // JSON String
 /*   String jsonString = "[{\"value_object_name\":\"MoneytoryValue\",\"value_object_stereotype\":\"value_object\",\"value_object_attributes\":[{\"attribute_name\":\"total\",\"attribute_type\":\"double\"},{\"attribute_name\":\"unit\",\"attribute_type\":\"string\"}]},{\"value_object_name\":\"MoneytoryValue2\",\"value_object_stereotype\":\"value_object\",\"value_object_attributes\":[{\"attribute_name\":\"total2\",\"attribute_type\":\"double\"},{\"attribute_name\":\"unit2\",\"attribute_type\":\"string\"}]}]"; */
   
  public JsonTransformer(){

  }
  public void JsonParser() {

    FileValueObject file = new FileValueObject();
    JSONParser parser = new JSONParser();

    try {
      //Read Json
       Object obj = parser.parse(new FileReader("src/Diagram.json"));
       
       JSONObject jsonObject = (JSONObject)obj;


       JSONArray jsonArray = (JSONArray) jsonObject.get("Class");
       
       
       for (int i = 0; i <jsonArray.size(); i++) {
         
         JSONObject att = (JSONObject) jsonArray.get(i);
         JSONArray arrayAtt= (JSONArray) att.get("class_attributes");
        
         ArrayList<String> arrAttName = new ArrayList<>();//name attribute
         ArrayList<String> arrAttType= new ArrayList<>();//type attribute
         ArrayList<String> arrAttIdentifier= new ArrayList<>();//identifier attribute
         ArrayList<String> arrAttVisibility= new ArrayList<>();//visibility attribute

          String className = (String) ((JSONObject)jsonArray.get(i)).get("class_name");
          String classStereotype = (String) ((JSONObject)jsonArray.get(i)).get("class_stereotype");
          String classVisibility = (String) ((JSONObject)jsonArray.get(i)).get("class_visibility");
      
         
          for (int j = 0; j < arrayAtt.size(); j++) {

            String attName = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_name");
            String attType = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_type");
            String attIdentifier = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_is_identifier");
            String attVisibility = (String) ((JSONObject)arrayAtt.get(j)).get("attribute_visibility");
            
            arrAttName.add(attName);
            arrAttType.add(attType);
            arrAttIdentifier.add(attIdentifier);
            arrAttVisibility.add(attVisibility);
            
          }
         
         if(classStereotype.equals("value_object")){
          System.out.println("archivo");
          file.Create(className);
          file.Write(className, classStereotype, classVisibility, arrAttName, arrAttType, arrAttIdentifier, arrAttVisibility);
         }
         
          
       }

    } catch(Exception e) {
       e.printStackTrace();
    }
   
     /* try {

        // Parse JSON string using JSON parser.
        Object obj = parser.parse(new FileReader("src/Diagram.json"));
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
      }  */
  }


}
