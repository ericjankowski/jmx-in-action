package com.programeric.java.jmx.componentization;

public interface LoggerMBean {
	void setLogLevel(int level);
	int getLogLevel();
	String retrieveLog(int linesBack);
	void writeLog(String message, int type);
}
