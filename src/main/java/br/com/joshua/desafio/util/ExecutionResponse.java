package br.com.joshua.desafio.util;


public class ExecutionResponse {

	public enum Status {
		SUCCESS, FAIL
	}

	private Status status;

	private String message;

	private Object result;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
