package com.tm.guestbook;

import com.tm.guestbook.api.repository.GuestBookEntryRepository;
import com.tm.guestbook.security.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuestBookMain implements CommandLineRunner {

	@Autowired
	private GuestBookEntryRepository guestBookEntryRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(GuestBookMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		userDetailsRepository.deleteAll();;
//		UserEntity userEntity = new UserEntity();
//		userEntity.setEmail("email");
//		userEntity.setPassword(new BCryptPasswordEncoder().encode("pass"));
//		userDetailsRepository.save(userEntity);
//
//		GuestBookEntryEntity guestBookEntryEntity = new GuestBookEntryEntity();
//		guestBookEntryEntity.setGuestBookEntryText("Test");
//		guestBookEntryEntity.setGuestBookEntryStatus(1L);
//		guestBookEntryEntity.setUserEntity(userEntity);
//		guestBookEntryRepository.save(guestBookEntryEntity);
	}
}
