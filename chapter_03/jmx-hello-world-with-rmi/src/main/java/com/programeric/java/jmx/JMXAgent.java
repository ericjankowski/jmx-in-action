package com.programeric.java.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JMXAgent {
	private MBeanServer server = null;
	
	public JMXAgent(){
		server = MBeanServerFactory.createMBeanServer("JMXAgent");
		
		startHTMLAdapter();
		startRMIConnector();
		
	}
	
	private void startHTMLAdapter(){
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		ObjectName adapterName = null;
		
		try{
			adapter.setPort(9092);
			adapterName = new ObjectName("JMXAgent:name=html,port=9092");
			server.registerMBean(adapter, adapterName);
			adapter.start();
		}catch(Exception e){
			System.err.println("Error Starting HTML Adapter for JMXAgent: " + e.getMessage());
		}
	}
	
	private void startRMIConnector(){
		try{
			JMXServiceURL jmxServiceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/server");
			JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceUrl, null, server);
			connectorServer.start();
		}catch(Exception e){
			System.err.println("Error Starting RMI Connector for JMXAgent: " + e.getMessage());
		}
	}
	
	public static void main(String [] args){
		System.out.println("JMXAgent is running...");
		JMXAgent agent = new JMXAgent();
		
	}
}
