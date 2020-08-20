package com.java.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.model.UserLoginInfoModel;

//@SpringBootTest
//@ContextConfiguration(classes=Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserLoginInforRepoTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserLoginInfoRepository userLoginInfoRepository;
	
	@Test
	public void testGet() {
		this.entityManager.persist(new UserLoginInfoModel("userA", "A@a", "passwordA", Arrays.asList("ROLE_user")));
		UserLoginInfoModel user = this.userLoginInfoRepository.findById("userA").orElse(null);
		assertThat(user.getUsername()).isEqualTo("userA");
        assertThat(user.getEmail()).isEqualTo("A@a");
        assertThat(user.getPassword()).isEqualTo("passwordA");
        assertIterableEquals(user.getAuthorities(), Arrays.asList("ROLE_user"));
	}
	
//	@Test
//	public void testSave() {
//		UserLoginInfoModel user = new UserLoginInfoModel("userA", "A@a", "passwordA", Arrays.asList("ROLE_user")); 
//		this.userLoginInfoRepository.save(user);
//		this.entityManager.
//		assertThat(user.getUsername()).isEqualTo("userA");
//        assertThat(user.getEmail()).isEqualTo("A@a");
//        assertThat(user.getPassword()).isEqualTo("passwordA");
//        assertIterableEquals(user.getAuthorities(), Arrays.asList("ROLE_user"));
//	}
}
