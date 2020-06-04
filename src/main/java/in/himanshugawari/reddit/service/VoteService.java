package in.himanshugawari.reddit.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.himanshugawari.reddit.dto.VoteDto;
import in.himanshugawari.reddit.exceptions.PostNotFoundException;
import in.himanshugawari.reddit.exceptions.SpringRedditException;
import in.himanshugawari.reddit.model.Post;
import in.himanshugawari.reddit.model.Vote;
import in.himanshugawari.reddit.model.VoteType;
import in.himanshugawari.reddit.repository.PostRepository;
import in.himanshugawari.reddit.repository.VoteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {

	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final AuthService authService;

	/*
	 * @Transactional public void vote(VoteDto voteDto) { Post post =
	 * postRepository.findById(voteDto.getPostId()) .orElseThrow(() -> new
	 * PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
	 * Optional<Vote> voteByPostAndUser =
	 * voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
	 * authService.getCurrentUser()); if (voteByPostAndUser.isPresent() &&
	 * voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) { throw
	 * new SpringRedditException("You have already " + voteDto.getVoteType() +
	 * "'d for this post"); } if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
	 * post.setVoteCount(post.getVoteCount() + 1); } else {
	 * post.setVoteCount(post.getVoteCount() - 1); }
	 * voteRepository.save(mapToVote(voteDto, post)); postRepository.save(post); }
	 */
	
	@Transactional
	public String vote(VoteDto voteDto) {
		Post post = postRepository.findById(voteDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
		Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
				authService.getCurrentUser());
		if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
			throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
		}
		if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
			post.setVoteCount(post.getVoteCount() + 1);
		} else {
			post.setVoteCount(post.getVoteCount() - 1);
		}
		Vote mapToVote = mapToVote(voteDto, post);
		voteRepository.save(mapToVote);
		postRepository.save(post);
		
		return mapToVote.getVoteId().toString();
	}

	private Vote mapToVote(VoteDto voteDto, Post post) {
		return Vote.builder().voteType(voteDto.getVoteType()).post(post).user(authService.getCurrentUser()).build();
	}
}