package hu.szoftverfolyamat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelProfileDto {

	private Long id;
	private UserProfileDataDto leader;
	private String name;
	private String description;
	private boolean open;
	private Date creationDate;

}
