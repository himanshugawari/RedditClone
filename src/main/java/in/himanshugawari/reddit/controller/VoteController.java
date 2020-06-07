package in.himanshugawari.reddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.VoteDto;
import in.himanshugawari.reddit.service.VoteService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

	private final VoteService voteService;

	/*
	 * @PostMapping public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
	 * voteService.vote(voteDto); return new ResponseEntity<>(HttpStatus.OK); }
	 */

	@PostMapping
	public ResponseEntity<String> vote(@RequestBody VoteDto voteDto) {
		return ResponseEntity.status(HttpStatus.OK).body(voteService.vote(voteDto));
	}
}