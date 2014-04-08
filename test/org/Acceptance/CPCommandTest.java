package org.jconsole;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class CPCommandTest {

	static CPCommand CpCom = new CPCommand();
	static CDCommand cdCom = new CDCommand();
	static LSCommand LSCom = new LSCommand();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


	static String curDir = System.getProperty("user.dir");
	JConsole jcon;
	
	@Before
	public void setup(){
		 jcon=JConsole.instance();
		jcon.setCurrentDir(curDir);
		CpCom.setConsole(jcon);
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

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
		
		@Test
		public void CopyLSDir()
		{
			try {
				String srcDir = curDir + "/testResource/testDoc.txt";
				String destDir="C:\\Users\\Rahul\\Desktop\\Zillow";
				String [] args1 = {srcDir,destDir };
				CpCom.execute(args1);
				
				//changing the Directory to Zillow
				
				String cdcur="C:\\Users\\Rahul\\Desktop\\Zillow";
				jcon.setCurrentDir(cdcur);
				cdCom.setConsole(jcon);
				
				String [] args2 = {cdcur};
				cdCom.execute(args2);
				File newFileDir = cdCom.getCurrentDir();
				String newDir = newFileDir.toString(); 
				
				//List all the files
				String [] args3 = new String[0];

				jcon.setCurrentDir(cdcur);
				LSCom.setConsole(jcon);
				LSCom.execute(args3);
				boolean condition=false;
				String output=outContent.toString();
				if(output.contains("testDoc.txt"))
					condition=true;
				
				Assert.assertTrue(condition);
				}catch (CommandFailedException e) {
				e.printStackTrace();
				fail("Exception was thrown");
			}
		}
}
