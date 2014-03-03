package org.jconsole;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class RMDIRCommandTest {
	static String myDir = System.getProperty("user.dir");
	RMDIRCommand rmCom = new RMDIRCommand();
	JConsole jcon=JConsole.instance();
	
	@Before
	public void setup() throws Exception{
		jcon.setCurrentDir(myDir);
		rmCom.setConsole(jcon);
		SetClassPath.main(null);
	}
	
	@Test
	public void TestExecuteNullInput()			//mkdir given with null argument 
	{
		RMDIRCommand rm =new RMDIRCommand();
		String [] args1= null;
		try {
			rm.execute(args1);
			fail("Exception was not thrown");
		} catch (CommandFailedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestExecuteValidInput()			 
	{
		File f  = new File(myDir + "/RMTest");
		String [] args1= {"RMTest"};
		try {
			if(!f.exists()){
				f.mkdir();
			}
			rmCom.execute(args1);
			if(f.exists()){
				fail("Exception was not thrown");
			}
		    } catch (CommandFailedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestRecursiveDel()			
	{
		File f  = new File(myDir + "/RMTest/level2/level3");
		String [] args1= {"RMTest"};
		try {
			if(!f.exists()){
				f.mkdirs();
			}
			rmCom.execute(args1);
			if(f.exists()){
				fail("Exception was not thrown");
			}
		    } catch (CommandFailedException e) {
			e.printStackTrace();
		}
	}
}
