package hu.szoftverfolyamat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "channel_post_comments")
public class ChannelPostCommentEntity implements Serializable {

	private static final long serialVersionUID = 4869337815181280464L;
	
	private Long id;
	private ChannelPostEntity post;
	private UserProfileData author;
	private String text;
	private Date creationDate;
	
	@Id
	@GeneratedValue
	@Column(name = "channel_post_comment_id")
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "channel_post_id", nullable = false)
	public ChannelPostEntity getPost() {
		return post;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "author_credential_id", nullable = false)
	public UserProfileData getAuthor() {
		return author;
	}
	
	@Column(name = "text", nullable = false)
	public String getText() {
		return text;
	}
	
	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPost(ChannelPostEntity post) {
		this.post = post;
	}
	
	public void setAuthor(UserProfileData author) {
		this.author = author;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "ChannelPostCommentEntity [id=" + this.id + ", authorCredentialId=" + this.author.getCredentialId()
				+ ", text=" + this.text + ", creationDate=" + this.creationDate + "]";
	}

}
