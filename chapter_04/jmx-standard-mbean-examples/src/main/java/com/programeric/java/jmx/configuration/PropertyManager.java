package com.programeric.java.jmx.configuration;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyManager implements PropertyManagerMBean {

	private Properties properties = null;
	
	public PropertyManager(String path){
		setSource(path);
	}
	
	public PropertyManager(){
		setSource( "default.properties");
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	public Enumeration<Object> keys() {
		return properties.keys();
	}

	public void setSource(String path) {
		try{
			properties = new Properties();
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
			fis.close();
		}catch(Exception e){
			System.err.println("Error loading properties file: " + e.getMessage());
		}
	}
}
