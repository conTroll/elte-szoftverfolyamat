package hu.szoftverfolyamat.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ChannelPostDto {

	private Long id;
	private ChannelProfileDto channel;
	private String text;
	private Date creationDate;
	private List<ChannelPostCommentDto> comments;

}
