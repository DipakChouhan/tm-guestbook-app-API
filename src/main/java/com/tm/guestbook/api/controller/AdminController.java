package com.tm.guestbook.api.controller;

import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.api.service.GuestBookEntryService;
import com.tm.guestbook.api.model.BaseControllerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private GuestBookEntryService guestBookEntryService;
    private BaseControllerModel baseControllerModel;

    @GetMapping("/getAllGuestBookEntries")
    public ResponseEntity<BaseControllerModel> getAllGuestBookEntries() {
        baseControllerModel = new BaseControllerModel();
        try {
            List<GuestBookEntryModel> guestBookEntryModels = guestBookEntryService.getAllGuestBookEntries();
            baseControllerModel.setPayloads(guestBookEntryModels);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records fetched successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Error occurred while fetching records");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    @PostMapping("/deleteGuestBookEntries")
    public ResponseEntity<BaseControllerModel> deleteGuestBookEntries(@RequestBody String guestBookEntryIds) {
        try {
            baseControllerModel = new BaseControllerModel();
            guestBookEntryService.deleteGuestBookEntries(guestBookEntryIds);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records deleted successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getInfoMessages().add("Error occurred while deleting records");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    @PutMapping("/approveGuestBookEntries")
    public ResponseEntity<BaseControllerModel> approveGuestBookEntries(@RequestBody String guestBookEntryIds) {
        baseControllerModel = new BaseControllerModel();
        try {
            guestBookEntryService.approveGuestBookEntries(guestBookEntryIds);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Approved successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getInfoMessages().add("Error occurred while Approving");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }
}
