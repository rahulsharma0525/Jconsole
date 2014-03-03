/*
 * SystemProperties.java
 *
 * Created on February 9, 2002, 2:26 PM
 */

package org.jconsole;

import java.io.*;
import java.util.*;
/**
 *
 * @author  nzjuneja
 * @version 
 */
public class SystemProperties {
   
   
   public static final String CFG_FILE = "jconsole.config";
   public static final String PROMPT = "jconsole.prompt";
   public static final String PROMPT_VALUE = "[Jconsole]";   
   public static final String VERSION = "jconsole.version";
   public static final String VERSION_VALUE = "1.0";
   static {
		   java.util.Properties prop = new Properties(System.getProperties());
                   prop.getProperty(CFG_FILE , get("user.home") + File.separator + "jconsole.properties");
                   File file = new File(prop.getProperty(CFG_FILE , 
                            get("user.home") + File.separator + "jconsole.properties"));
                   
                   // load defaults
                   prop.setProperty(PROMPT , PROMPT_VALUE);
                   prop.setProperty(VERSION , VERSION_VALUE);
		   try{
		   prop.load(new FileInputStream(file));
		   }
		   catch(Exception ex)
		   {
			  Console.pt("Properties File " + getConsoleHome() + " Not found \n Proceeding with Default properties : \n Message : " + ex.getMessage());    
		   }
                   System.getProperties().putAll(prop);
		   
   }
   
   
   public static String getConsoleHome()
   {
	return System.getProperty(CFG_FILE , System.getProperty("user.home") + File.separator + "jconsole.properties");
   }      
   
   public static String get(String key)
   {
    return System.getProperty(key);   
   }
 

}
