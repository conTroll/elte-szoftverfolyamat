package hu.szoftverfolyamat.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class PostEntity {

	private long postId;
	private String text;
	private Date creationDate;
	private UserProfileData userProfileData;
	private List<CommentEntity> commentEntities;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "postId")
	public List<CommentEntity> getCommentEntities() {
		return this.commentEntities;
	}

	@Column(name = "creation_date")
	public Date getCreationDate() {
		return this.creationDate;
	}

	@Id
	@GeneratedValue
	@Column(name = "post_id")
	public long getPostId() {
		return this.postId;
	}

	@Column(name = "text")
	public String getText() {
		return this.text;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_credential_id")
	public UserProfileData getUserProfileData() {
		return this.userProfileData;
	}

	public void setCommentEntities(List<CommentEntity> commentEntities) {
		this.commentEntities = commentEntities;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUserProfileData(UserProfileData userProfileData) {
		this.userProfileData = userProfileData;
	}

}
