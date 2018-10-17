package pl.edu.pjatk.tau.labone.exception;

/**
 * @author s14646
 */
public class DuplicatedIdException extends RuntimeException {
	private static final long serialVersionUID = 1997753363232807009L;

	public DuplicatedIdException() {
	}

	public DuplicatedIdException(String message) {
		super(message);
	}

	public DuplicatedIdException(Throwable cause) {
		super(cause);
	}

	public DuplicatedIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedIdException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}