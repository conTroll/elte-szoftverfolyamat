package hu.szoftverfolyamat.dto;

import java.util.Date;

public class RegistrationFormDto {

	private String username;
	private String password;
	private String email;
	private String fullName;
	private String shortName;
	private Boolean publicHabitat;
	private String habitat;
	private Boolean publicJobAndWorkplace;
	private String job;
	private String workplace;
	private Boolean publicBirthday;
	private Date birthday;

	public Date getBirthday() {
		return this.birthday;
	}

	public String getEmail() {
		return this.email;
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

	public String getPassword() {
		return this.password;
	}

	public Boolean getPublicBirthday() {
		return this.publicBirthday;
	}

	public Boolean getPublicHabitat() {
		return this.publicHabitat;
	}

	public Boolean getPublicJobAndWorkplace() {
		return this.publicJobAndWorkplace;
	}

	public String getShortName() {
		return this.shortName;
	}

	public String getUsername() {
		return this.username;
	}

	public String getWorkplace() {
		return this.workplace;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPublicBirthday(Boolean publicBirthday) {
		this.publicBirthday = publicBirthday;
	}

	public void setPublicHabitat(Boolean publicHabitat) {
		this.publicHabitat = publicHabitat;
	}

	public void setPublicJobAndWorkplace(Boolean publicJobAndWorkplace) {
		this.publicJobAndWorkplace = publicJobAndWorkplace;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

}
