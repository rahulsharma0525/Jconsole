package org.jconsole;

import java.io.*;


class MKDIRCommand extends ConsoleCommand {
	private static final String NAME = "mkdir";
	private static final String DESC = "used to create a new directory.";
	private static final String HELP = "mkdir new-dir-name";

	public MKDIRCommand() {
		super(NAME, DESC, HELP);
	}

	public void execute(String[] args) throws CommandFailedException {
		int count = (args == null) ? 0 : args.length;
		if (count == 0 ) {
			super.throwCmdFailed(HELP);
		}

		String name=args[0];
		File newDir = new File( super.getCurrentDir(), name );
		if( !newDir.mkdir() ) {
			super.throwCmdFailed("mkdir failed");
		}
	}

	protected void initialize() throws Exception {
	}

	
}