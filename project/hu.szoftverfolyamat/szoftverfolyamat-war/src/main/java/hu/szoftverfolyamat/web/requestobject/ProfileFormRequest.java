package hu.szoftverfolyamat.web.requestobject;

public class ProfileFormRequest {

	private Long credentialId;
	private String username;
	private String password;
	private String email;
	private String fullName;
	private String shortName;
	private String publicHabitat;
	private String habitat;
	private String publicJobAndWorkplace;
	private String job;
	private String workplace;
	private String publicBirthday;
	private String birthday;

	public Long getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPublicHabitat() {
		return publicHabitat;
	}

	public void setPublicHabitat(String publicHabitat) {
		this.publicHabitat = publicHabitat;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public String getPublicJobAndWorkplace() {
		return publicJobAndWorkplace;
	}

	public void setPublicJobAndWorkplace(String publicJobAndWorkplace) {
		this.publicJobAndWorkplace = publicJobAndWorkplace;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getPublicBirthday() {
		return publicBirthday;
	}

	public void setPublicBirthday(String publicBirthday) {
		this.publicBirthday = publicBirthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
