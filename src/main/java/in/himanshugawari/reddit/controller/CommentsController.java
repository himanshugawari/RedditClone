package in.himanshugawari.reddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.CommentsDto;
import in.himanshugawari.reddit.service.CommentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = { "/api/comments/" })
@AllArgsConstructor
public class CommentsController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(commentsDto));
	}

}
