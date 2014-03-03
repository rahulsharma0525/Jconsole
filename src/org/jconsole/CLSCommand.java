package org.jconsole;

import java.io.*;

/*
* used to clear the screen
*/
class CLSCommand extends ConsoleCommand {
	private static final String NAME = "cls";
	private static final String DESC = "used to clear the screen.";
	private static final String HELP = "- cls";

  public CLSCommand() {
    super(NAME, DESC, HELP);
  }

	public void execute(String[] args) throws CommandFailedException {
    for (int x=0; x < 50; x++) {
      Console.out.println("");
    }
  }

	protected void initialize() throws Exception {
  }


}