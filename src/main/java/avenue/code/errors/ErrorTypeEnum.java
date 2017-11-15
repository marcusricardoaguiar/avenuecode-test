package avenue.code.errors;

public enum ErrorTypeEnum {

	/*
	 * The types of errors the API can return.
	 */
	
	INVALID_PARAMETER(1),
	INVALID_FIELD(2),
	NOT_FOUND(3),
	INTEGRITY_VIOLATION(4);

	int code;

	ErrorTypeEnum(int value) {
		code = value;
	}
}
