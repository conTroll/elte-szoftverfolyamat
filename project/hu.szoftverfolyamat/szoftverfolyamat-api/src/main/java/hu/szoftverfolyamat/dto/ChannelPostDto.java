package hu.szoftverfolyamat.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ChannelPostDto {

	private Long id;
	private ChannelProfileDto channel;
	private String text;
	private Date creationDate;
	private List<ChannelPostCommentDto> comments;

}
