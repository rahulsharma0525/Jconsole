package org.jconsole;

class ExitCommand extends ConsoleCommand {
	private static final String NAME = "exit";
	private static final String DESC = "used to exit the console, same as quit";
	private static final String HELP = "- exit";
	
	public ExitCommand() {
		super(NAME, DESC, HELP);
	}
	
	
	public void execute(String[] args) throws CommandFailedException {
		super.getCommand("quit").execute(null);
	}
	
	public void initialize() throws Exception {}
}
