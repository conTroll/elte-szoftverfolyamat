package hu.szoftverfolyamat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "channel_posts")
public class ChannelPostEntity implements Serializable {
	
	private static final long serialVersionUID = -2663142290953994895L;
	
	private Long id;
	private ChannelProfileEntity channel;
	private String text;
	private Date creationDate;
	private List<ChannelPostCommentEntity> comments;

	@Id
	@GeneratedValue
	@Column(name = "channel_post_id")
	public Long getId() {
		return this.id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "channel_id", nullable = false)
	public ChannelProfileEntity getChannel() {
		return this.channel;
	}
	
	@Column(name = "text", nullable = false)
	public String getText() {
		return this.text;
	}
	
	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@OrderBy("creationDate ASC")
	public List<ChannelPostCommentEntity> getComments() {
		return this.comments;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setChannel(ChannelProfileEntity channel) {
		this.channel = channel;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public void setComments(List<ChannelPostCommentEntity> comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "ChannelPostEntity [id=" + this.id + ", channelId=" + this.channel.getId()
				+ ", text=" + this.text + ", creationDate=" + this.creationDate + "]";
	}

}
