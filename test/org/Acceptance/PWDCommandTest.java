package org.jconsole;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PWDCommandTest {

	static PWDCommand pcom = new PWDCommand();
	static CDCommand cdCom = new CDCommand();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	static String myDir = System.getProperty("user.dir");
	static String curDir = System.getProperty("user.dir");
	JConsole jcon;
	
	@Before
	public void setup(){
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		 jcon=JConsole.instance();
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
	
//Added By Rahul	
	@Test
	public void CDCurDir()
	{
		try {
			String [] args1=null;
			pcom.execute(args1);
			String output=outContent.toString();
			Assert.assertTrue(output.contains(myDir));
			String cdcur="C:\\Users\\Rahul\\Desktop\\MSC Proj\\Acceptance\\JConsole\\src";
			jcon.setCurrentDir(curDir);
			cdCom.setConsole(jcon);
			
			String [] args2 = {"src"};
			cdCom.execute(args2);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString(); 
			boolean cond=cdcur.equals(newDir);
			Assert.assertTrue(cond);
			
			
			
			} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
	//End of addition by Rahul
	
}
