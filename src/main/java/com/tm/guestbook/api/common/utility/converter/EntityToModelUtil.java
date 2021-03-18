package com.tm.guestbook.api.common.utility.converter;

import com.tm.guestbook.api.entity.GuestBookEntryEntity;
import com.tm.guestbook.api.model.GuestBookEntryModel;
import com.tm.guestbook.api.common.utility.date.DateTimeUtil;
import com.tm.guestbook.security.entity.UserEntity;
import com.tm.guestbook.security.model.UserModel;

public class EntityToModelUtil {

    private EntityToModelUtil() {}

    public static GuestBookEntryModel convertGuestBookEntryEntityToGuestBookEntryModel(GuestBookEntryEntity guestBookEntryEntity) {
        GuestBookEntryModel guestBookEntryModel = new GuestBookEntryModel();
        guestBookEntryModel.setGuestBookEntryId(guestBookEntryEntity.getGuestBookEntryId());
        guestBookEntryModel.setGuestBookEntryText(guestBookEntryEntity.getGuestBookEntryText());
        guestBookEntryModel.setGuestBookEntryImage(guestBookEntryEntity.getGuestBookEntryImage());
        if (null != guestBookEntryEntity.getUserEntity()) {
            guestBookEntryModel.setCapturedBy(guestBookEntryEntity.getUserEntity().getEmail());
        }
        guestBookEntryModel.setStatus(guestBookEntryEntity.getGuestBookEntryStatus() == 1? "Pending": "Approved");
        guestBookEntryModel.setCreatedBy(guestBookEntryEntity.getCreatedBy());
        guestBookEntryModel.setCreatedOn(DateTimeUtil.convertLocalDateTimeToString(guestBookEntryEntity.getCreatedOn()));
        guestBookEntryModel.setModifiedBy(guestBookEntryEntity.getModifiedBy());
        guestBookEntryModel.setModifiedOn(DateTimeUtil.convertLocalDateTimeToString(guestBookEntryEntity.getModifiedOn()));
        return guestBookEntryModel;
    }

    public static UserModel converUserEntityToUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setName(userEntity.getName());
        userModel.setUsername(userEntity.getUsername());
        userModel.setRole(userEntity.getRole());
        return userModel;
    }
}
