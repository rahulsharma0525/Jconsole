
package org.jconsole;


public class CommandResultException extends CommandFailedException {
	private int m_code;
	private Object m_result;

	public CommandResultException(String m) {
		this(m, 0, null);
	}
	
	public CommandResultException(String m, int c) {
		this(m, c, null);
	}

	public CommandResultException(String m, Object res) {
		this(m, 0, res);
	}
	
	public CommandResultException(String m, int code, Object res) {
		super(m);
		m_code = code;
		m_result = res;
	}
	
	
}
