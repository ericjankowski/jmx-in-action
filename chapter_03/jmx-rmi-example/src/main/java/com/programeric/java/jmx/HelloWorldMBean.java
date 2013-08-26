package com.programeric.java.jmx;

public interface HelloWorldMBean {
	void setGreeting(String greeting);
	String getGreeting();
	void printGreeting();
}
