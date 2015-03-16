package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserCredential;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-application-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserCredentialsRepositoryTest {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	@Test
	public void testGetUserByUsername() {
        // given
		final UserCredential userCredential;
		userCredential = new UserCredential();
		userCredential.setEnabled(true);
		userCredential.setPassword("123");
		userCredential.setUsername("admin");
		userCredential.setUserProfileData(null);
		userCredential.setUserRole(null);

        // when
        userCredentialsRepository.saveAndFlush(userCredential);

        // then
		Assert.assertNotNull(userCredentialsRepository.getUserByUsername("admin"));
	}
}
