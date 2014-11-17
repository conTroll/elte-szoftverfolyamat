package hu.szoftverfolyamat.dto;

public class UserCredentialDto {

	private Long credentialId;
	private String username;
	private String password;
	private Boolean enabled;
	private UserProfileDataDto userProfileDataDto;

	public Long getCredentialId() {
		return this.credentialId;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public UserProfileDataDto getUserProfileDataDto() {
		return this.userProfileDataDto;
	}

	public Boolean isEnabled() {
		return this.enabled;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserProfileDataDto(UserProfileDataDto userProfileDataDto) {
		this.userProfileDataDto = userProfileDataDto;
	}

}
