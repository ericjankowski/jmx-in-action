package com.programeric.java.jmx.configuration;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class HelloWorldSetup {
	public HelloWorldSetup() {
		try {
			MBeanServerConnection client = RMIClientFactory.getClient();
			ObjectName helloWorldName = new ObjectName("JMXAgent:name=helloWorld");
			client.createMBean("com.programeric.java.jmx.HelloWorld", helloWorldName);
			client.invoke(helloWorldName, "printGreeting", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HelloWorldSetup setup = new HelloWorldSetup();
	}
}
