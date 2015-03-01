package hu.szoftverfolyamat.entity;

import hu.szoftverfolyamat.enums.MessageStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class MessageEntity {

    private long id;
    private String text;
    private Date createdAt;
    private UserProfileData userFrom;
    private UserProfileData userTo;
    private MessageStatus status;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_from")
    public UserProfileData getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(final UserProfileData userFrom) {
        this.userFrom = userFrom;
    }

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_to")
    public UserProfileData getUserTo() {
        return userTo;
    }

    public void setUserTo(final UserProfileData userTo) {
        this.userTo = userTo;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }
}
