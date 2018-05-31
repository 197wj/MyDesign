package com.design.service.exception;

/**
 * 农作物已存在异常
 * @author 王静
 *
 */
public class CropExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CropExistException() {
	}

	public CropExistException(String message) {
		super(message);
	}

	public CropExistException(Throwable cause) {
		super(cause);
	}

	public CropExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CropExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
