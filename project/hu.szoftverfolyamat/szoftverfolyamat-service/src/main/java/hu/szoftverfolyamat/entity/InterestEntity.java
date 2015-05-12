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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "interests")
    public List<UserProfileData> getUsers() {
        return users;
    }

    public void setUsers(List<UserProfileData> users) {
        this.users = users;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "interests")
    public List<ChannelProfileEntity> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelProfileEntity> channels) {
        this.channels = channels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestEntity entity = (InterestEntity) o;

        if (id != entity.id) return false;
        if (createdAt != null ? !createdAt.equals(entity.createdAt) : entity.createdAt != null) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
