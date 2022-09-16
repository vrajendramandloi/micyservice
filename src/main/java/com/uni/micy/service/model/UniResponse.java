package com.uni.micy.service.model;

public class UniResponse {
	private String status;
	private Object value;
	
	public UniResponse(String status, Object value) {
		this.status = status;
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "UniResponse [status=" + status + ", value=" + value + "]";
	}
}
