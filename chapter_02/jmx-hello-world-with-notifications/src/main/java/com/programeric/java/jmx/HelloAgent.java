package com.programeric.java.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent implements NotificationListener{
	private MBeanServer mbs = null;
	
	public HelloAgent (){
		mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
		
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		HelloWorld hw = new HelloWorld();
		ObjectName adapterName = null;
		ObjectName helloWorldName = null;
		
		try{
			helloWorldName = new ObjectName("HelloAgent:name=helloWorld1");
			mbs.registerMBean(hw, helloWorldName);
			
			adapterName = new ObjectName("HelloAgent:name=htmladapter,port=9092");
			adapter.setPort(9092);
			mbs.registerMBean(adapter, adapterName);
			adapter.start();
			hw.addNotificationListener(this, null, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		System.out.println("Hello agent is running");
		HelloAgent agent = new HelloAgent();
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println("Receiving notification...");
		System.out.println(notification.getType());
		System.out.println(notification.getMessage());
		
	}
}
