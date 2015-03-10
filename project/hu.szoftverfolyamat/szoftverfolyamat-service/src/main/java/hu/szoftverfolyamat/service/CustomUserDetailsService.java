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
     
    private Collection<? extends GrantedAuthority> getGrantedAuthorities(final UserRole userRole) {
    	final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
    	return authorities;
    }
    
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final UserCredential userCredential  = this.userCredentialService.getUser(username);

		return (userCredentialService.getUser(username) != null)
            ? getUser(userCredential)
            : null;
	}

    private User getUser(final UserCredential userCredential) {
        return new User(userCredential.getUsername(), userCredential.getPassword(), userCredential.isEnabled(),
                true, true, true, getGrantedAuthorities(userCredential.getUserRole()));
    }
}
