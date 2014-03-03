package org.jconsole;

import java.io.*;
import java.util.*;


class HistoryCommand extends ConsoleCommand {
	private static final String NAME = "hist";
	private static final String DESC = "used to display the command history.";
	private static final String HELP = "- hist [#]";

	public HistoryCommand() {
		super(NAME, DESC, HELP);
	}

	public void execute(String[] args) throws CommandFailedException {
		List cmdList = super.getCommandHistory();

		if ((cmdList == null) || (cmdList.size() == 0)) {
			Console.out.println("No History...");
			return;
		} 
		
		int histCount = cmdList.size();
		if (args.length > 0) {
			try {
				histCount = Integer.parseInt(args[0]);
			} catch (Exception e) {
			super.throwCmdFailed("argument not a number: " + args[0], e);
			}
		}
		for (int i = (cmdList.size() - histCount) + 1 ; i <= cmdList.size(); i++ ) {
			Console.out.println( Utilities.Pad("!"+i, 5, Utilities.PAD_RIGHT ) +
									(HistoryEntry)cmdList.get(i-1) );
		}

	}

	protected void initialize() throws Exception {
	}

	
}