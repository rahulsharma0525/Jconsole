package org.jconsole;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LSCommandTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	static CDCommand cdCom = new CDCommand();

	static LSCommand LSCom = new LSCommand();
	static String curDir = System.getProperty("user.dir");
	JConsole jcon;
	
	
	@Before
	public void setUpStreams() {
	System.setOut(new PrintStream(outContent));
	System.setErr(new PrintStream(errContent));
	jcon=JConsole.instance();
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
	
	//Added by Rahul
	@Test
	public void LSComCountTest()
	{
		try {
			String [] args1 = new String[0];
			LSCom.execute(args1);
			String output=outContent.toString();
			int count=output.length();
			System.out.println("Count :"+count);
		//	boolean condition=(count==1704);
			Assert.assertEquals(count,1704);
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
	@Test
	public void LSComFalseTest()
	{
		try {
			String [] args1 = new String[0];
			LSCom.execute(args1);
			String output=outContent.toString();
			int count=output.length();
			System.out.println("Count :"+count);
			boolean condition=output.contains("Rahul.xml");
			Assert.assertFalse(condition);
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
	
	
	@Test
	public void LSComCDTest()
	{
		try {
			
			
			String cdcur="C:\\Users\\Rahul\\Desktop";
			jcon.setCurrentDir(cdcur);
			cdCom.setConsole(jcon);
			
			String [] args2 = {cdcur};
			cdCom.execute(args2);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString(); 
		//	boolean cond=newDir.equals("C:\\Users\\Rahul\\Desktop");
			
			
			String [] args1 = new String[0];
			LSCom.execute(args1);
			String output=outContent.toString();
			int count=output.length();
			System.out.println("Count :"+count);
			boolean condition=output.contains("Maven");
			Assert.assertTrue(condition);
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
	
	
	@Test
	public void LSComCDCountTest()
	{
		try {
			
			
			String cdcur="C:\\Users\\Rahul\\Desktop";
			jcon.setCurrentDir(cdcur);
			cdCom.setConsole(jcon);
			
			String [] args2 = {cdcur};
			cdCom.execute(args2);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString(); 
		//	boolean cond=newDir.equals("C:\\Users\\Rahul\\Desktop");
			
			
			String [] args1 = new String[0];
			LSCom.execute(args1);
			String output=outContent.toString();
			int count=output.length();
			System.out.println("Count :"+count);
			Assert.assertEquals(count, 2982);
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
		outContent.reset();
	}
	
	//End of Addition by Rahul
}
