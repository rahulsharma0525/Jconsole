/*
 * Console.java
 *
 * Created on February 9, 2002, 2:55 PM
 */

package org.jconsole;

import java.io.*;

/**
 *
 * @author  nzjuneja
 * @version 
 */
public final class Console {
  public static PrintStream err = System.err;
  public static InputStream in = System.in;
  public static PrintStream out = System.out;
  
  public static void setErrorStream(PrintStream stream)
  {
      err = stream;
  }
  
  public static void setOutputStream(PrintStream stream)
  {
      out = stream;
  }
  
  public static void setInputStream(InputStream stream)
  {
      in = stream;
  }
  
  public static void p(String str)
  {
     out.println(str);   
  }
  
  public static void pt(String str)
  {
   out.println("\t" + str);   
  }
}
