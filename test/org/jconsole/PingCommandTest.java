package org.jconsole;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.jconsole.commands.pingCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PingCommandTest {

	static pingCommand pingCommand = new pingCommand();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setup(){
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    JConsole jcon=JConsole.instance();
	    pingCommand.setConsole(jcon);
	}


	@Test
	public void PingSuccessTest()
	{
	    try {
	    	boolean flag=false;
	        String [] args1={"www.google.com"};
	        pingCommand.execute(args1);
	        String output=outContent.toString();
	        //if((output.contains("74.125.225.115")||output.contains("74.125.225.82") || output.contains("74.125.225.146")||output.contains("74.125.225.83")))
	        
	        //Google Server IP address Starts with  74.125.225.*
	        //Eg: 74.125.225.115,74.125.225.82 etc
	        if(output.contains("74.125.225."))
	        {
	        	if(output.contains("HOST = www.google.com")){
	        	
	        		flag=true;
	        	}
	        }
	        
	        Assert.assertTrue(flag);
	    } catch (CommandFailedException e) {
	        e.printStackTrace();
	        fail("Exception was thrown");
	    }
	    outContent.reset();
	}
	public void PingFailTest()
	{
	    try {
	    	boolean flag=false;
	        String [] args1={"LocalHost"};
	        pingCommand.execute(args1);
	        String output=outContent.toString();
	        
	        //Since LocalHost is set as server it should not contain other server info.
	        	if(output.contains("HOST = www.google.com")==false){
	        	   		flag=true;
	        	}
	        
	        Assert.assertTrue(flag);
	    } catch (CommandFailedException e) {
	        e.printStackTrace();
	        fail("Exception was thrown");
	    }
	    outContent.reset();
	}
}
