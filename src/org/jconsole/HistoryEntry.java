package org.jconsole;




public class HistoryEntry {
  private String m_cmdString;
  private ConsoleCommand m_cmd;
  private String[] m_args;

  public HistoryEntry(String aCmdString, ConsoleCommand aCmd, String args[]) {
    m_cmdString = aCmdString;
    m_cmd = aCmd;
    m_args = args;
  }

  public ConsoleCommand getCommand() {
    return m_cmd;
  }

  public String[] getArgs() {
    return m_args;
  }

  public String toString() {
 	  return m_cmdString;
  }


}
