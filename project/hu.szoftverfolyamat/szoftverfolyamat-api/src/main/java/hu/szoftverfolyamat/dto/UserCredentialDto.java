package hu.szoftverfolyamat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCredentialDto {

	private Long credentialId;
	
	@Size(min=1, max=32) @NotNull
	private String username;
	
	@Size(min=1, max=32) @NotNull
	private String password;
	private Boolean enabled;
	private UserProfileDataDto userProfileDataDto;
}
