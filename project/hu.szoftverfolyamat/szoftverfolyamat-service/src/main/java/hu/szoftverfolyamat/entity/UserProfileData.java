package hu.szoftverfolyamat.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_profile_data")
public class UserProfileData {

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
	private Date birthday;
	private Long avatarId;
	private List<ChannelProfileEntity> ownedChannels;
    private List<InterestEntity> interests;

	@Column(name = "avatar_id")
	public Long getAvatarId() {
		return this.avatarId;
	}

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
	public boolean getPublicBirthday() {
		return this.publicBirthday;
	}

	@Column(name = "public_habitat")
	public boolean getPublicHabitat() {
		return this.publicHabitat;
	}

	@Column(name = "public_job_and_workplace")
	public boolean getPublicJobAndWorkplace() {
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
	
	@OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
	@OrderBy("name ASC")
	public List<ChannelProfileEntity> getOwnedChannels() {
		return ownedChannels;
	}

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(
            name = "interests_to_user",
            joinColumns = {@JoinColumn(name="user_credentials_id", referencedColumnName = "credential_id")},
            inverseJoinColumns = {@JoinColumn(name="interest_id", referencedColumnName = "id")}
    )
    public List<InterestEntity> getInterests() {
        return interests;
    }

	public void setAvatarId(Long avatarId) {
		this.avatarId = avatarId;
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
	
	public void setOwnedChannels(List<ChannelProfileEntity> ownedChannels) {
		this.ownedChannels = ownedChannels;
	}

    public void setInterests(List<InterestEntity> interests) {
        this.interests = interests;
    }
}
