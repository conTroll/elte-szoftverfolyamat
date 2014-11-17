package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.entity.UserCredential;
import hu.szoftverfolyamat.enums.Role;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.repository.UserCredentialsRepository;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCredentialService {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	@Autowired
	private UserProfileDataService userProfileDataService;

	@Autowired
	private UserRoleService userRoleService;

	public UserCredential createUserCredential(
			UserCredentialDto userCredentialDto) throws UserServiceException,
			ParseException {
		UserCredential userCredential;

		userCredential = this.userCredentialsRepository
				.getUserByUsername(userCredentialDto.getUsername());
		if (userCredential != null) {
			throw new UserServiceException("Can not create user with name '"
					+ userCredentialDto.getUsername()
					+ "', because name already exists!");
		}
		userCredential = new UserCredential();
		userCredential.setEnabled(true);
		userCredential.setPassword(userCredentialDto.getPassword());
		userCredential.setUsername(userCredentialDto.getUsername());
		userCredential = this.userCredentialsRepository
				.saveAndFlush(userCredential);
		userCredential.setUserProfileData(this.userProfileDataService
				.createUserProfileData(userCredential.getCredentialId(),
						userCredentialDto.getUserProfileDataDto()));
		userCredential.setUserRole(this.userRoleService.createRole(
				userCredential.getCredentialId(), Role.ROLE_USER));
		return this.userCredentialsRepository.saveAndFlush(userCredential);
	}

	public UserCredential getUser(String username) {
		return this.userCredentialsRepository.getUserByUsername(username);
	}
}
