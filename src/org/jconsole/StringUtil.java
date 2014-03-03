/*
 * StringUtil.java
 *
 * Created on February 9, 2002, 3:46 PM
 */

package org.jconsole;
import java.util.*;
/**
 *
 * @author  Niraj Juneja
 * @version 
 */
public class StringUtil {

    public static Properties subList(Properties prop , String pattern){
        
        Properties aprop = new Properties();
        Enumeration enum2  =prop.propertyNames();
        while(enum2.hasMoreElements())
        {
         String str = (String)enum2.nextElement();
         if(str.startsWith(pattern))
            aprop.put(str , prop.getProperty(str));

        }
        return aprop;
    }
        
   
    
}
