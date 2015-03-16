package hu.szoftverfolyamat.dto;

import lombok.Data;

@Data
public class UserCredentialDto {

	private Long credentialId;
	private String username;
	private String password;
	private Boolean enabled;
	private UserProfileDataDto userProfileDataDto;
}
