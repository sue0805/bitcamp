package guestbook.service;

public class InvalidMessagePasswordException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMessagePasswordException(String msg) {
		super(msg);
	}

	public InvalidMessagePasswordException() {}
}
