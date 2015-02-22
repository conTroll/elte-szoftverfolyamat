package hu.szoftverfolyamat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity {

	private long commentId;
	private String text;
	private long postId;
	private UserProfileData userProfileData;
	private Date creationDate;

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	public long getCommentId() {
		return this.commentId;
	}

	@Column(name = "creation_date")
	public Date getCreationDate() {
		return this.creationDate;
	}

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

	public void setCommentId(long commentId) {
		this.commentId = commentId;
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
