package in.himanshugawari.reddit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.PostRequest;
import in.himanshugawari.reddit.dto.PostResponse;
import in.himanshugawari.reddit.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = { "/api/posts/" })
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	/*
	 * @PostMapping public ResponseEntity<Void> createPost(@RequestBody PostRequest
	 * postRequest) { postService.save(postRequest); return new
	 * ResponseEntity<>(HttpStatus.CREATED); }
	 */

	@PostMapping
	public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRequest));
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
	}

	@GetMapping(path = { "by-subreddit/{id}" })
	public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
	}

	@GetMapping(path = { "by-user/{name}" })
	public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUsername(name));
	}

}