package com.tm.guestbook.api.controller;

import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.api.service.GuestBookEntryService;
import com.tm.guestbook.api.model.BaseControllerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestBookEntryService guestBookEntryService;
    private BaseControllerModel baseControllerModel;

    @PostMapping("/createGuestBookEntry")
    public ResponseEntity<BaseControllerModel> createGuestBookEntry(@RequestBody GuestBookEntryModel guestBookEntryModel) {
        baseControllerModel = new BaseControllerModel();
        try {
            if (null == guestBookEntryModel) {
                baseControllerModel.getErrorMessages().add("Please check request body, it should meet API specifications");
                return new ResponseEntity<>(baseControllerModel, HttpStatus.NOT_ACCEPTABLE);
            }
            guestBookEntryService.createGuestBookEntry(guestBookEntryModel);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Saved successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getInfoMessages().add("Save operation failed");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    @PostMapping("/createGuestBookEntryImage")
    public ResponseEntity<BaseControllerModel> createGuestBookEntryImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam(value = "guestBookEntryId", required = false) Long guestBookEntryId) {
        baseControllerModel = new BaseControllerModel();
        try {
            if (null == multipartFile) {
                baseControllerModel.getErrorMessages().add("Please check request, it should meet API specifications");
                return new ResponseEntity<>(baseControllerModel, HttpStatus.NOT_ACCEPTABLE);
            }
            GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
            guestBookEntryModel.setGuestBookEntryImage(multipartFile.getBytes());
            if (guestBookEntryId != null) {
                guestBookEntryModel.setGuestBookEntryId(guestBookEntryId);
                guestBookEntryService.updateGuestEntryImage(guestBookEntryModel);
            } else {
                guestBookEntryService.createGuestBookEntry(guestBookEntryModel);
            }

            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Image Uploaded successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getInfoMessages().add("Image Uploaded operation failed");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    @GetMapping("/getAllGuestBookEntriesForUser/{userId}")
    public ResponseEntity<BaseControllerModel> getAllGuestBookEntriesForUser(@PathVariable("userId") Long userId) {
        baseControllerModel = new BaseControllerModel();
        try {
            List<GuestBookEntryModel> guestBookEntryModels = guestBookEntryService.getAllGuestBookEntriesForUser(userId);
            baseControllerModel.setPayloads(guestBookEntryModels);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records fetched successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Error occurred while fetching records");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }
    @PostMapping("/updateGuestEntryText")
    public ResponseEntity<BaseControllerModel> updateGuestEntryText(@RequestBody GuestBookEntryModel guestBookEntryModel) {
        baseControllerModel = new BaseControllerModel();
        try {
            guestBookEntryService.updateGuestEntryText(guestBookEntryModel);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records update successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Update failed");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

}
