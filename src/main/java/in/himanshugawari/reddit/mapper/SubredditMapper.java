package in.himanshugawari.reddit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import in.himanshugawari.reddit.dto.SubredditDto;
import in.himanshugawari.reddit.model.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubredditToDto(Subreddit subreddit);
	
	
}
