/*
 * echoCommand.java
 *
 * Created on March 7, 2002, 11:05 AM
 */

package org.jconsole.commands;
import org.jconsole.*;
import java.net.*;
/**
 *
 * @author  nzjuneja
 * @version 
 */
public class pingCommand extends ConsoleCommand {

    	private static final String NAME = "ping";
	private static final String DESC = "The network ping";
	private static final String HELP = "ping [ip / Host]";

    
    /** Creates new echoCommand */
        public pingCommand() {
            super(NAME , DESC , HELP);
      }

    protected void initialize() throws Exception {
    }
    
    public void execute(String[] args) throws CommandFailedException {
          InetAddress ad = null;
          try{
        if(args.length > 0)
            ad = InetAddress.getByName(args[0]);
        else 
            ad = InetAddress.getByName(null);
        }catch(Exception ex)
            {
             throw new CommandFailedException("Unable to ping " + args[0] , ex);   
            }
        Console.out.println("HOST = " + ad.getHostName() + " IP= " + ad.getHostAddress());
    }
    
}
