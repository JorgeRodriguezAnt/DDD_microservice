import java.util.List;

import Generator.Transformer;
import Generator.Validator;
import Model.TransformerClass;

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
