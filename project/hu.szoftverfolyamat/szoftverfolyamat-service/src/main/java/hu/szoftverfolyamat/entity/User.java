package hu.szoftverfolyamat.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
     
    @Id
    @GeneratedValue
    private Long id;
     
    private String username;
     
    private String password;
     
    @OneToOne(fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private Role role;
 
    public Long getId() {
        return this.id;
    }
 
    public String getPassword() {
        return this.password;
    }
 
    public Role getRole() {
        return this.role;
    }
 
    public String getUsername() {
        return this.username;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public void setRole(Role role) {
        this.role = role;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }   
 
}
