package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.entity.UserCredential;
import hu.szoftverfolyamat.enums.Role;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
@Transactional
public class UserCredentialService {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	@Autowired
	private UserProfileDataService userProfileDataService;

	@Autowired
	private UserRoleService userRoleService;

	public UserCredential createUserCredential(final UserCredentialDto userCredentialDto) throws UserServiceException, ParseException {
		if (userCredentialsRepository.getUserByUsername(userCredentialDto.getUsername()) != null) {
			throw new UserServiceException("Can not create user with name '" + userCredentialDto.getUsername() + "', because name already exists!");
		}

		UserCredential userCredential = new UserCredential();
		userCredential.setEnabled(true);
		userCredential.setPassword(userCredentialDto.getPassword());
		userCredential.setUsername(userCredentialDto.getUsername());

        userCredential = userCredentialsRepository.saveAndFlush(userCredential);
		userCredential.setUserProfileData(userProfileDataService
                .createUserProfileData(userCredential.getCredentialId(), userCredentialDto.getUserProfileDataDto()));
        userCredential.setUserRole(userRoleService.createRole(userCredential.getCredentialId(), Role.ROLE_USER));
		return userCredentialsRepository.saveAndFlush(userCredential);
	}

	public UserCredential getUser(String username) {
		final UserCredential userCredential  = userCredentialsRepository.getUserByUsername(username);
		userCredential.setUserRole(userRoleService.getRoleByCredentialId(userCredential.getCredentialId()));
		return userCredential;
	}

	// TODO userrole fetchelese, hogy egy selectel menjen!
	// public UserCredential getUser(String username) {
	//
	// return this.userCredentialsRepository.getUserByUsername(username);
	// }
}
