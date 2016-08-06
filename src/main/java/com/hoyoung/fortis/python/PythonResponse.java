package com.hoyoung.fortis.python;

import java.util.List;

public class PythonResponse {
	
	public static final int STATUS_FAILURE = -1;
	public static final int STATUS_SUCCESS = 0;
	
	private boolean status;
	private String message;
	private List<String> data;
	
	
	
	public boolean isStatus() {
		return status;
	}
	

	public void setStatus(boolean status) {
		this.status = status;
	}
	

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	

	public List<String> getData() {
		return data;
	}
	


	public void setData(List<String> data) {
		this.data = data;
	}
	


	@Override
	public String toString() {
		return "PythonResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
	
	
}
