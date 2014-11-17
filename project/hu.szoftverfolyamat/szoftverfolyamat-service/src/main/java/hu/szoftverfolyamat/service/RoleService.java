package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.Role;
import hu.szoftverfolyamat.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

	@Autowired
    private RoleRepository roleRepository;
 
    public Role getRole(long id) {
        return this.roleRepository.findOne(id);
    }
    
}
