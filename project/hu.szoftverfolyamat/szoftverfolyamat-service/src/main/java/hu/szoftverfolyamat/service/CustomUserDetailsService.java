package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.UserCredential;
import hu.szoftverfolyamat.entity.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialService userCredentialService;
	
	@Autowired
	private UserRoleService userRoleService;
     
    private Collection<? extends GrantedAuthority> getGrantedAuthorities(UserRole userRole) {
    	List<GrantedAuthority> authorities;
    	
    	authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
    	
    	return authorities;
    }
    
    public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserCredential userCredential;
		
		userCredential = this.userCredentialService.getUser(username);
		if(userCredential != null) {
			return new User(userCredential.getUsername(), userCredential.getPassword(), userCredential.isEnabled(), true, true, true, this.getGrantedAuthorities(userCredential.getUserRole()));
		}
		return null;
	}

	
}
