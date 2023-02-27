package generator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import model.Attribute;
import model.Relation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddAttributesAggregate {
    

    

    public AddAttributesAggregate(){

    }

    public void addAttributes(List<Attribute> pattributes) throws FileNotFoundException, IOException, ParseException{
        

        Validator validator = new Validator() {};
        /* validator.validateJSON(); */
       
       /* validator.classesToTransform.size(); */
        

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/Diagram.json"));
      
        // Validation: is a JSOn object and containds classess
        JSONObject jsonObject = (JSONObject)obj;

        JSONArray jsonArrayRelation = (JSONArray) jsonObject.get("Relation");
        List<Relation> Relations = new ArrayList<>();
        for (int t = 0; t < jsonArrayRelation.size(); t++) {
          
          
          String relId = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Id");
          String relType = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_type");
          String relMultStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Multiplicity_Start");
          String relRoleNameStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Role_Name_Start");
          String relClassStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_Start");
          String relMultEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Multiplicity_End");
          String relRoleNameEnd= (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Role_Name_End");
          String relClassEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_End");
          String relClassId = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_id");

    
          Relations.add(new Relation(relId, relType, relMultStart, relRoleNameStart, relClassStart, relMultEnd, relRoleNameEnd, relClassEnd, relClassId));

          if(Relations.get(t).relationMultiplicityStart.equals("1..*") || Relations.get(t).relationMultiplicityStart.equals("0..*")){
            String typeAtt = "List<"+Relations.get(t).relationClassEnd +">";
           ;
            pattributes.add(new Attribute(Relations.get(t).relationRoleNameEnd, typeAtt, "no", "private","yes", null));
          }else{
            pattributes.add(new Attribute(Relations.get(t).relationRoleNameEnd, Relations.get(t).relationClassEnd, "no", "private","no", null));
          }
          
        }
       


       
            
             
                
            
            
        
        
    }
}
