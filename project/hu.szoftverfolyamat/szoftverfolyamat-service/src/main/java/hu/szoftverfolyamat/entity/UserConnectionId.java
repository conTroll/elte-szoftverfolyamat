package hu.szoftverfolyamat.entity;

import java.io.Serializable;

public class UserConnectionId implements Serializable {

	private static final long serialVersionUID = 1292409400291822898L;

	private long credentialId1;
	private long credentialId2;

	public long getCredentialId1() {
		return this.credentialId1;
	}

	public long getCredentialId2() {
		return this.credentialId2;
	}

	public void setCredentialId1(long credentialId1) {
		this.credentialId1 = credentialId1;
	}

	public void setCredentialId2(long credentialId2) {
		this.credentialId2 = credentialId2;
	}
}
