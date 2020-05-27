package in.himanshugawari.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.himanshugawari.reddit.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
