/*
 * PTrace.java
 *
 * Created on October 14, 2001, 2:43 PM
 */

package org.jconsole;

/**
 *
 * @author  nzjuneja
 * @version 
 */
public class PTrace extends org.jconsole.ConsoleCommand{

  private static final String NAME = "ptrace";
   private static final String DESC = "used to print the last Error Stact trace";
   private static final String HELP = "- ptrace ";
    /** Creates new stop */
    public PTrace() {
         super(NAME, DESC, HELP);
    }

    public void execute(String[] args) throws CommandFailedException {
        PushTrace.pop();
    }
    
    protected void initialize() throws Exception {
    }
}
