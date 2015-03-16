package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.UserConnectionEntity;
import hu.szoftverfolyamat.entity.UserConnectionId;
import hu.szoftverfolyamat.repository.UserConnectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserConnectionService {

	@Autowired
	private UserConnectionsRepository userConnectionsRepository;

	public void createUserConnection(final Long userCredentialId1, final Long userCredentialId2) {
		final UserConnectionEntity connectionEntity1 = new UserConnectionEntity();
		connectionEntity1.setCredentialId1(userCredentialId1);
		connectionEntity1.setCredentialId2(userCredentialId2);
		userConnectionsRepository.saveAndFlush(connectionEntity1);

        final UserConnectionEntity connectionEntity2 = new UserConnectionEntity();
		connectionEntity2.setCredentialId1(userCredentialId2);
		connectionEntity2.setCredentialId2(userCredentialId1);
		userConnectionsRepository.saveAndFlush(connectionEntity2);
	}

	public void deleteUserConnection(final Long userCredentialId1, final Long userCredentialId2) {
		final UserConnectionId connectionId1 = new UserConnectionId();
		connectionId1.setCredentialId1(userCredentialId1);
		connectionId1.setCredentialId2(userCredentialId2);
		userConnectionsRepository.delete(connectionId1);

        final UserConnectionId connectionId2 = new UserConnectionId();
		connectionId2.setCredentialId1(userCredentialId2);
		connectionId2.setCredentialId2(userCredentialId1);
		userConnectionsRepository.delete(connectionId2);
	}

	public List<Long> getFriendsIdByUserCredentialId(final Long credentialId) {
		return userConnectionsRepository.getFriendsIdByUserCredentialId(credentialId);
	}
}
