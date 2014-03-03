package org.jconsole;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LSCommandTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	static LSCommand LSCom = new LSCommand();
	static String curDir = System.getProperty("user.dir");
	
	
	@Before
	public void setUpStreams() {
	System.setOut(new PrintStream(outContent));
	System.setErr(new PrintStream(errContent));
	JConsole jcon=JConsole.instance();
	jcon.setCurrentDir(curDir);
	LSCom.setConsole(jcon);
	}
	
	
	@Test
	public void LSComTest()
	{
		try {
			System.out.println("hwllo");
			String [] args1 = new String[0];
			String sampleFile = "Jconsole.xml";
			LSCom.execute(args1);
			String output=outContent.toString();
			System.out.println("the out put of ls command is:"+output);
			assertTrue(output.contains(sampleFile));
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
}
