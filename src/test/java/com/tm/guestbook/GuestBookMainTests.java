package com.tm.guestbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.security.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Rollback(value = true)
public class GuestBookMainTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void admin_getAllGuestBookEntries() throws Exception {
        mvc.perform(get("/admin/getAllGuestBookEntries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_getAllGuestBookEntriesForUser() throws Exception {
        mvc.perform(get("/guest/getAllGuestBookEntriesForUser/14")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_createGuestBookEntry() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryText("This is the entry text I have added here");
        String jsonString = mapper.writeValueAsString(guestBookEntryModel);
        mvc.perform(post("/guest/createGuestBookEntry")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_updateGuestEntryText() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryId(25L);
        guestBookEntryModel.setGuestBookEntryText("Content is updated from herer");
        String jsonString = mapper.writeValueAsString(guestBookEntryModel);
        mvc.perform(post("/guest/updateGuestEntryText")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void admin_deleteGuestBookEntries() throws Exception {
        mvc.perform(post("/admin/deleteGuestBookEntries").contentType(MediaType.APPLICATION_JSON).content("2"))
                .andExpect(status().isOk());
    }

    @Test
    public void admin_approveGuestBookEntries() throws Exception {
        mvc.perform(put("/admin/approveGuestBookEntries").contentType(MediaType.APPLICATION_JSON).content("3"))
                .andExpect(status().isOk());
    }

    @Test
    public void security_createUser() throws Exception {
        try {
            String jsonString = produceCreateUserJsonString();
            mvc.perform(post("/security/userDetails/register")
                    .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                    .andExpect(status().isOk());
        } catch (Exception ignored) {

        }
    }

    private String produceCreateUserJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserModel userModel = new UserModel();
        userModel.setName("Test");
        userModel.setEmail("test@gmail.com");
        userModel.setUsername("testUserName");
        userModel.setPassword(new BCryptPasswordEncoder().encode("test"));
        return mapper.writeValueAsString(userModel);
    }

}
