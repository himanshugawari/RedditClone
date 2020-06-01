package in.himanshugawari.reddit.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.SubredditDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = { "/api/subreddit" })
@AllArgsConstructor
@Slf4j
public class SubredditController {

	@PostMapping
	public void createSubreddit(@RequestBody SubredditDto subredditDto) {

	}
}
