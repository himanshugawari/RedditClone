package in.himanshugawari.reddit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.himanshugawari.reddit.dto.PostRequest;
import in.himanshugawari.reddit.dto.PostResponse;
import in.himanshugawari.reddit.exceptions.PostNotFoundException;
import in.himanshugawari.reddit.exceptions.SubredditNotFoundException;
import in.himanshugawari.reddit.mapper.PostMapper;
import in.himanshugawari.reddit.model.Post;
import in.himanshugawari.reddit.model.Subreddit;
import in.himanshugawari.reddit.model.User;
import in.himanshugawari.reddit.repository.PostRepository;
import in.himanshugawari.reddit.repository.SubredditRepository;
import in.himanshugawari.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
//@Slf4j
@Transactional
public class PostService {

	private final PostRepository postRepository;
	private final SubredditRepository subredditRepository;
	private final UserRepository userRepository;
	private final AuthService authService;
	private final PostMapper postMapper;

	/*
	 * @javax.transaction.Transactional public void save(PostRequest postRequest) {
	 * Subreddit subreddit =
	 * subredditRepository.findByName(postRequest.getSubredditName())
	 * .orElseThrow(() -> new
	 * SubredditNotFoundException(postRequest.getSubredditName()));
	 * postRepository.save(postMapper.map(postRequest, subreddit,
	 * authService.getCurrentUser())); }
	 */

	@javax.transaction.Transactional
	public PostResponse save(PostRequest postRequest) {
		Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
				.orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
		Post savePost= postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
		return postMapper.mapToDto(savePost);
	}

	@Transactional(readOnly = true)
	public PostResponse getPost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
		return postMapper.mapToDto(post);
	}

	@Transactional(readOnly = true)
	public List<PostResponse> getAllPosts() {
		return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<PostResponse> getPostsBySubreddit(Long subredditId) {
		Subreddit subreddit = subredditRepository.findById(subredditId)
				.orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
		List<Post> posts = postRepository.findAllBySubreddit(subreddit);
		return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<PostResponse> getPostsByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}

}