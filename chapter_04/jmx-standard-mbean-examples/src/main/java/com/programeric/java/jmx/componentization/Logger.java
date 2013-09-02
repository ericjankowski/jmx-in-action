package com.programeric.java.jmx.componentization;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Logger implements LoggerMBean, MBeanRegistration{
	public static final int ALL 	= 3;
	public static final int ERRORS 	= 2;
	public static final int NONE	= 1;
	
	private PrintWriter out = null;
	private int logLevel = Logger.ALL;
	private MBeanServer server;
	
	public Logger(){
		try{
			out = new PrintWriter(new FileOutputStream("record.log"));
		}catch(Exception e){
			System.err.println("Error creating PrintWriter during Logger construction: " + e.getMessage());
		}
	}
	
	public void setLogLevel(int logLevel){
		this.logLevel = logLevel;
	}
	
	public int getLogLevel(){
		return logLevel;
	}
	
	public void writeLog(String message, int type){
		try{
			if(type <= logLevel){
				out.println(message);
			}
		}catch(Exception e){
			System.err.println("Error writing to log: " + e.getMessage());
		}
	}
	
	public String retrieveLog(int linesback){
		return "No log data available.  This method is not yet implemented.";
	}

	public ObjectName preRegister(MBeanServer server, ObjectName name){
		this.server = server;
		try{
			ObjectName propertyManagerName = new ObjectName("JMXAgent:name=properties");
			Object[] parameters = {"logLevel"};
			String[] signature = {"java.lang.String"};
			String value = (String)server.invoke(propertyManagerName, "getProperty", parameters, signature);
			logLevel = Integer.parseInt(value);
		}catch(Exception e){
			System.err.println("Error reading logLevel from properties: " + e.getMessage());
		}
		return name;  //This return statement seems stupid.  It has nothing to do with the meaningful behavior of the method.
	}
	
	public void postRegister(Boolean registrationSuccess){}
	public void preDeregister(){}
	public void postDeregister(){}
	
	
}
