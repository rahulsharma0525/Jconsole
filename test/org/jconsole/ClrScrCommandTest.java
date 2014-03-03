package org.jconsole;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ClrScrCommandTest {
    static CLSCommand clsCommand = new CLSCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    static String curDir = System.getProperty("user.dir");
    static HelpCommand HelpCom = new HelpCommand();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon = JConsole.instance();
        jcon.setCurrentDir(curDir);
        jcon.processLine("help");
        HelpCom.setConsole(jcon);
        try {
            System.out.println("Rahul");
            String[] args1 = new String[0];
            String sampleFile = "Jconsole.xml";
            HelpCom.execute(args1);
            String output = outContent.toString();
            int count=0;
            for(int i=0;i<output.length();i++){
            	
            	if(output.charAt(i)=='\n')
            		count++;
            	
            }            
            System.out.println("Count:"+count);
        } 
        
        catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        clsCommand.setConsole(jcon);
    }


    @Test
    public void ClearCommandSuccess() {
        try {
            String[] args1 = {"1"};
            clsCommand.execute(args1);
            int count=0;
            String lines = outContent.toString();
            for(int i=0;i<lines.length();i++){
            	
            	if(lines.charAt(i)=='\n')
            		count++;
            	
            }
            boolean flag=false;
            //Help command has 41 new line character and the CLS commands adds 50 more 
            if(count==91)
            	flag=true;
            Assert.assertTrue(flag);
        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        outContent.reset();
    }
}