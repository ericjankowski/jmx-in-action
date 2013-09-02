package com.programeric.java.jmx.configuration;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBSource implements DBSourceMBean{
	private DataSource dataSource = null;
	private boolean autoCommit = false;
	
	public DBSource(String JNDIName){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup(JNDIName);
		}catch(Exception e){
			System.err.println("Error retreiving data source from context: " + e.getMessage());
		}
	}
	
	public void resetDataSource(String name){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup(name);
		}catch(Exception e){
			System.err.println("Error resetting data source: " + e.getMessage());
		}
	}
	
	public Connection getConnection(){
		Connection connection = null;
		try{
			connection = dataSource.getConnection();
			connection.setAutoCommit(autoCommit);
			return connection;
		}catch(Exception e){
			System.err.println("Error getting connection: " + e.getMessage());
			return null;
		}
	}
	
	public boolean getAutoCommit(){
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit){
		this.autoCommit = autoCommit;
	}
}
