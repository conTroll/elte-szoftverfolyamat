package hu.szoftverfolyamat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole {
     
    private Long userRoleId;
    private String role;
    private Long credentialId;
 
    @Column(name = "credential_id")
    public Long getCredentialId() {
		return this.credentialId;
	}
 
    @Id
    @GeneratedValue
    @Column(name = "user_role_id")
    public Long getId() {
        return this.userRoleId;
    }
 
    @Column(name = "role")
    public String getRole() {
        return this.role;
    }
 
    public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}
 
    public void setId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

	public void setRole(String role) {
        this.role = role;
    }
     
}
