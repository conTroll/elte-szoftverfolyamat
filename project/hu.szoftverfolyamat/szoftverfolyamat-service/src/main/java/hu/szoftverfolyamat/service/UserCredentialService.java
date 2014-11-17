package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.UserCredential;
import hu.szoftverfolyamat.repository.UserCredentialsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCredentialService {

	@Autowired
    private UserCredentialsRepository userCredentialsRepository;
 
    public UserCredential getUser(String username) {
        return this.userCredentialsRepository.getUserByUsername(username);
    }
}
