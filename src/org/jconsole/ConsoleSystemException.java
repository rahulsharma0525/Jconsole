/*
 * ConsoleSystemException.java
 *
 * Created on February 9, 2002, 2:29 PM
 */

package org.jconsole;
import java.io.*;
/**
 *
 * @author  Niraj Juneja
 * @version 
 */
public class ConsoleSystemException extends java.lang.RuntimeException {

    private Throwable m_embeddedException;
	/** Creates new ConsoleSystemException */
	public ConsoleSystemException() {
	super();
	}

	public ConsoleSystemException(String str){
	super(str);
	}
    
	public ConsoleSystemException(String str , Throwable t)
	{
		super(str);
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


