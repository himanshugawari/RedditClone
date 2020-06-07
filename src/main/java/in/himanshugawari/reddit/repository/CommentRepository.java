package in.himanshugawari.reddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.himanshugawari.reddit.model.Comment;
import in.himanshugawari.reddit.model.Post;
import in.himanshugawari.reddit.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPost(Post post);

	List<Comment> findAllByUser(User user);
}
