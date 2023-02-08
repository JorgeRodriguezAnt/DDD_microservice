import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/* import org.json.simple.parser.ParseException; */



public class JsonTransformer {
 
  public JsonTransformer(){

  }
  public void JsonParser() {

    FileValueObject fileVO = new FileValueObject();
    FileEntity fileEntity = new FileEntity();
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
          fileVO.Create(className);
          fileVO.Write(className, classStereotype, classVisibility, arrAttName, arrAttType, arrAttIdentifier, arrAttVisibility);
         } 

         if(classStereotype.equals("Entity")){
          System.out.println("archivo");
          fileEntity.Create(className);
          fileEntity.Write(className, classStereotype, classVisibility, arrAttName, arrAttType, arrAttIdentifier, arrAttVisibility);
         }
         
          
       }

    } catch(Exception e) {
       e.printStackTrace();
    }
   
  }


}
