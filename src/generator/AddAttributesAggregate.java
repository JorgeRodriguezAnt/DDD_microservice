package generator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import model.Attribute;
import model.Relation;
import model.TransformerClass;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddAttributesAggregate {
    

    

    public AddAttributesAggregate(){

    }

    public void addAttributes(List<Attribute> pattributes, List<TransformerClass> classesToTransform) throws FileNotFoundException, IOException, ParseException{
        
      for (TransformerClass transformerClass : classesToTransform) {
        System.out.println( "clases:" +transformerClass.name);
      }
        

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
        String relClassIdStart = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_id_Start");
        String relMultEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Multiplicity_End");
        String relRoleNameEnd= (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Role_Name_End");
        String relClassEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get  ("Relation_Class_End");
        String relClassIdEnd = (String) ((JSONObject)jsonArrayRelation.get(t)).get("Relation_Class_id_End");
       Relations.add(new Relation(relId, relType, relMultStart, relRoleNameStart, relClassStart, relClassIdStart, relMultEnd, relRoleNameEnd, relClassEnd, relClassIdEnd));

          




          /* if(Relations.get(t).relationMultiplicityEnd.contains("..*") || Relations.get(t).relationMultiplicityEnd.contains("..2")){
            for (TransformerClass transformerClass : classesToTransform) {
              if(transformerClass.id.equals(Relations.get(t).RelationClassId)){
                String typeAtt = "List<"+transformerClass.name +">";
                pattributes.add(new Attribute(Relations.get(t).relationRoleNameEnd, typeAtt, "no", "private","yes", null));
              }
            }
            
          }else{
            pattributes.add(new Attribute(Relations.get(t).relationRoleNameEnd, Relations.get(t).relationClassEnd, "no", "private","no", null));
          } */
          
        }
       


       
            
             
                
            
            
        
        
    }
}
