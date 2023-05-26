package com.jrodriguezuv.generatems.service.generate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jrodriguezuv.generatems.model.Tutorial;
import com.jrodriguezuv.generatems.service.model.Attribute;
import com.jrodriguezuv.generatems.service.model.Relation;
import com.jrodriguezuv.generatems.service.model.TransformerClass;



public class validateRelations {
    


    public void validate(List<TransformerClass> classesToTransform, String json) throws FileNotFoundException, IOException, ParseException{
        Tutorial tutorial;
        JSONParser parser = new JSONParser();
        
        Object obj = parser.parse(json);
        /* int count = 0; */
        CreateService service = new CreateService();
        
        CreateController createController = new CreateController();
        JSONArray jsonArray2= (JSONArray) obj;
        for (int z = 0; z < jsonArray2.size(); z++) {
            JSONObject jsonArray3 = (JSONObject) jsonArray2.get(z);

        JSONArray jsonArrayRelation = (JSONArray) jsonArray3.get("Relation");
        List<Relation> objectTRelations = new ArrayList<>();

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


            objectTRelations.add(new Relation(relId, relType, relMultStart, relRoleNameStart, relClassStart, relClassIdStart, relMultEnd, relRoleNameEnd, relClassEnd, relClassIdEnd));

    }


    // Validate relations with possible warning (E, VO) and add attribute AR ("Exception Entity")
    for (TransformerClass transformerClass : classesToTransform) {
        for (Relation relations : objectTRelations) {
            
            if(relations.relationClassIdStart.equals(transformerClass.id)){
                if(transformerClass.stereotype.equals("Aggregate Root") || transformerClass.stereotype.equals("Entity")){
                    if(transformerClass.stereotype.equals("Entity")){
                            System.out.println("Warning: There is no aggregate root, but an entity behaves as one, however it is not recommended in your design.  ");
                            if( relations.relationType.equals("Generalization")){
                                String nameClass = transformerClass.name; 
                                transformerClass.fatherClass = nameClass;
                            }
                    }
                    /* if(transformerClass.stereotype.equals("Aggregate Root") ){
                        count++;
                    } */
                    
                    if(relations.relationMultiplicityEnd.contains("..*") || relations.relationMultiplicityEnd.contains("..2")){   
                        String typeAtt = "List<"+transformerClass.name +">";
                        transformerClass.attributes.add(new Attribute(relations.relationRoleNameEnd, typeAtt, "no", "private","yes", null));
                        
                    }else{
                        transformerClass.attributes.add(new Attribute(relations.relationRoleNameEnd, relations.relationClassEnd, "no", "private","no", null));
                        
                    } 
                    System.out.println("entra");
                   
                    
                }else{
                    System.out.println("Error: Value Object is can't a cluster of associated objects. Re-design your model for optimal class generation, adding an aggregate root.");
                    System.exit(1);
                }
            }
                
        }
    }
    //A message of information about have more of a aggregate root
    /* if(count>1){
        System.out.println("Warning: There are more than one aggregate root, it is recommended that there is only one because of the possible problems or errors it can generate.");
    } */
            
}
       
    }

    
   
        

}
        

        
         
            
        
        
    
    


