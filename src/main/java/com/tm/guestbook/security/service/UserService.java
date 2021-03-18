package com.tm.guestbook.security.service;

import com.tm.guestbook.security.model.UserModel;

public interface UserService {
    Void createUser(UserModel userModel) throws Exception;
    UserModel getUserDetailsByEmail(String emailIO);
}
