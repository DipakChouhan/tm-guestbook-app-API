package com.tm.guestbook.common.utility.converter;

import com.tm.guestbook.api.entity.GuestBookEntryEntity;
import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.security.entity.UserEntity;
import com.tm.guestbook.security.model.UserModel;

public class ModelToEntityUtil {

    private ModelToEntityUtil() {}

    public static UserEntity convertUserModelToUserEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setName(userModel.getName());
        userEntity.setUsername(userModel.getUsername());
        userEntity.setRole("USER");
        return userEntity;
    }

    public static GuestBookEntryEntity convertGuestBookEntryModelToGuestBookEntryEntity(GuestBookEntryModel guestBookEntryModel) {
        GuestBookEntryEntity guestBookEntryEntity = new GuestBookEntryEntity();
        guestBookEntryEntity.setGuestBookEntryId(guestBookEntryModel.getGuestBookEntryId());
        return guestBookEntryEntity;
    }
}
