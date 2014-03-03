package org.jconsole;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PWDCommandTest {

	static PWDCommand pcom = new PWDCommand();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	static String myDir = System.getProperty("user.dir");
	
	@Before
	public void setup(){
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		JConsole jcon=JConsole.instance();
		jcon.setCurrentDir(myDir);
		pcom.setConsole(jcon);
	}
	
	@Test
	public void CurDir()
	{
		try {
			String [] args1=null;
			pcom.execute(args1);
			String output=outContent.toString();
			Assert.assertTrue(output.contains(myDir));
			} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
}
