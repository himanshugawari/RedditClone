package in.himanshugawari.reddit.dto;

import in.himanshugawari.reddit.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
	private VoteType voteType;
	private Long postId;
}