package org.jconsole;

import java.util.*;

class HelpCommand extends ConsoleCommand {
	private static final String NAME = "help";
	private static final String DESC = "used to provide help information";
	private static final String HELP = "- help [command-name optional]";

	public HelpCommand() {
		super(NAME, DESC, HELP);
	}

	

	public void execute(String[] args) throws CommandFailedException {
		if (args == null || args.length == 0) {
			System.out.println("The following commands are installed:");
			List l = super.getAllCommandsList();
			for (Iterator i = l.iterator(); i.hasNext(); ) {
				ConsoleCommand cmd = (ConsoleCommand) i.next();
				System.out.println(cmd.getName() + "\t- " + cmd.getShortDescription());
			}
			System.out.println("");
			return;
		}

		ConsoleCommand cmd = super.getCommand(args[0]);
		System.out.println("Help topic for - " + cmd.getName());
		System.out.println(cmd.getHelp());
		System.out.println("");
	}

	public void initialize() throws Exception {}
}

