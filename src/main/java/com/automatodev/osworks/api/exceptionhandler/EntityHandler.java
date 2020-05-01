package com.automatodev.osworks.api.exceptionhandler;

import java.time.LocalDateTime;

public class EntityHandler {
	private Integer status;
	private LocalDateTime time;
	private String titleString;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getTitleString() {
		return titleString;
	}
	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}
	
	
}
