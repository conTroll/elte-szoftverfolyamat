package hu.szoftverfolyamat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user_credentials")
public class UserCredential {
     
    private Long credentialId;
    private String username; 
    private String password;
    private boolean enabled;
    private UserRole userRole;
    private UserProfileData userProfileData;
 
    @Id
    @GeneratedValue
    @Column(name = "credential_id")
    public Long getCredentialId() {
		return this.credentialId;
	}

//	@Column(name = "enabled")
//    public int getEnabled() {
//		return this.enabled;
//	}

	@Column(name = "password_hash")
    public String getPassword() {
        return this.password;
    }
	
	@Column(name = "username")
    public String getUsername() {
        return this.username;
    }

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id")
    public UserProfileData getUserProfileData() {
		return this.userProfileData;
	}
 
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id")
    public UserRole getUserRole() {
		return this.userRole;
	}
 
	@Column(name = "enabled")
	public boolean isEnabled() {
		return this.enabled;
	}

 
    public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}
 
    public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setPassword(String password) {
        this.password = password;
    }

	public void setUsername(String username) {
        this.username = username;
    }

	public void setUserProfileData(UserProfileData userProfileData) {
		this.userProfileData = userProfileData;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}   
 
}
