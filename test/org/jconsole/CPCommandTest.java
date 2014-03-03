package org.jconsole;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class CPCommandTest {

	static CPCommand CpCom = new CPCommand();
	static String curDir = System.getProperty("user.dir");
	
	@Before
	public void setup(){
		JConsole jcon=JConsole.instance();
		jcon.setCurrentDir(curDir);
		CpCom.setConsole(jcon);
	}
	
	@Test
	public void CopytoCurrentDir()
	{
		try {
			String srcDir = curDir + "/testResource/testDoc.txt";
			String [] args1 = {srcDir, "."};
			CpCom.execute(args1);
			String destfile = args1[0].substring(args1[0].lastIndexOf("/"));
			String destPath = curDir + destfile; 
			File f = new File(destPath);
			if(!(f.exists())) { 
				fail("File not found");
			}
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
	}
	
	@Test
	public void CopyMaskedFile()
	{
		try {
			File fd = new File("./testResource/testDoc2.txt");
				if(fd.exists()){
					fd.delete();
					}
			String [] args1 = {"*stDoc2.txt", "./testResource"};
			CpCom.execute(args1);
			String destPath = curDir +  "/testResource/testDoc2.txt"; 
			File f = new File(destPath);
			if(!(f.exists())) { 
				fail("File not found");
			}
			}catch (CommandFailedException e) {
			e.printStackTrace();
			fail("Exception was thrown");
		}
	}

		@Test
		public void NullArg()
		{
		try {
			String [] args1 = new String[0];
			CpCom.execute(args1);
			fail("Exception not thrown");
			}catch (CommandFailedException e) {
			e.printStackTrace();
		}
	}
}
