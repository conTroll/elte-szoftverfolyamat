package hu.szoftverfolyamat.enums;

public enum SubscriberStatus {
	
	ACTIVE(true), PENDING(false), BANNED(false);
	
	private boolean active;
	
	private SubscriberStatus(boolean active) {
		this.active = active;
	}
	
	public boolean isActive(){
		return this.active;
	}

}
