package com.programeric.java.jmx.componentization;

public interface DataLayerMBean {
	boolean insterData(Object data);
	boolean updateData(Object data);
	boolean deleteData(Object data);
	boolean retrieveData(Object data);
}
