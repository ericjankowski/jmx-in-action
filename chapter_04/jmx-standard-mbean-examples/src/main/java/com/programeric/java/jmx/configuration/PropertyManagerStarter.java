package com.programeric.java.jmx.configuration;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class PropertyManagerStarter {
	public PropertyManagerStarter(){
		try{
			MBeanServerConnection client = RMIClientFactory.getClient();
			ObjectName propertyName = new ObjectName("JMXAgent:name=property");
			client.createMBean("com.programeric.java.jmx.PropertyManager", propertyName);
		}catch(Exception e){
			System.err.println("Error creating property manager MBean: " + e.getMessage());
		}
	}
	
	public static void main(String args[]){
		PropertyManagerStarter starter = new PropertyManagerStarter();
	}
}
