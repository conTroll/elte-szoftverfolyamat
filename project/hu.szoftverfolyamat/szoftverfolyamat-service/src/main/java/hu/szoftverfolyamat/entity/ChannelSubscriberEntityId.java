package hu.szoftverfolyamat.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ChannelSubscriberEntityId implements Serializable {

	private static final long serialVersionUID = 6994900612427159949L;
	
	private ChannelProfileEntity channel;
	private UserProfileData user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "channel_id", nullable = false)
	public ChannelProfileEntity getChannel() {
		return channel;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subscriber_credential_id", nullable = false)
	public UserProfileData getUser() {
		return user;
	}

	public void setChannel(ChannelProfileEntity channel) {
		this.channel = channel;
	}

	public void setUser(UserProfileData user) {
		this.user = user;
	}

}
