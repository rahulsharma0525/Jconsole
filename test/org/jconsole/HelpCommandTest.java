package org.jconsole;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HelpCommandTest {
	
	static HelpCommand hcom = new HelpCommand();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	static String myDir = System.getProperty("user.dir");
	
	@Before
	public void setup(){
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		JConsole jcon=JConsole.instance();
		jcon.setCurrentDir(myDir);
		hcom.setConsole(jcon);
	}
	
	@Test
	public void heplDisplay()
	{
		try {
			String [] args1={"cp"};
			System.out.println("");
			hcom.execute(args1);
			String output=outContent.toString();
			Assert.assertTrue(output.contains("Help"));
			} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
}
