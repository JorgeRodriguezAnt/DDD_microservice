import java.util.List;

import generator.Transformer;
import generator.Validator;
import model.TransformerClass;

public class App {
    public static void main(String[] args) throws Exception {
        

        /* JsonValidator json = new JsonValidator();
        json.Valitor(); */
        
        Validator json = new Validator();
        
        List<TransformerClass> classestoTransform = json.validateJSON();
        
        Transformer myTransfomer = new Transformer(classestoTransform);
        
        myTransfomer.transform();
        
    }

   
}
