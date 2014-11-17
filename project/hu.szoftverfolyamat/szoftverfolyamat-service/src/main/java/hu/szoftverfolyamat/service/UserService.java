package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.User;
import hu.szoftverfolyamat.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
    private UserRepository userRepository;
 
    public User getUser(String username) {
        return this.userRepository.getUserByUsername(username);
    }
}
