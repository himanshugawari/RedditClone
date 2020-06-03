package in.himanshugawari.reddit.exceptions;

public class SubredditNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8747345804142725138L;

	public SubredditNotFoundException(String message) {
		super(message);
	}
}