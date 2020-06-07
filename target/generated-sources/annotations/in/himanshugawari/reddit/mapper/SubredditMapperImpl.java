package in.himanshugawari.reddit.mapper;

import in.himanshugawari.reddit.dto.SubredditDto;
import in.himanshugawari.reddit.dto.SubredditDto.SubredditDtoBuilder;
import in.himanshugawari.reddit.model.Subreddit;
import in.himanshugawari.reddit.model.Subreddit.SubredditBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-06T20:34:32+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDtoBuilder subredditDto = SubredditDto.builder();

        subredditDto.id( subreddit.getId() );
        subredditDto.name( subreddit.getName() );
        subredditDto.description( subreddit.getDescription() );

        subredditDto.numberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDto.build();
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDto subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditBuilder subreddit1 = Subreddit.builder();

        subreddit1.id( subreddit.getId() );
        subreddit1.name( subreddit.getName() );
        subreddit1.description( subreddit.getDescription() );

        return subreddit1.build();
    }
}
