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

@RestController
@RequestMapping(SecurityConstants.PATH_SECURITY + SecurityConstants.PATH_USER_DETAILS)
public class UserDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private UserService userService;

    private BaseControllerModel baseControllerModel;

    @PostMapping(SecurityConstants.PATH_REGISTER)
    public ResponseEntity<BaseControllerModel> register(@RequestBody UserModel userModel) {
        baseControllerModel = new BaseControllerModel();
        try {
            baseControllerModel.setPayloads(userService.createUser(userModel));
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("User Registered Successfully! Please login to to continue");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Failed To Register User");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }

    @PostMapping("/getUserDetails")
    public ResponseEntity<BaseControllerModel> getUserDetails(@RequestBody String emailID) {
        baseControllerModel = new BaseControllerModel();
        try {
            baseControllerModel.setPayloads(userService.getUserDetailsByEmail(emailID));
            baseControllerModel.setSuccess(true);
            baseControllerModel.getInfoMessages().add("User details fetch successfully");
        } catch (Exception exception) {
            baseControllerModel.setSuccess(false);
            baseControllerModel.getErrorMessages().add("Failed to fetch user details");
        }
        return new ResponseEntity<>(baseControllerModel, HttpStatus.OK);
    }
}
