package in.himanshugawari.reddit.exceptions;

public class SpringRedditException extends RuntimeException {

	private static final long serialVersionUID = -1787625639846954940L;

	public SpringRedditException(String string, Exception e) {
		super(string, e);
	}

	public SpringRedditException(String string) {
		super(string);
	}
}
