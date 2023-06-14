package pl.bgnat.pagination.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestValidationException extends RuntimeException {
	public static final String INVALID_REQUEST_S_FIELDS_CANNOT_BE_NULL = "Invalid request: %s. Fields cannot be null!";
	public RequestValidationException(String request) {
		super(String.format(INVALID_REQUEST_S_FIELDS_CANNOT_BE_NULL, request));
	}
}
