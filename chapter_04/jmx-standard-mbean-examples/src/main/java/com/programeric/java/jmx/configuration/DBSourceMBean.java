package com.programeric.java.jmx.configuration;

import java.sql.Connection;

public interface DBSourceMBean {
	void resetDataSource(String name);
	void setAutoCommit(boolean commit);
	boolean getAutoCommit();
	Connection getConnection();
}
