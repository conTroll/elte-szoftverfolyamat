package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.UserRole;
import hu.szoftverfolyamat.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleService {

	@Autowired
    private UserRoleRepository userRoleRepository;
 
    public UserRole getRole(long id) {
        return this.userRoleRepository.findOne(id);
    }
    
}
