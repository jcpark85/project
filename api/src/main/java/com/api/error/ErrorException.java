package com.api.error;

import lombok.Data;

@Data
public class ErrorException extends RuntimeException{
	public final static int BAD_REQUEST = 400;
    public final static int INTERNAL_SERVER_ERROR = 500;

    private final int code;
    private final String message;
}
