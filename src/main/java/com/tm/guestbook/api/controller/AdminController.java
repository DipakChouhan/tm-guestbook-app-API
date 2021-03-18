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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is responsible for controlling the admin behaviour
 * Created By: Dipak Chouhan
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private GuestBookEntryService guestBookEntryService;
    private BaseControllerModel baseControllerModel;

    /**
     * Endpoint to get all the guest book entries
     * @return ResponseEntity
     */
    @GetMapping("/getAllGuestBookEntries")
    public ResponseEntity<BaseControllerModel> getAllGuestBookEntries() {
        LOGGER.info("AdminController.getAllGuestBookEntries----> Starts");
        baseControllerModel = new BaseControllerModel();
        try {
            List<GuestBookEntryModel> guestBookEntryModels = guestBookEntryService.getAllGuestBookEntries();
            baseControllerModel.setPayloads(guestBookEntryModels);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records fetched successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Error occurred while fetching records");
            LOGGER.error("AdminController.getAllGuestBookEntries----> error: {}", exception.getMessage());
        }
        LOGGER.info("AdminController.getAllGuestBookEntries----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    /**
     * Endpoint to delete guest book entries based on ID
     * @param guestBookEntryIds
     * @return ResponseEntity
     */
    @PostMapping("/deleteGuestBookEntries")
    public ResponseEntity<BaseControllerModel> deleteGuestBookEntries(@RequestBody String guestBookEntryIds) {
        LOGGER.info("AdminController.deleteGuestBookEntries----> Starts");
        try {
            baseControllerModel = new BaseControllerModel();
            guestBookEntryService.deleteGuestBookEntries(guestBookEntryIds);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Records deleted successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getInfoMessages().add("Error occurred while deleting records");
            LOGGER.error("AdminController.deleteGuestBookEntries----> error: {}", exception.getMessage());
        }
        LOGGER.info("AdminController.deleteGuestBookEntries----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }


    /**
     * Endpoint to approve particular guest book entry by admin
     * @param guestBookEntryIds
     * @return ResponseEntity
     */
    @PutMapping("/approveGuestBookEntries")
    public ResponseEntity<BaseControllerModel> approveGuestBookEntries(@RequestBody String guestBookEntryIds) {
        LOGGER.info("AdminController.approveGuestBookEntries----> Starts");
        baseControllerModel = new BaseControllerModel();
        try {
            guestBookEntryService.approveGuestBookEntries(guestBookEntryIds);
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("Approved successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getInfoMessages().add("Error occurred while Approving");
            LOGGER.error("AdminController.approveGuestBookEntries----> error: {}", exception.getMessage());
        }
        LOGGER.info("AdminController.approveGuestBookEntries----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }
}
