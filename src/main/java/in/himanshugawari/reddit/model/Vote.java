package in.himanshugawari.reddit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long voteId;

	private VoteType voteType;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "postId", referencedColumnName = "postId")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;
}
