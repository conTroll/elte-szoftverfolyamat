package hu.szoftverfolyamat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile_data")
public class UserProfileData {

	
	private Long credentialId;
	private String email;
	private String fullName;
	private String shortName;
	private int publicHabitat;
	private String habitat;
	private int publicJobAndWorkplace;
	private String job;
	private String workplace;
	private int publicBirthday;
	private Date birthday;

	@Column(name = "birthday")
	public Date getBirthday() {
		return this.birthday;
	}

	@Id
	@Column(name = "credential_id")
	public Long getCredentialId() {
		return this.credentialId;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return this.fullName;
	}

	@Column(name = "habitat")
	public String getHabitat() {
		return this.habitat;
	}

	@Column(name = "job")
	public String getJob() {
		return this.job;
	}

	@Column(name = "public_birthday")
	public int getPublicBirthday() {
		return this.publicBirthday;
	}

	@Column(name = "public_habitat")
	public int getPublicHabitat() {
		return this.publicHabitat;
	}

	@Column(name = "public_job_and_workplace")
	public int getPublicJobAndWorkplace() {
		return this.publicJobAndWorkplace;
	}

	@Column(name = "short_name")
	public String getShortName() {
		return this.shortName;
	}

	@Column(name = "workplace")
	public String getWorkplace() {
		return this.workplace;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
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

	public void setPublicBirthday(int publicBirthday) {
		this.publicBirthday = publicBirthday;
	}

	public void setPublicHabitat(int publicHabitat) {
		this.publicHabitat = publicHabitat;
	}

	public void setPublicJobAndWorkplace(int publicJobAndWorkplace) {
		this.publicJobAndWorkplace = publicJobAndWorkplace;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
}
