package org.jconsole;

class QuitCommand extends ConsoleCommand {
	private static final String NAME = "quit";
	private static final String DESC = "used to exit the console, same as exit";
	private static final String HELP = "- quit";
	
	public QuitCommand() {
		super(NAME, DESC, HELP);
	}
	
	
	
	public void execute(String[] args) throws CommandFailedException {
		Console.out.println("Roots Console done.");
		System.exit(0);
	}
	public void initialize() throws Exception {}
}
