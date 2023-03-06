package generator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Relation;
import model.TransformerClass;

public class validateRelations {
    


    public void validate(List<TransformerClass> classesToTransform) throws FileNotFoundException, IOException, ParseException{

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/Diagram.json"));
  
        // Validation: is a JSOn object and containds classess
        JSONObject jsonObject = (JSONObject)obj;

        JSONArray jsonArrayRelation = (JSONArray) jsonObject.get("Relation");
        List<Relation> Relations = new ArrayList<>();

        // Creation list of object of Relations
        for (int t = 0; t < jsonArrayRelation.size(); t++) {
      
      
         String relId = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Id");
        String relType = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_type");
        String relMultStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Multiplicity_Start");
        String relRoleNameStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Role_Name_Start");
         String relClassStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_Start");
        String relMultEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Multiplicity_End");
        String relRoleNameEnd= (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Role_Name_End");
        String relClassEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get  ("Relation_Class_End");
        String relClassId = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_id");


         Relations.add(new Relation(relId, relType, relMultStart, relRoleNameStart, relClassStart, relMultEnd, relRoleNameEnd, relClassEnd, relClassId));
  
    }

    }

    
   


   
        
         
            
        
        
    
    
}

