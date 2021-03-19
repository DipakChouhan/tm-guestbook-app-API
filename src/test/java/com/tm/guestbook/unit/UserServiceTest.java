package com.tm.guestbook.unit;

import com.tm.guestbook.security.entity.UserEntity;
import com.tm.guestbook.security.model.UserModel;
import com.tm.guestbook.security.repository.UserDetailsRepository;
import com.tm.guestbook.security.service.impl.UserDetailsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    UserDetailsRepository userDetailsRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_loadUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(12L);
        userEntity.setEmail("some@mail.com");
        userEntity.setPassword("Dipak");
        userEntity.setRole("USER");
        userEntity.setUsername("Dipak");

        Mockito.when(userDetailsRepository.loadUserByUsername("some@mail.com")).thenReturn(userEntity);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("some@mail.com");

        Assert.assertNotNull(userDetails);
    }

    @Test
    public void test_getUserDetailsByEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(12L);
        userEntity.setEmail("some@mail.com");
        userEntity.setPassword("Dipak");
        userEntity.setRole("USER");
        userEntity.setUsername("Dipak");

        Mockito.when(userDetailsRepository.loadUserByUsername("some@mail.com")).thenReturn(userEntity);

        UserModel userModel = userDetailsServiceImpl.getUserDetailsByEmail("some@mail.com");

        Assert.assertNotNull(userModel);
    }

    @Test
    public void test_createUser()  {

        UserModel userModel = new UserModel();
        userModel.setName("DIApk");
        userModel.setEmail("d@dd.com");
        userModel.setPassword("dipak0");
        userModel.setRole("ADMIN");
        userModel.setUsername("username");

        //Mockito.when(userDetailsRepository.loadUserByUsername("some@mail.com")).thenReturn(null);
        boolean flag = true;
        try {
            userDetailsServiceImpl.createUser(userModel);
        } catch (Exception exception) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }
}
