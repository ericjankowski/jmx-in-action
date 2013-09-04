package com.programeric.java.jmx.notifications;

import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Polling extends NotificationBroadcasterSupport implements PollingMBean, Runnable{
	private boolean stop = true;
	private int index = 0;
	
	public Polling(){}
	
	public void start(){
		try{
			stop = false;
			Thread t = new Thread(this);
			t.start();
		}catch(Exception e){
			System.err.println("Error starting the Polling object: " + e.getMessage());
		}
	}
	
	public void stop(){
		stop = true;
	}
	
	public void run(){
		while(!stop){
			try{
				Thread.sleep(1000);
				System.out.println("Polling...");
			}catch(Exception e){
				System.err.println("Error sleeping: " + e.getMessage());
			}
			Notification notification = new Notification("com.programeric.java.jmx.notifications.PollingMBean.counter", this, index++);
			sendNotification(notification);
		}
	}
	
	public MBeanNotificationInfo[] getNotificationInfo(){
		String[] type = {"com.programeric.java.jmx.notifications.PollingMBean.counter"};
		MBeanNotificationInfo[] info = new MBeanNotificationInfo[1];
		info[0] = new MBeanNotificationInfo(type, "javax.management.Notification", "The Polling MBean counter");
		return info;
	}
}
