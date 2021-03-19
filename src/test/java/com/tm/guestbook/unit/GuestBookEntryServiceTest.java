package com.tm.guestbook.unit;

import com.tm.guestbook.api.common.utility.GuestBookSecurityContextHolder;
import com.tm.guestbook.api.entity.GuestBookEntryEntity;
import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.api.repository.GuestBookEntryRepository;
import com.tm.guestbook.api.service.GuestBookEntryServiceImpl;
import com.tm.guestbook.security.entity.UserEntity;
import com.tm.guestbook.security.repository.UserDetailsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GuestBookEntryServiceTest {

    @InjectMocks
    GuestBookEntryServiceImpl guestBookEntryServiceImpl;

    @Mock
    GuestBookEntryRepository guestBookEntryRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getAllGuestBookEntries() {
        List<GuestBookEntryEntity> guestBookEntryEntitys = new ArrayList<>();
        GuestBookEntryEntity guestBookEntryEntityOne = new GuestBookEntryEntity();
        guestBookEntryEntityOne.setGuestBookEntryId(1L);
        guestBookEntryEntityOne.setGuestBookEntryText("One");
        guestBookEntryEntityOne.setGuestBookEntryStatus(1L);
        guestBookEntryEntityOne.setCreatedBy("TestUser");
        guestBookEntryEntityOne.setModifiedBy("TestUser");
        guestBookEntryEntityOne.setCreatedOn(LocalDateTime.now());
        guestBookEntryEntityOne.setModifiedOn(LocalDateTime.now());

        GuestBookEntryEntity guestBookEntryEntityTwo = new GuestBookEntryEntity();
        guestBookEntryEntityTwo.setGuestBookEntryId(2L);
        guestBookEntryEntityTwo.setGuestBookEntryText("Two");
        guestBookEntryEntityTwo.setGuestBookEntryStatus(1L);
        guestBookEntryEntityTwo.setCreatedBy("TestUser");
        guestBookEntryEntityTwo.setModifiedBy("TestUser");
        guestBookEntryEntityTwo.setCreatedOn(LocalDateTime.now());
        guestBookEntryEntityTwo.setModifiedOn(LocalDateTime.now());

        GuestBookEntryEntity guestBookEntryEntityThree = new GuestBookEntryEntity();
        guestBookEntryEntityThree.setGuestBookEntryId(3L);
        guestBookEntryEntityThree.setGuestBookEntryText("Three");
        guestBookEntryEntityThree.setGuestBookEntryStatus(2L);
        guestBookEntryEntityThree.setCreatedBy("TestUser");
        guestBookEntryEntityThree.setModifiedBy("TestUser");
        guestBookEntryEntityThree.setCreatedOn(LocalDateTime.now());
        guestBookEntryEntityThree.setModifiedOn(LocalDateTime.now());

        guestBookEntryEntitys.add(guestBookEntryEntityOne);
        guestBookEntryEntitys.add(guestBookEntryEntityTwo);
        guestBookEntryEntitys.add(guestBookEntryEntityThree);

        Mockito.when(guestBookEntryRepository.findAll(Sort.by(Sort.Order.desc("createdOn")))).thenReturn(guestBookEntryEntitys);

        //test
        List<GuestBookEntryModel> guestBookEntryModels = guestBookEntryServiceImpl.getAllGuestBookEntries();

        Assert.assertEquals(3, guestBookEntryModels.size());
    }

    @Test
    public void test_getAllGuestBookEntriesForUser() {
        List<GuestBookEntryEntity> guestBookEntryEntitys = new ArrayList<>();
        GuestBookEntryEntity guestBookEntryEntityOne = new GuestBookEntryEntity();
        guestBookEntryEntityOne.setGuestBookEntryId(1L);
        guestBookEntryEntityOne.setGuestBookEntryText("One");
        guestBookEntryEntityOne.setGuestBookEntryStatus(1L);
        guestBookEntryEntityOne.setCreatedBy("TestUser");
        guestBookEntryEntityOne.setModifiedBy("TestUser");
        guestBookEntryEntityOne.setCreatedOn(LocalDateTime.now());
        guestBookEntryEntityOne.setModifiedOn(LocalDateTime.now());

        GuestBookEntryEntity guestBookEntryEntityTwo = new GuestBookEntryEntity();
        guestBookEntryEntityTwo.setGuestBookEntryId(2L);
        guestBookEntryEntityTwo.setGuestBookEntryText("Two");
        guestBookEntryEntityTwo.setGuestBookEntryStatus(1L);
        guestBookEntryEntityTwo.setCreatedBy("TestUser");
        guestBookEntryEntityTwo.setModifiedBy("TestUser");
        guestBookEntryEntityTwo.setCreatedOn(LocalDateTime.now());
        guestBookEntryEntityTwo.setModifiedOn(LocalDateTime.now());

        guestBookEntryEntitys.add(guestBookEntryEntityOne);
        guestBookEntryEntitys.add(guestBookEntryEntityTwo);

        Mockito.when(guestBookEntryRepository.getAllGuestBookEntriesForUser(5L)).thenReturn(guestBookEntryEntitys);

        //test
        List<GuestBookEntryModel> guestBookEntryModels = guestBookEntryServiceImpl.getAllGuestBookEntriesForUser(5L);

        Assert.assertEquals(2, guestBookEntryModels.size());
    }

    @Test
    public void test_deleteGuestBookEntries()
    {
        guestBookEntryServiceImpl.deleteGuestBookEntries("2");
        verify(guestBookEntryRepository, times(1)).deleteById(2L);
    }

    @Test
    public void test_updateGuestEntryText()
    {
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryId(2L);
        guestBookEntryModel.setGuestBookEntryText("TwoAdded");
        guestBookEntryModel.setCapturedBy("dipak");
        boolean flag = true;
        try {
            guestBookEntryServiceImpl.updateGuestEntryText(guestBookEntryModel);
        } catch (Exception exception) {
            flag = false;
        }

        Assert.assertTrue(flag);
    }

    @Test
    public void test_updateGuestEntryImage()
    {
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryId(2L);
        guestBookEntryModel.setGuestBookEntryImage(new byte[100]);
        guestBookEntryModel.setCapturedBy("dipak");
        boolean flag = true;
        try {
            guestBookEntryServiceImpl.updateGuestEntryImage(guestBookEntryModel);
        } catch (Exception exception) {
            flag = false;
        }

        Assert.assertTrue(flag);
    }

    @Test
    public void test_approveGuestBookEntries()
    {
        boolean flag = true;
        try {
            guestBookEntryServiceImpl.approveGuestBookEntries("2");
        } catch (Exception exception) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }

    public void test_createGuestBookEntry()
    {
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryText("TwoAdded");
        guestBookEntryModel.setCapturedBy("dipak");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(12L);
        userEntity.setEmail("some@mail.com");
        userEntity.setPassword("Dipak");
        userEntity.setRole("USER");
        userEntity.setUsername("Dipak");
        Mockito.when(userDetailsRepository.loadUserByUsername(guestBookEntryModel.getCapturedBy())).thenReturn(userEntity);
        boolean flag = true;
        try {
            guestBookEntryServiceImpl.createGuestBookEntry(guestBookEntryModel);
        } catch (Exception exception) {
            flag = false;
        }

        Assert.assertTrue(flag);
    }
}
