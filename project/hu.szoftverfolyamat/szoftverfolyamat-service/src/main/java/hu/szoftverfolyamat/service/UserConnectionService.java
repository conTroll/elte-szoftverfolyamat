package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.UserConnectionEntity;
import hu.szoftverfolyamat.entity.UserConnectionId;
import hu.szoftverfolyamat.repository.UserConnectionsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserConnectionService {

	@Autowired
	private UserConnectionsRepository userConnectionsRepository;

	public void createUserConnection(Long userCredentialId1,
			Long userCredentialId2) {
		UserConnectionEntity connectionEntity;

		connectionEntity = new UserConnectionEntity();
		connectionEntity.setCredentialId1(userCredentialId1);
		connectionEntity.setCredentialId2(userCredentialId2);

		this.userConnectionsRepository.saveAndFlush(connectionEntity);

		connectionEntity = new UserConnectionEntity();
		connectionEntity.setCredentialId1(userCredentialId2);
		connectionEntity.setCredentialId2(userCredentialId1);

		this.userConnectionsRepository.saveAndFlush(connectionEntity);
	}

	public void deleteUserConnection(Long userCredentialId1,
			Long userCredentialId2) {
		UserConnectionId connectionId;

		connectionId = new UserConnectionId();
		connectionId.setCredentialId1(userCredentialId1);
		connectionId.setCredentialId2(userCredentialId2);

		this.userConnectionsRepository.delete(connectionId);

		connectionId = new UserConnectionId();
		connectionId.setCredentialId1(userCredentialId2);
		connectionId.setCredentialId2(userCredentialId1);

		this.userConnectionsRepository.delete(connectionId);

	}

	public List<Long> getFriendsIdByUserCredentialId(Long credentialId) {
		return this.userConnectionsRepository
				.getFriendsIdByUserCredentialId(credentialId);
	}
}
