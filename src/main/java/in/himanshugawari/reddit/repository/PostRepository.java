package in.himanshugawari.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.himanshugawari.reddit.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
