package com.example.kafka.model;

public class GreetingEvent {

    private long id;
    private String content;
    private String creationDate;
	private String eventKind;
	
	public GreetingEvent() {
		super();
	}
	
	public GreetingEvent(long id, String content, String creationDate, String eventKind) {
		super();
		this.id = id;
		this.content = content;
		this.creationDate = creationDate;
		this.eventKind = eventKind;
	}
	
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public String getEventKind() {
		return eventKind;
	}

	@Override
	public String toString() {
		
		return id + ", " + content + ", "+ creationDate + ", "+ eventKind;
	}
	
}
	

    