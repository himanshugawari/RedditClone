package in.himanshugawari.reddit.mapper;

import in.himanshugawari.reddit.dto.CommentsDto;
import in.himanshugawari.reddit.model.Comment;
import in.himanshugawari.reddit.model.Comment.CommentBuilder;
import in.himanshugawari.reddit.model.Post;
import in.himanshugawari.reddit.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-06T20:34:32+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentsDto commentsDto, Post post, User user) {
        if ( commentsDto == null && post == null && user == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        if ( commentsDto != null ) {
            comment.text( commentsDto.getText() );
        }
        if ( post != null ) {
            comment.post( post );
            comment.user( post.getUser() );
        }
        comment.createdDate( java.time.Instant.now() );

        return comment.build();
    }

    @Override
    public CommentsDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setUserName( comment.getUser().getUsername() );
        commentsDto.setPostId( comment.getPost().getPostId() );

        return commentsDto;
    }
}
