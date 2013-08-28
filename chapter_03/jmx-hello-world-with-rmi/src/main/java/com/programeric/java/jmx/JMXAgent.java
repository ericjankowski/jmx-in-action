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
	
	protected void startHTMLAdapter(){
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
	}
	
	public static void main(String [] args){
		System.out.println("JMXAgent is running");
		JMXAgent agent = new JMXAgent();
	}
}
