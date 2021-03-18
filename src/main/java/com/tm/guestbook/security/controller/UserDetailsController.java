package com.tm.guestbook.security.controller;

import com.tm.guestbook.api.model.BaseControllerModel;
import com.tm.guestbook.security.constant.SecurityConstants;
import com.tm.guestbook.security.model.UserModel;
import com.tm.guestbook.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is responsible for controlling the user related information
 * Created By: Dipak Chouhan
 */

@RestController
@RequestMapping(SecurityConstants.PATH_SECURITY + SecurityConstants.PATH_USER_DETAILS)
public class UserDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private UserService userService;

    private BaseControllerModel baseControllerModel;

    /**
     * Endpoint to register a new user
     * @param userModel
     * @return ResponseEntity
     */
    @PostMapping(SecurityConstants.PATH_REGISTER)
    public ResponseEntity<BaseControllerModel> register(@RequestBody UserModel userModel) {
        LOGGER.info("UserDetailsController.register----> Starts");
        LOGGER.info("Registration started for user email: {}", userModel.getEmail());
        baseControllerModel = new BaseControllerModel();
        try {
            baseControllerModel.setPayloads(userService.createUser(userModel));
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("User Registered Successfully! Please login to to continue");
            LOGGER.info("Registration successful for user email: {}", userModel.getEmail());
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Failed To Register User");
            LOGGER.info("Registration failed for user email: {}", userModel.getEmail());
        }
        LOGGER.info("UserDetailsController.register----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    /**
     * Endpoint to get the user details using email ID
     * @param emailID
     * @return ResponseEntity
     */
    @PostMapping("/getUserDetails")
    public ResponseEntity<BaseControllerModel> getUserDetails(@RequestBody String emailID) {
        LOGGER.info("UserDetailsController.getUserDetails----> Starts");
        baseControllerModel = new BaseControllerModel();
        try {
            baseControllerModel.setPayloads(userService.getUserDetailsByEmail(emailID));
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("User details fetch successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Failed to fetch user details");
        }
        LOGGER.info("UserDetailsController.getUserDetails----> Ends");
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }
}
