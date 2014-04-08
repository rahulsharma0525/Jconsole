package org.jconsole;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HistoryCommandTest {
static HistoryCommand historyCommand = new HistoryCommand();
private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
static String curDir = System.getProperty("user.dir");
static HelpCommand HelpCom = new HelpCommand();
static LSCommand LSCom=new LSCommand();

@Before
public void setup(){
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
    JConsole jcon=JConsole.instance();
    jcon.setCurrentDir(curDir);
    jcon.processLine("help");
    jcon.processLine("ls");
    HelpCom.setConsole(jcon);
    LSCom.setConsole(jcon);
    try {
		System.out.println("Rahul");
		String [] args1 = new String[0];
		String sampleFile = "Jconsole.xml";
		HelpCom.execute(args1);
		LSCom.execute(args1);
		String output=outContent.toString();
		
		}
    catch (CommandFailedException e) {
        e.printStackTrace();
        fail("Exception was thrown");
    }
    historyCommand.setConsole(jcon);
}


@Test
public void HistoryCommandPassCaseCheck()
{
    try {
        String [] args1={"1"};
        historyCommand.execute(args1);
        String output=outContent.toString();
          
        boolean Flag=(output.contains("help")&&output.contains("ls"));
        Assert.assertTrue(Flag);
        
    } catch (CommandFailedException e) {
        e.printStackTrace();
        fail("Exception was thrown");
    }
    outContent.reset();
}


@Test
public void HistoryCommandFailCaseCheck()
{
    try {
        String [] args1={"1"};
        historyCommand.execute(args1);
        String output=outContent.toString();
        
        boolean Flag=(output.contains("help")&&output.contains("cp"));
        Assert.assertTrue(Flag);
        
    } catch (CommandFailedException e) {
        e.printStackTrace();
        fail("Exception was thrown");
    }
    outContent.reset();
}

}
