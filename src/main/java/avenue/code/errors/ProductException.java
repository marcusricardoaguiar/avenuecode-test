package avenue.code.errors;

import javax.validation.ValidationException;

public class ProductException extends ValidationException {
	
	/*
	 * Use ErrorTypeEnum to return structural errors to user.
	 */

	private static final long serialVersionUID = 1L;

	public ErrorTypeEnum code;

	public String message;
	
	public String fields;
	
	public ProductException(ErrorTypeEnum code, String message) {
		this.code = code;
		this.message = message;
		this.fields = "";
	}

	public ProductException(ErrorTypeEnum code, String message, String fields) {
		this.code = code;
		this.message = message;
		this.fields = fields;
	}

	public ErrorTypeEnum getCode() {
		return code;
	}

	public void setCode(ErrorTypeEnum code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	public String showError(){
		return "{\"Code\": \"" + code + "\", \"Message\": \"" + message + "\", \"Fields\": \"" + fields + "\"}";
	}
}
