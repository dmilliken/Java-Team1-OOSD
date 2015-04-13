package com.java.team1.travelexperts;
 
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
 
// - - - - - - - - - - - - - - - - - - - - - - - -
// This file contains methods to validate form data
// Garima 
// - - - - - - - - - - - - - - - - - - - - - - - -
 
public class Validator<decimal>

{
             //private static final Date String = null;
             //private Object datePattern;
 
 
 
             public static boolean isPresent(JTextField c,String FieldName)
             {
                    if((c.getText().length()==0) || ((c.getText()==""))){
                           showMessage(c,FieldName+" is required.");
                           c.requestFocusInWindow();
                           return false;
                    }
                    return true;              
             }
      
             public static boolean isNumber(JTextComponent c,String FieldName)
             {                  
                    try {
                           int i =Integer.parseInt(c.getText());
                           return true;                    
                    } catch (NumberFormatException e) {
                             showMessage(c,FieldName+" is required.");
                             c.requestFocusInWindow();
                             //e.printStackTrace();
                             return false;
                    }                                      
             }
             
             public static boolean isValidPrice(JTextComponent p,JTextComponent c, String PriceFieldName, String CommFieldName)
             {                  
                    try {
                           int i =Integer.parseInt(c.getText());
                           return true;                    
                    } catch (NumberFormatException e) {
                             showMessage(c,CommFieldName+" should be less than " + PriceFieldName +".");
                             c.requestFocusInWindow();
                             //e.printStackTrace();
                             return false;
                    }                                      
             }
      
             public static boolean isPositiveDouble(JTextComponent c,String FieldName)
             {                  
                    try {
                           double i = Double.parseDouble(c.getText());
                           if(i>0)                         
                           return true;
                           else
                        	   {showMessage(c,FieldName+" must be positive.");
                                 return false;}
                    }
                   
                   
                    catch (NumberFormatException e) {
                             showMessage(c,FieldName+" is required.");
                             c.requestFocusInWindow();
                             //e.printStackTrace();
                             return false;
                    }
                                                    
             }
            
            
            
             /*//Check date is in proper format or not     
             public boolean isDate(JDateChooser c,String FieldName)
             {
                    boolean isDate =false;
                    //String date=c.getText();
                    String date=c.getDateFormatString();
                    //SimpleDateFormat dateformat= new SimpleDateFormat(c.getDateFormatString())
                    String  datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
                    //isDate = date.matches(datePattern);
                    //return isDate;
                    if(isDate==false)
                    {
                      
                       c.requestFocusInWindow();
                       //e.printStackTrace();
                       return false;
                     }                                    
                    return true;             
             }*/
            
            
             public static boolean dateCompare(Date startdate,Date enddate)
             {
                    //Package pkg1=new Package();
                    //Date datestart =pkg1.getPkgStartDate() ;
                    //Date dateend= pkg1.getPkgEndDate();
                                 int rs=enddate.compareTo(startdate);
                                
                                 if(rs==1){
                                        return true;
                                 }
                               else{
                                  return false;
                                  //JOptionPane.showMessageDialog(this,"Check date","Invalid Entry", JOptionPane.ERROR_MESSAGE);                                   
                                 }                         
             }
                          
                           public boolean isEmail(JTextComponent c,String FieldName)
             {
                    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                    String email1 = c.getText();
                    boolean b = email1.matches(EMAIL_REGEX);
                    if(b==false)
                    {
                      showMessage(c,FieldName+" is required.");
                       c.requestFocusInWindow();
                       //e.printStackTrace();
                       return false;
                     }                                    
                    return true;
             }
            
            
             private static void showMessage(JTextComponent c, String message) {
                    // TODO Auto-generated method stub
                    JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
             }
}