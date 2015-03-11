package hu.szoftverfolyamat.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_connections")
@IdClass(UserConnectionId.class)
public class UserConnectionEntity {

	private long credentialId1;
	private long credentialId2;

	@Id
	@Column(name = "credential_id_1")
	public long getCredentialId1() {
		return this.credentialId1;
	}

	@Id
	@Column(name = "credential_id_2")
	public long getCredentialId2() {
		return this.credentialId2;
	}

	public void setCredentialId1(long credentialId1) {
		this.credentialId1 = credentialId1;
	}

	public void setCredentialId2(long credentialId2) {
		this.credentialId2 = credentialId2;
	};
}
