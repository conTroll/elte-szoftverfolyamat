package hu.szoftverfolyamat.entity;

import hu.szoftverfolyamat.enums.SubscriberStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "channel_subscribers")
public class ChannelSubscriberEntity implements Serializable {

	private static final long serialVersionUID = -4992730295050324392L;
	
	private ChannelSubscriberEntityId id;
	private SubscriberStatus status;
	private Date subscriptionDate;

	@EmbeddedId
	public ChannelSubscriberEntityId getId() {
		return id;
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
	
	public void setId(ChannelSubscriberEntityId id) {
		this.id = id;
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
