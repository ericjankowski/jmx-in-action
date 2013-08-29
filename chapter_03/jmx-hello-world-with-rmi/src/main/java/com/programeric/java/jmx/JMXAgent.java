package com.programeric.java.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

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
		}catch (Exception e){
			System.out.println("Error Starting HTML Adapter for Agent");
		}
	}
	
	private void startRMIConnector(){
		RmiConnectorServer connectorserver = new RmiConnectorServer();
	}
	
	public static void main(String [] args){
		System.out.println("JMXAgent is running");
		JMXAgent agent = new JMXAgent();
	}
}
