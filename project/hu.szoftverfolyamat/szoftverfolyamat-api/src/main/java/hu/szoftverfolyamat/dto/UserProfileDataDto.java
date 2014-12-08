package hu.szoftverfolyamat.dto;

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

	public UserProfileDataDto() {
		this.publicHabitat = false;
		this.publicJobAndWorkplace = false;
		this.publicBirthday = false;
		this.friend = false;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public Long getCredentialId() {
		return this.credentialId;
	}

	public String getEmail() {
		return this.email;
	}

	public Long getFriendNumber() {
		return this.friendNumber;
	}

	public String getFullName() {
		return this.fullName;
	}

	public String getHabitat() {
		return this.habitat;
	}

	public String getJob() {
		return this.job;
	}

	public boolean getPublicBirthday() {
		return this.publicBirthday;
	}

	public boolean getPublicHabitat() {
		return this.publicHabitat;
	}

	public boolean getPublicJobAndWorkplace() {
		return this.publicJobAndWorkplace;
	}

	public String getShortName() {
		return this.shortName;
	}

	public String getWorkplace() {
		return this.workplace;
	}

	public boolean isFriend() {
		return this.friend;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	public void setFriendNumber(Long friendNumber) {
		this.friendNumber = friendNumber;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setPublicBirthday(boolean publicBirthday) {
		this.publicBirthday = publicBirthday;
	}

	public void setPublicHabitat(boolean publicHabitat) {
		this.publicHabitat = publicHabitat;
	}

	public void setPublicJobAndWorkplace(boolean publicJobAndWorkplace) {
		this.publicJobAndWorkplace = publicJobAndWorkplace;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
}
