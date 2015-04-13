package com.java.team1.travelexperts;
 
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DecimalStyle;
import java.util.Date;
 
import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
 
import com.toedter.calendar.JDateChooser;
 
// - - - - - - - - - - - - - - - - - - - - - - - -
// This file contains methods to validate form data
// Garima 
// - - - - - - - - - - - - - - - - - - - - - - - -
 
public class Validator<decimal>
 
{
             private static final Date String = null;
             private Object datePattern;
 
             public boolean isPresent(JTextComponent c,String FieldName)
             {
                    if((c.getText().length()==0) && ((c.getText()==""))){
                           showMessage(c,FieldName+"is required.");
                           c.requestFocusInWindow();
                           return false;
                    }
                    return true;              
             }
      
             public boolean isNumber(JTextComponent c,String FieldName)
             {                  
                    try {
                           int i =Integer.parseInt(c.getText());
                           return true;                    
                    } catch (NumberFormatException e) {
                             showMessage(c,FieldName+"is required.");
                             c.requestFocusInWindow();
                             //e.printStackTrace();
                             return false;
                    }                                      
             }
      
             public boolean isDouble(JTextComponent c,String FieldName)
             {                  
                    try {
                           double i = Double.parseDouble(c.getText());
                           return true;                    
                    } catch (NumberFormatException e) {
                             showMessage(c,FieldName+"is required.");
                             c.requestFocusInWindow();
                             //e.printStackTrace();
                             return false;
                    }                                      
             }
            
            
            
             //Check date is in proper format or not
             public boolean isDate(JDateChooser c,String FieldName)
             {
                    boolean isDate =false;
                    //String date=c.getText();
                    String date=c.getDateFormatString();
                    //SimpleDateFormat dateformat= new SimpleDateFormat(c.getDateFormatString())
                    String  datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
                    isDate = date.matches(datePattern);
                    //return isDate;
                    if(isDate==false)
                    {
                       showMessage(null,"Correct Date format is required.");
                       c.requestFocusInWindow();
                       //e.printStackTrace();
                       return false;
                     }                                    
                    return true;
                   
             }
            
             public boolean isEmail(JTextComponent c,String FieldName)
             {
                    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                    String email1 = c.getText();
                    boolean b = email1.matches(EMAIL_REGEX);
                    if(b==false)
                    {
                       showMessage(c,FieldName+"is required.");
                       c.requestFocusInWindow();
                       //e.printStackTrace();
                       return false;
                     }                                    
                    return true;
             }
            
            
             private void showMessage(JTextComponent c, String message) {
                    // TODO Auto-generated method stub
                    JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
             }
}