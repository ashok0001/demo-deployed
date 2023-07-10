package Exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	
	private String error ;
	private String details;
	private LocalDateTime timeStamp;
	public ErrorDetails(String error, String details, LocalDateTime timeStamp) {
		super();
		this.error = error;
		this.details = details;
		this.timeStamp = timeStamp;
	}
	
	

}
