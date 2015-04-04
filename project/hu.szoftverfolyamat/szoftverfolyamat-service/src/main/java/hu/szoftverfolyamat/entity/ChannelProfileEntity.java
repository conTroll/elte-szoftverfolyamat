package hu.szoftverfolyamat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "channel_profile_data")
public class ChannelProfileEntity implements Serializable {
	
	private static final long serialVersionUID = -3771475272950984076L;
	
	private Long id;
	private ImageResourceEntity avatar;
	private UserProfileData leader;
	private String name;
	private String description;
	private boolean open;
	private Date creationDate;

	private Set<ChannelSubscriberEntity> subscribers;
	private List<ChannelPostEntity> posts;
	
	@Id
	@GeneratedValue
	@Column(name = "channel_id", nullable = false)
	public Long getId() {
		return this.id;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "avatar_id", nullable = true)
	public ImageResourceEntity getAvatar() {
		return avatar;
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
	
	@OneToMany(mappedBy = "id.channel", fetch = FetchType.LAZY)
	public Set<ChannelSubscriberEntity> getSubscribers() {
		return this.subscribers;
	}
	
	@Column(name = "open", nullable = false)
	public boolean isOpen() {
		return this.open;
	}
	
	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setAvatarId(ImageResourceEntity avatar) {
		this.avatar = avatar;
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
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof ChannelProfileEntity))
			return false;
		
		ChannelProfileEntity other = (ChannelProfileEntity) obj;
		return Objects.equals(this.id, other.id);
		
	}
	
	@Override
	public int hashCode() {
		return 31 + Objects.hashCode(this.id);
	}
	
	@Override
	public String toString() {
		return "ChannelProfileEntity [id=" + this.id + ", leaderCredentialId=" + this.leader.getCredentialId()
				+ ", name=" + this.name + ", description=" + this.description + ", open=" + this.open + "]";
	}
	
}
