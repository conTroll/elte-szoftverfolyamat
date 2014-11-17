package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
        List<GrantedAuthority> authList = this.getGrantedAuthorities(this.getRoles(role));
        return authList;
    }
	
	public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
     
    public List<String> getRoles(Long id) {
 
        List<String> roles = new ArrayList<String>();
 
        roles.add(this.roleService.getRole(id).getRole());
        
        return roles;
    }
     
    public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user;
		org.springframework.security.core.userdetails.User userDetails;
		
		user = this.userService.getUser(username);
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), this.getAuthorities(user.getRole().getId()));
		}
		return null;
	}

	
}
