package com.example.kafka.model;

public class CustomerEvent {

    private long id;
    private String name;
    private String creationDate;
	private String eventKind;
	
	public CustomerEvent() {
		super();
	}
	public CustomerEvent(long id, String name, String creationDate, String eventKind) {
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.eventKind = eventKind;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public String getEventKind() {
		return eventKind;
	}
	
	@Override
	public String toString() {
		
		return id + ", " + name + ", "+ creationDate + ", "+ eventKind;
	}
	
	
	
}
	

    