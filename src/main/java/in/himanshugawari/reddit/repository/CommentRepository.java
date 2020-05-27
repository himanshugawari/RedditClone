package in.himanshugawari.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.himanshugawari.reddit.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
