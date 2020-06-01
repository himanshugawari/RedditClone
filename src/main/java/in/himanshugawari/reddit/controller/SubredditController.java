package in.himanshugawari.reddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.SubredditDto;
import in.himanshugawari.reddit.service.SubredditService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = { "/api/subreddit" })
@AllArgsConstructor
//@Slf4j
public class SubredditController {

	private final SubredditService subredditService;

	@PostMapping
	public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDto));
	}
}
