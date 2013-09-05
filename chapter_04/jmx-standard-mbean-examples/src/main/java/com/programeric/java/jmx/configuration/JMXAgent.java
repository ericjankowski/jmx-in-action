package com.programeric.java.jmx.configuration;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class JMXAgent {
	private MBeanServer server = null;
	
	public JMXAgent(){
		server = MBeanServerFactory.createMBeanServer("JMXAgent");
		startRMIConnector();
	}
	
	private void startRMIConnector(){
		try{
			JMXServiceURL jmxServiceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/server");
			JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceUrl, null, server);
			connectorServer.start();
		}catch(Exception e){
			System.err.println("Did you run: $JAVA_HOME/bin/rmiregistry 2099 &");
			System.err.println("Error Starting RMI Connector for JMXAgent: " + e.getMessage());
		}
	}
	
	public static void main(String [] args){
		System.out.println("JMXAgent is running...");
		JMXAgent agent = new JMXAgent();
	}
}
