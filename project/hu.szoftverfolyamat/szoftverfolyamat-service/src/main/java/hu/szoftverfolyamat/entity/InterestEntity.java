package hu.szoftverfolyamat.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "interests")
public class InterestEntity {
    private long id;
    private String name;
    private Date createdAt;
    private List<UserProfileData> users;
    private List<ChannelProfileEntity> channels;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(
        name = "interests_to_user",
        joinColumns = {@JoinColumn(name="interest_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name="user_credentials_id", referencedColumnName = "credential_id")}
    )
    public List<UserProfileData> getUsers() {
        return users;
    }

    public void setUsers(List<UserProfileData> users) {
        this.users = users;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(
        name = "interest_to_channel",
        joinColumns = {@JoinColumn(name="interest_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name="channel_id", referencedColumnName = "channel_id")}
    )
    public List<ChannelProfileEntity> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelProfileEntity> channels) {
        this.channels = channels;
    }
}
