package hu.szoftverfolyamat.entity;

import hu.szoftverfolyamat.enums.SubscriberStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "channel_subscribers")
public class ChannelSubscriberEntity implements Serializable {

	private static final long serialVersionUID = -4992730295050324392L;
	
	private ChannelProfileEntity channel;
	private UserProfileData user;
	private SubscriberStatus status;
	private Date subscriptionDate;

	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "channel_id", nullable = false)
	public ChannelProfileEntity getChannel() {
		return channel;
	}
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subscriber_credential_id", nullable = false)
	public UserProfileData getUser() {
		return user;
	}

	@Column(name = "subscriber_status", nullable = false)
	@Enumerated(EnumType.STRING)
	public SubscriberStatus getStatus() {
		return status;
	}
	
	@Column(name = "subscription_date", nullable = false)
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}
	
	public void setChannel(ChannelProfileEntity channel) {
		this.channel = channel;
	}
	
	public void setUser(UserProfileData user) {
		this.user = user;
	}
	
	public void setStatus(SubscriberStatus status) {
		this.status = status;
	}
	
	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	
	@Override
	public String toString() {
		return "ChannelSubscriberEntity [status=" + this.status
				+ ", subscriptionDate=" + this.subscriptionDate + "]";
	}

	
}
