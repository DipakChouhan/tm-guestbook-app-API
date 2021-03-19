package com.tm.guestbook.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.guestbook.api.controller.AdminController;
import com.tm.guestbook.api.controller.GuestController;
import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.api.repository.GuestBookEntryRepository;
import com.tm.guestbook.api.service.GuestBookEntryService;
import com.tm.guestbook.api.service.GuestBookEntryServiceImpl;
import com.tm.guestbook.security.controller.UserDetailsController;
import com.tm.guestbook.security.model.UserModel;
import com.tm.guestbook.security.repository.UserDetailsRepository;
import com.tm.guestbook.security.service.UserService;
import com.tm.guestbook.security.service.impl.UserDetailsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@Rollback(value = true)
public class GuestBookMainTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads_GuestBookEntryService() {
        GuestBookEntryService guestBookEntryService = applicationContext.getBean(GuestBookEntryService.class);
        Assert.assertTrue(guestBookEntryService instanceof GuestBookEntryServiceImpl);

        GuestController guestController = applicationContext.getBean(GuestController.class);
        Assert.assertNotNull(guestController);
    }

    @Test
    public void admin_getAllGuestBookEntries_test() throws Exception {
        mockMvc.perform(get("/admin/getAllGuestBookEntries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void contextLoads_AdminController() {
        AdminController adminController = applicationContext.getBean(AdminController.class);
        Assert.assertNotNull(adminController);
    }


    @Test
    public void guest_getAllGuestBookEntriesForUser_test() throws Exception {
        mockMvc.perform(get("/guest/getAllGuestBookEntriesForUser/14")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void contextLoads_UserService() {
        UserService userService = applicationContext.getBean(UserService.class);
        Assert.assertTrue(userService instanceof UserDetailsServiceImpl);

        UserDetailsService userDetailsService = applicationContext.getBean(UserDetailsService.class);
        Assert.assertTrue(userDetailsService instanceof UserDetailsServiceImpl);
    }

    @Test
    public void guest_createGuestBookEntry_test() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryText("test entry added");
        String jsonString = mapper.writeValueAsString(guestBookEntryModel);
        mockMvc.perform(post("/guest/createGuestBookEntry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_updateGuestEntryText_test() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryId(25L);
        guestBookEntryModel.setGuestBookEntryText("Updated here");
        String jsonString = mapper.writeValueAsString(guestBookEntryModel);
        mockMvc.perform(post("/guest/updateGuestEntryText")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void contextLoads_UserDetailsController() {
        UserDetailsController userDetailsController = applicationContext.getBean(UserDetailsController.class);
        Assert.assertNotNull(userDetailsController);
    }


    @Test
    public void admin_deleteGuestBookEntries_test() throws Exception {
        mockMvc.perform(post("/admin/deleteGuestBookEntries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2"))
                .andExpect(status().isOk());
    }

    @Test
    public void contextLoads_UserDetailsRepository() {
        UserDetailsRepository userDetailsRepository = applicationContext.getBean(UserDetailsRepository.class);
        Assert.assertNotNull(userDetailsRepository);
    }

    @Test
    public void admin_approveGuestBookEntries_test() throws Exception {
        mockMvc.perform(put("/admin/approveGuestBookEntries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("3"))
                .andExpect(status().isOk());
    }

    @Test
    public void contextLoads_GuestBookEntryRepository() {
        GuestBookEntryRepository guestBookEntryRepository = applicationContext.getBean(GuestBookEntryRepository.class);
        Assert.assertNotNull(guestBookEntryRepository);
    }

    @Test
    public void security_createUser_test() throws Exception {
        try {
            String jsonString = produceCreateUserJsonString();
            mockMvc.perform(post("/security/userDetails/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonString))
                    .andExpect(status().isOk());
        } catch (Exception ignored) {

        }
    }

    private String produceCreateUserJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserModel userModel = new UserModel();
        userModel.setName("simple");
        userModel.setEmail("simple@simple.com");
        userModel.setUsername("simple");
        userModel.setPassword(new BCryptPasswordEncoder()
                .encode("simple"));
        return mapper.writeValueAsString(userModel);
    }

}
