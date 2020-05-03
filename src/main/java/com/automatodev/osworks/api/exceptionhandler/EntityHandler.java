package com.automatodev.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class EntityHandler {
	private Integer status;
	private LocalDateTime time;
	private String titleString;
	private List<FieldHndler> fieldHandlerLiset;
	
	
	public static class FieldHndler {
		private String fieldString;
		private String messString;
		
		
		public FieldHndler(String fieldString, String messString) {
			this.fieldString = fieldString;
			this.messString = messString;
		}
		public String getFieldString() {
			return fieldString;
		}
		public void setFieldString(String fieldString) {
			this.fieldString = fieldString;
		}
		public String getMessString() {
			return messString;
		}
		public void setMessString(String messString) {
			this.messString = messString;
		}
		
		
	}
	
	
	public List<FieldHndler> getFieldHandlerLiset() {
		return fieldHandlerLiset;
	}
	public void setFieldHandlerLiset(List<FieldHndler> fieldHandlerLiset) {
		this.fieldHandlerLiset = fieldHandlerLiset;
	}
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
