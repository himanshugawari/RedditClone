package in.himanshugawari.reddit.exceptions;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2322840150370151065L;

	public PostNotFoundException(String e) {
		super(e);
	}
}