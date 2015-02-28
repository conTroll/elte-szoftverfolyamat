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

	public UserRole createRole(final Long userCredentialId, final Role role) {
		UserRole userRole = new UserRole();
		userRole.setCredentialId(userCredentialId);
		userRole.setRole(role.getRoleName());
		return userRoleRepository.saveAndFlush(userRole);
	}

	public UserRole getRoleByCredentialId(final Long userCredentialId) {
		return userRoleRepository.getByUserCredentialId(userCredentialId);
	}
}
