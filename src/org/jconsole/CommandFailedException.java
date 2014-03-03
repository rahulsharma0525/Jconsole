package org.jconsole;

import java.io.*;


public class CommandFailedException extends Exception {
	private Throwable m_embeddedException;
	
	/** Creates new CommandFailedException */
	public CommandFailedException() {
		super();
	}

	public CommandFailedException(String m) {
		super(m);
	}
	
	public CommandFailedException(String m, Throwable t) {
		super(m);
		m_embeddedException = t;
	}

	public void printStackTrace() {
		if (m_embeddedException == null) {
			super.printStackTrace();
			return;
		}
		
		System.err.println(this.getClass().getName() + ": " + this.getMessage());
		m_embeddedException.printStackTrace();
	}

	public void printStackTrace(PrintStream s) {
		if (m_embeddedException == null) {
			super.printStackTrace(s);
			return;
		}
		
		s.println(this.getClass().getName() + ": " + this.getMessage());
		m_embeddedException.printStackTrace(s);	
	}
	
	public void printStackTrace(PrintWriter s) {
		if (m_embeddedException == null) {
			super.printStackTrace(s);
			return;
		}
		
		s.println(this.getClass().getName() + ": " + this.getMessage());
		m_embeddedException.printStackTrace(s);	
	}	
}