package in.himanshugawari.reddit.mapper;

import in.himanshugawari.reddit.dto.PostRequest;
import in.himanshugawari.reddit.dto.PostResponse;
import in.himanshugawari.reddit.model.Post;
import in.himanshugawari.reddit.model.Post.PostBuilder;
import in.himanshugawari.reddit.model.Subreddit;
import in.himanshugawari.reddit.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-06T20:34:32+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
        if ( postRequest == null && subreddit == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postRequest != null ) {
            post.description( postRequest.getDescription() );
            post.postId( postRequest.getPostId() );
            post.postName( postRequest.getPostName() );
            post.url( postRequest.getUrl() );
        }
        if ( subreddit != null ) {
            post.subreddit( subreddit );
        }
        if ( user != null ) {
            post.user( user );
        }
        post.createdDate( java.time.Instant.now() );
        post.voteCount( 0 );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setPostName( post.getPostName() );
        postResponse.setDescription( post.getDescription() );
        postResponse.setId( post.getPostId() );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setUrl( post.getUrl() );
        postResponse.setSubredditName( postSubredditName( post ) );
        postResponse.setVoteCount( post.getVoteCount() );

        postResponse.setDuration( getDuration(post) );
        postResponse.setDownVote( isPostDownVoted(post) );
        postResponse.setCommentCount( commentCount(post) );
        postResponse.setUpVote( isPostUpVoted(post) );

        return postResponse;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
