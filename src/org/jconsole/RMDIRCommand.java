package org.jconsole;

import java.io.*;


class RMDIRCommand extends ConsoleCommand {
	private static final String NAME = "rmdir";
	private static final String DESC = "used to remove a directory.";
	private static final String HELP = "rmdir dir-name";

	public RMDIRCommand() {
		super(NAME, DESC, HELP);
	}

	public void execute(String[] args) throws CommandFailedException {
		int count = (args == null) ? 0 : args.length;
		if (count == 0 ) {
			super.throwCmdFailed(HELP);
		}

		String name=args[0];
		File f = super.getCurrentDir();
		
		File theDir = new File( super.getCurrentDir(), name );

        // if the directory doesn't exist do nothing.
        if( !theDir.exists())
        {
          return;
        }

        // check if a given source is a file or a dir.
		if( !theDir.isDirectory())
        {
			super.throwCmdFailed("rmdir failed. '" + name +" is not a Directory.");
        }

        //remember the absolute path.
        String absPath = theDir.getAbsolutePath();
        // empty the content of the directory
        removeContent(theDir);
        // try to remove. if no succees throw an exception.
        if (!(new File(absPath)).delete())
        {
          super.throwCmdFailed("rmdir failed. Unable to remove "+absPath);
        }
	}

    /**
     * Recursively walks through a directory tree and removes its content
     * @param File - directory to remove a content from
     */
    private void removeContent(File file)
            throws CommandFailedException
    {
			String absPath = file.getAbsolutePath();
			
			if (file.isDirectory())
      {
        String[] dirItem = file.list();
        for (int i=0; i<dirItem.length; i++)
        {
          File tmpFile = new File(absPath, dirItem[i]);

          if (tmpFile.isFile())
          {
            if (!tmpFile.delete())
            {
              super.throwCmdFailed("rmdir failed. Unable to remove "+tmpFile.getName());
            }
          }
          else
          {
            removeContent(tmpFile);
            if (!tmpFile.delete())
            {
              super.throwCmdFailed("rmdir failed. Unable to remove "+tmpFile.getName());
            }
          }
        }
      }

    }



	protected void initialize() throws Exception {
	}

	protected boolean needsMQ() {
		return false;
	}
}