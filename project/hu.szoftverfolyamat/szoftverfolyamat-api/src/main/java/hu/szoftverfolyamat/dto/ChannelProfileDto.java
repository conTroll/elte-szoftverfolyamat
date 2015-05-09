package hu.szoftverfolyamat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChannelProfileDto {

	private Long id;
	private UserProfileDataDto leader;
	private String name;
	private String description;
	private boolean open;
	private Date creationDate;

}
