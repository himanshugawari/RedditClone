package in.himanshugawari.reddit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.himanshugawari.reddit.dto.CommentsDto;
import in.himanshugawari.reddit.exceptions.PostNotFoundException;
import in.himanshugawari.reddit.mapper.CommentMapper;
import in.himanshugawari.reddit.model.Comment;
import in.himanshugawari.reddit.model.NotificationEmail;
import in.himanshugawari.reddit.model.Post;
import in.himanshugawari.reddit.model.User;
import in.himanshugawari.reddit.repository.CommentRepository;
import in.himanshugawari.reddit.repository.PostRepository;
import in.himanshugawari.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

	private static final String POST_URL = "";
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final AuthService authService;
	private final CommentMapper commentMapper;
	private final CommentRepository commentRepository;
	private final MailContentBuilder mailContentBuilder;
	private final MailService mailService;

	public CommentsDto save(CommentsDto commentsDto) {
		Post post = postRepository.findById(commentsDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
		Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
		commentRepository.save(comment);

		String message = mailContentBuilder
				.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
		sendCommentNotification(message, post.getUser());

		return commentMapper.mapToDto(comment);
	}

	/*
	 * public void save(CommentsDto commentsDto) { Post post =
	 * postRepository.findById(commentsDto.getPostId()) .orElseThrow(() -> new
	 * PostNotFoundException(commentsDto.getPostId().toString())); Comment comment =
	 * commentMapper.map(commentsDto, post, authService.getCurrentUser());
	 * commentRepository.save(comment);
	 * 
	 * String message = mailContentBuilder .build(post.getUser().getUsername() +
	 * " posted a comment on your post." + POST_URL);
	 * sendCommentNotification(message, post.getUser()); }
	 */

	private void sendCommentNotification(String message, User user) {
		mailService.sendMail(
				new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
	}

	public List<CommentsDto> getAllCommentsForPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
		return commentRepository.findByPost(post).stream().map(commentMapper::mapToDto).collect(Collectors.toList());
	}

	public List<CommentsDto> getAllCommentsForUser(String userName) {
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
		return commentRepository.findAllByUser(user).stream().map(commentMapper::mapToDto).collect(Collectors.toList());
	}

}
