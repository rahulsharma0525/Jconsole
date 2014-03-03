package org.jconsole;

import java.io.*;

/**
 * Insert the type's description here.
 * Creation date: (10/17/2001 11:11:39 AM)
 * @author: Saravanan Thangaraju
 */
public class SetClassPath {
/**
 * SetClassPath constructor comment.
 */
public SetClassPath() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2001 11:11:47 AM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) throws Exception {

	String rootsHome = System.getProperty("roots.home");
	if(rootsHome==null || rootsHome.length()<2)
	{
		rootsHome = new File("s.log").getAbsoluteFile().getParent();		
	}
	System.setProperty("roots.home",rootsHome);
	String libDirName = "lib";
	File libDir  = new File(rootsHome+File.separator+libDirName);
	File[] contents = libDir.listFiles();
	File aFile = new File("AppendCp.bat");
	if(aFile.exists())
	{
		aFile.delete();
	}
	
	FileOutputStream fos = new FileOutputStream("AppendCp.bat");
	fos.write(("set roots.home="+rootsHome).getBytes());
	fos.write("set _cp=%classpath%\n".getBytes());
	for(int i=0;i<contents.length;i++)
	{
		if(contents[i].isFile() && contents[i].getPath().endsWith(".jar"))
		{
			fos.write(("set _cp=%_cp%;"+contents[i].getAbsolutePath()+"\n").getBytes());
		}
		
	}

	fos.write("set classpath=%_cp%\n".getBytes());
	fos.write("java org.jconsole.JConsole".getBytes());
	
	}
}