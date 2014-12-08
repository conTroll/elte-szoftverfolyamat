package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.UserRole;
import hu.szoftverfolyamat.enums.Role;
import hu.szoftverfolyamat.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	public UserRole createRole(Long userCredentialId, Role role) {
		UserRole userRole;

		userRole = new UserRole();
		userRole.setCredentialId(userCredentialId);
		userRole.setRole(role.getRoleName());

		return this.userRoleRepository.saveAndFlush(userRole);
	}

	public UserRole getRoleByCredentialId(Long userCredentialId) {
		return this.userRoleRepository.getByUserCredentialId(userCredentialId);
	}

}
