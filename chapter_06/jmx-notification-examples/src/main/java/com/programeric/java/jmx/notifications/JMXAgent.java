package com.programeric.java.jmx.notifications;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JMXAgent implements NotificationListener{
	private MBeanServer server = null;
	
	public JMXAgent(){
		server = MBeanServerFactory.createMBeanServer("JMXAgent");
		startHTMLAdapter();
		try{
			Polling polling = new Polling();
			ObjectName pollingName = new ObjectName("JMXAgent:name=polling");
			server.registerMBean(polling, pollingName);
			polling.addNotificationListener(this, null, null);
		}catch(Exception e){
			System.err.println("Error registering polling MBean: " + e.getMessage());
		}
	}
	
	private void startHTMLAdapter(){
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		ObjectName adapterName = null;
		
		try{
			
			adapterName = new ObjectName("JMXAgent:name=htmladapter,port=9092");
			adapter.setPort(9092);
			server.registerMBean(adapter, adapterName);
			adapter.start();
		}catch(Exception e){
			System.err.println("Error starting HTML Adapter: " + e.getMessage());
		}
	}
	
	public static void main(String [] args){
		System.out.println("JMXAgent is running...");
		JMXAgent agent = new JMXAgent();
	}

	public void handleNotification(Notification notification, Object handback) {
		System.out.println("Received a notification:: " + notification.getType() + " Sequence Number: " + notification.getSequenceNumber());
	}
}
