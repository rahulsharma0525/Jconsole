package org.jconsole;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CDCommandTest {

	static CDCommand cdCom = new CDCommand();
	static String curDir = System.getProperty("user.dir");
	
	
	@Before
	public void setup(){
		JConsole jcon=JConsole.instance();
		jcon.setCurrentDir(curDir);
		cdCom.setConsole(jcon);
	}

	@Test //no arguments to cd
	public void TestExecuteNullInput()
	{
		try {
			String [] args1 = {""};
			cdCom.execute(args1);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString();    
			Assert.assertEquals(curDir,newDir);
			
		} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
	}
	
	
	@Test //changing dir to src
	public void TestExecuteValidInput()
	{
		try {
			String [] args1 = {"src"};
			cdCom.execute(args1);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString();
			String expDir = curDir + "/src";
			Assert.assertEquals(expDir,newDir);
			
		} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
	}
	
	@Test //move one level up CD..
	public void TestExecuteOneLevelUp()
	{
		try {
			String [] args1 = {".."};
			cdCom.execute(args1);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString();
			String expDir = curDir.substring(0, curDir.lastIndexOf("/"));
			Assert.assertEquals(expDir,newDir);
			
		} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
	}
	
	@Test //current dir cd .
	public void TestExecuteCurDirInput()
	{
		try {
			String [] args1 = {"."};
			cdCom.execute(args1);
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString();
			String expDir = curDir;
			Assert.assertEquals(expDir,newDir);
			
		} catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
	}
	
	@Test //directory does not exist
	public void TestExecuteInvalidInput()
	{
		try {
			String [] args1 = {"psedo"};
			cdCom.execute(args1);
			fail("Exception was not thrown");
			} 
			catch (CommandFailedException e) {
			e.printStackTrace();
			File newFileDir = cdCom.getCurrentDir();
			String newDir = newFileDir.toString();
			String expDir = curDir;
			Assert.assertEquals(expDir,newDir);
			
		}
	}
}
