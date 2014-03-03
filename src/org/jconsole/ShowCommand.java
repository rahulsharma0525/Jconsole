package org.jconsole;

import java.io.*;


public class ShowCommand extends ConsoleCommand {
	private static final String NAME = "show";
	private static final String DESC = "to open the last ouput in IE";
	private static final String HELP = "opens the last output commands text in IE or the assigned application";
		
	public ShowCommand() {
		super(NAME, DESC, HELP);
	}
	
	
  protected void initialize() throws java.lang.Exception {}
  
	public void execute(String[] arr) throws CommandFailedException {
		File f = (arr.length == 0 ? ConsoleCommand.s_showFile : super.getFile(arr[0]));
		if (f == null || !f.exists() || !f.isFile()) {
		  super.throwCmdFailed("There is nothing to show.");
		}
		
		String fn = f.getAbsolutePath();
		try {
			if( System.getProperties().getProperty( "os.name" ).indexOf("9") > -1 ) {
				Runtime.getRuntime().exec( "start \"" + fn + "\"");
			}
			else {
				Runtime.getRuntime().exec( "cmd /C \"" + fn + "\"");
			}  
		}
		catch (Exception e) {
			e.printStackTrace();
		  super.throwCmdFailed("Failed to open file " + fn, e);
		}
  }
}