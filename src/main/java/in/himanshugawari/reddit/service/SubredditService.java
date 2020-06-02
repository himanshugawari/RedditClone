package in.himanshugawari.reddit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.himanshugawari.reddit.dto.SubredditDto;
import in.himanshugawari.reddit.exceptions.SpringRedditException;
import in.himanshugawari.reddit.mapper.SubredditMapper;
import in.himanshugawari.reddit.model.Subreddit;
import in.himanshugawari.reddit.repository.SubredditRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
//@Slf4j
public class SubredditService {

	private final SubredditRepository subredditRepository;
	private final SubredditMapper subredditMapper;

	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		// Subreddit save = subredditRepository.save(mapSubredditDto(subredditDto));
		Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}

	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		// return
		// subredditRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
		return subredditRepository.findAll().stream().map(subredditMapper::mapSubredditToDto)
				.collect(Collectors.toList());
	}

	/*
	public SubredditDto getSubreddit(Long id) {
		Subreddit subreddit = subredditRepository.findById(id)
				.orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
		return subredditMapper.mapSubredditToDto(subreddit);
	}
*/
	
	// changing from normal to mapstruct mapper
	/*
	 * private SubredditDto mapToDto(Subreddit subreddit) { return
	 * SubredditDto.builder().name(subreddit.getName()).id(subreddit.getId())
	 * .numberOfPosts(subreddit.getPosts().size()).build(); }
	 * 
	 * private Subreddit mapSubredditDto(SubredditDto subredditDto) { return
	 * Subreddit.builder().name(subredditDto.getName()).description(subredditDto.
	 * getDescription()).build(); }
	 */
}
