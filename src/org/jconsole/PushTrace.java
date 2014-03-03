/*
 * PushTrace.java
 *
 * Created on October 14, 2001, 2:34 PM
 */

package org.jconsole;

import java.util.*;
/**
 *
 * @author  nzjuneja
 * @version 
 */
public class PushTrace {

   private static java.lang.Throwable t;
   private static String _msg;
   
   public static void push(String msg , java.lang.Throwable to) throws CommandFailedException
   {
       t = to;
       throw new CommandFailedException( msg +  to.getMessage());
   }
   public static  void push(java.lang.Throwable to)
   {
       t = to;
   }

   public static void pop()
   {
       if(t != null)
           t.printStackTrace();   
       else 
       {
        Thread.currentThread().dumpStack();   
       }
       t = null;
       Console.out.println(" DONE");
   }
}
