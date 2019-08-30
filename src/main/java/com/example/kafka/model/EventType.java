package com.example.kafka.model;

public enum EventType {
	
	   CUSTOMEREVENT("Customer Event"),
	   GREETINGEVENT("Greeting Event");
		
		private String eventType;
	
		EventType(String eventType) {
	        this.eventType = eventType;
	    }
	
	    public String eventType() {
	        return eventType;
	    }


}
