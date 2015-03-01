package hu.szoftverfolyamat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "channel_profile_data")
public class ChannelProfileEntity implements Serializable {
	
	private static final long serialVersionUID = 5221266339115704529L;
	
	private Long id;
	private UserProfileData leader;
	private String name;
	private String description;
	private boolean open;
	private Date creationDate;
	private Set<ChannelSubscriberEntity> subscribers;
	private List<ChannelPostEntity> posts;
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof ChannelProfileEntity))
			return false;
		
		ChannelProfileEntity other = (ChannelProfileEntity) obj;
		return Objects.equals(this.id, other.id);
		
	}

	@Id
	@GeneratedValue
	@Column(name = "channel_id", nullable = false)
	public Long getId() {
		return this.id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "leader_credential_id", nullable = false)
	public UserProfileData getLeader() {
		return this.leader;
	}
	
	@Column(name = "name", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}
	
	@Column(name = "description", nullable = false, length = 256)
	public String getDescription() {
		return this.description;
	}
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
	@OrderBy("creationDate ASC")
	public List<ChannelPostEntity> getPosts() {
		return this.posts;
	}
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
	public Set<ChannelSubscriberEntity> getSubscribers() {
		return this.subscribers;
	}
	
	@Override
	public int hashCode() {
		return 31 + Objects.hashCode(this.id);
	}
	
	@Column(name = "open", nullable = false)
	public boolean isOpen() {
		return this.open;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLeader(UserProfileData leader) {
		this.leader = leader;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void setPosts(List<ChannelPostEntity> posts) {
		this.posts = posts;
	}
	
	public void setSubscribers(Set<ChannelSubscriberEntity> subscribers) {
		this.subscribers = subscribers;
	}
	
	@Override
	public String toString() {
		return "ChannelProfileEntity [id=" + this.id + ", leaderCredentialId=" + this.leader.getCredentialId()
				+ ", name=" + this.name + ", description=" + this.description + ", open=" + this.open + "]";
	}
	
}