package in.himanshugawari.reddit.exceptions;

public class SpringRedditException extends RuntimeException {

	private static final long serialVersionUID = -1787625639846954940L;

	public SpringRedditException(String s, Exception e) {
		super(s, e);
	}

	public SpringRedditException(String e) {
		super(e);
	}
}
