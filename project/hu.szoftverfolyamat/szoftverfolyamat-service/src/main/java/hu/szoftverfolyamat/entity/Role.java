package hu.szoftverfolyamat.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
     
    @Id
    @GeneratedValue
    private Long id;
     
    private String role;
     
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_roles", 
        joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")}
    )
    private Set<User> userRoles;
 
    public Long getId() {
        return this.id;
    }
 
    public String getRole() {
        return this.role;
    }
 
    public Set<User> getUserRoles() {
        return this.userRoles;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public void setUserRoles(Set<User> userRoles) {
        this.userRoles = userRoles;
    }
     
}
