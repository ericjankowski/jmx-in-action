package com.programeric.java.jmx;

import java.util.Enumeration;

public interface PropertyManagerMBean {
	void setProperty(String key, String value);
	String getProperty(String key);
	Enumeration<Object> keys();
	void setSource(String path);
}
