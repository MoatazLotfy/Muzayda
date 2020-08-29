/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLValidation;

/**
 *
 * @author mezo_
 */
import javafx.scene.control.TextField;

/**
 *
 * @author mezo_
 */
public class PhoneTextField extends TextField{

   
    
    @Override
    public void replaceText(int start, int end, String text) {
        if(end<11){
        if(start==0){
            if(text.matches("[0]") || text.isEmpty()){
          super.replaceText(start, end, text);
     }
        }
            else if(start==1){
             if(text.matches("[1]") || text.isEmpty()){
          super.replaceText(start, end, text);
            }
            }
              else if(start==2){
             if(text.matches("[0-2]") || text.isEmpty()){
          super.replaceText(start, end, text);
            }
              }
        
              else if(text.matches("[0-9]") || text.isEmpty()){
          super.replaceText(start, end, text);
     }
     
        
    }
}
    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement); //To change body of generated methods, choose Tools | Templates.
    }
    
}

