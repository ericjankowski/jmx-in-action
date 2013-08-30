package com.programeric.java.jmx;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RMIClientFactory {
	public static MBeanServerConnection getClient() {
		try {
			JMXServiceURL jmxServiceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/server");
			JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceUrl, null);

			return jmxc.getMBeanServerConnection();
		} catch (Exception e){
			System.err.println("Error creating MBeanServerConnection: " + e.getMessage());
		}
		return null;
	}
}
