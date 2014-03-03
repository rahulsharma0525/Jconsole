package org.jconsole;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;



class LSCommand extends ConsoleCommand {
	private static final String NAME = "ls";
	private static final String HELP = "used to display the directory listing. The only wildcard allowed is '*' ";
	private static final String DESC = "used to display the directory listing.";

	// keeps the last list of the directory
	private HashMap m_lastLSMap = new HashMap();

	// the date formate
	private SimpleDateFormat m_dateFormatter = new SimpleDateFormat("mm/dd/yy hh:mm:ss");

	private String m_filterStartsWith = null;
	private String m_filterEndsWith = null;

	// the constructor
	public LSCommand() {
		super(NAME, DESC, HELP);
	}

	// the execute logic
	public void execute(String[] args) throws CommandFailedException {
		boolean putInMap = true;
		if (args.length == 2 && args[1].equals("-nomemory")) {
			putInMap = false;
		}
		else {
			m_lastLSMap.clear();
		}

		boolean echo = (args.length == 2 && args[1].equals("-noecho"))? false:true;


		List lsList = new ArrayList();


		File currDir = super.getCurrentDir();

		String[] dirArray = null;
		if (args.length > 0) {
			if (args[0].indexOf("*") >= 0 ) {
				m_filterStartsWith = args[0].substring(0, args[0].indexOf("*") ) ;
				m_filterEndsWith = args[0].substring(args[0].indexOf("*") + 1 ) ;
				dirArray = currDir.list(new FileFilter());
			} else {
				super.throwCmdFailed("Invalid wildcard. The only wildcard allowed is '*'.");
			}
		} 
		else {
			dirArray = currDir.list();
		}

		if (dirArray != null) {
			for ( int arrayNo=0; arrayNo < dirArray.length; arrayNo++ ) {
				File currFile = new File(currDir.getAbsolutePath() +
													System.getProperty("file.separator")  + dirArray[arrayNo]);
				lsList.add(currFile);
			}

			Collections.sort(lsList, new FileComparator() );

			for ( int listNo=0; listNo < lsList.size(); listNo++ ) {
				File currFile = (File)lsList.get(listNo);

				if (putInMap) {
					m_lastLSMap.put(new Integer(listNo+1), currFile );
				}

				if (echo) {
					int fileLen = ((int)currFile.length()/1024) + (currFile.length()%1024==0?0:1) ;
					
					System.out.println(Utilities.Pad(listNo+1 + "", 5, Utilities.PAD_RIGHT) +  // fileNo
														Utilities.Pad( currFile.getName() , 30, Utilities.PAD_RIGHT) + // file name
														Utilities.Pad(currFile.isDirectory() ? "< DIR >" : fileLen + "KB", 15, Utilities.PAD_LEFT) + "  " + // <DIR>/size
														m_dateFormatter.format( new Date(currFile.lastModified()) ) ); // date
				}
			}
		}
	}

	protected void initialize() throws Exception {
	}

	protected Map getShareMap() {
		return m_lastLSMap;
	}

	class FileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return name.startsWith( m_filterStartsWith ) && name.endsWith( m_filterEndsWith );
		}
	}
}

class FileComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		int result = 0;

		File f1 = (File) o1;
		File f2 = (File) o2;

		if (f1 == f2) {
			result =  0;
		} else if (f1.isDirectory() && !f2.isDirectory()) {
			result = -1;
		} else if (!f1.isDirectory() && f2.isDirectory()) {
			result =  1;
		} else {
			result = f1.getName().compareTo(f2.getName());
		}
		return result;
	}
}


