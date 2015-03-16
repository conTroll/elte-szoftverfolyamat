package hu.szoftverfolyamat.dto;

import lombok.Data;

@Data
public class UserProfileDataDto {

	private Long credentialId;
	private String email;
	private String fullName;
	private String shortName;
	private boolean publicHabitat;
	private String habitat;
	private boolean publicJobAndWorkplace;
	private String job;
	private String workplace;
	private boolean publicBirthday;
	private String birthday;
	private Long friendNumber;
	private boolean friend;
	private Long avatarId;
}
