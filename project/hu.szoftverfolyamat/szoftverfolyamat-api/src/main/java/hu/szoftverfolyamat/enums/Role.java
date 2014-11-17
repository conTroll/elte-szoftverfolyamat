package hu.szoftverfolyamat.enums;

public enum Role {
	
	ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

	private String roleName;
	
	private Role(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
}
