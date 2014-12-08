package hu.szoftverfolyamat.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-application-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserCredentialsRepositoryTest {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	@Test
	public void testGetUserByUsername() {
		Assert.assertNotNull(this.userCredentialsRepository
				.getUserByUsername("admin"));
	}
}
