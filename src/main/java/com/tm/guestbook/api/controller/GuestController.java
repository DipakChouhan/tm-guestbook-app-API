package com.tm.guestbook.api.controller;

import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.api.service.GuestBookEntryService;
import com.tm.guestbook.api.model.BaseControllerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * This class is responsible for controlling the guest behaviour
 * Created By: Dipak Chouhan
 */

@RestController
@RequestMapping("/guest")
public class GuestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

    @Autowired
    private GuestBookEntryService guestBookEntryService;
    private BaseControllerModel baseControllerModel;

    /**
     * Endpoint to create new guest book entry text.
     * @param guestBookEntryModel
     * @return ResponseEntity
     */
    @PostMapping("/createGuestBookEntry")
    public ResponseEntity<BaseControllerModel> createGuestBookEntry(@RequestBody GuestBookEntryModel guestBookEntryModel) {
        LOGGER.info("GuestController.createGuestBookEntry----> Starts");
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
            LOGGER.error("GuestController.createGuestBookEntry----> error: {}", exception.getMessage());
        }
        LOGGER.info("GuestController.createGuestBookEntry----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    /**
     * Endpoint to create or update guest book entry image or update i
     * @param multipartFile
     * @param guestBookEntryId
     * @return ResponseEntity
     */
    @PostMapping("/createGuestBookEntryImage")
    public ResponseEntity<BaseControllerModel> createGuestBookEntryImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam(value = "guestBookEntryId", required = false) Long guestBookEntryId) {
        LOGGER.info("GuestController.createGuestBookEntryImage----> Starts");
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
            LOGGER.error("GuestController.createGuestBookEntryImage----> error: {}", exception.getMessage());
        }
        LOGGER.info("GuestController.createGuestBookEntryImage----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    /**
     * Endpoint to get all entries for a particular user
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping("/getAllGuestBookEntriesForUser/{userId}")
    public ResponseEntity<BaseControllerModel> getAllGuestBookEntriesForUser(@PathVariable("userId") Long userId) {
        LOGGER.info("GuestController.getAllGuestBookEntriesForUser----> Starts");
        baseControllerModel = new BaseControllerModel();
        try {
            List<GuestBookEntryModel> guestBookEntryModels = guestBookEntryService.getAllGuestBookEntriesForUser(userId);
            baseControllerModel.setPayloads(guestBookEntryModels);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records fetched successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Error occurred while fetching records");
            LOGGER.error("GuestController.getAllGuestBookEntriesForUser----> error: {}", exception.getMessage());
        }
        LOGGER.info("GuestController.getAllGuestBookEntriesForUser----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    /**
     * Endpoint to update particular text entry
     * @param guestBookEntryModel
     * @return ResponseEntity
     */
    @PostMapping("/updateGuestEntryText")
    public ResponseEntity<BaseControllerModel> updateGuestEntryText(@RequestBody GuestBookEntryModel guestBookEntryModel) {
        LOGGER.info("GuestController.updateGuestEntryText----> Starts");
        baseControllerModel = new BaseControllerModel();
        try {
            guestBookEntryService.updateGuestEntryText(guestBookEntryModel);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records update successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Update failed");
            LOGGER.error("GuestController.updateGuestEntryText----> error: {}", exception.getMessage());
        }
        LOGGER.info("GuestController.updateGuestEntryText----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

}
