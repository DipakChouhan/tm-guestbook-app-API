package com.tm.guestbook.api.entity;

import com.tm.guestbook.common.entity.BaseEntity;
import com.tm.guestbook.security.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GUEST_BOOK_ENTRY")
public class GuestBookEntryEntity extends BaseEntity {
    @Id
    @Column(name = "GUEST_BOOK_ENTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestBookEntryId;
    @Column(name = "GUEST_BOOK_ENTRY_TEXT")
    private String guestBookEntryText;
    @Column(name = "GUEST_BOOK_ENTRY_IMAGE")
    private byte[] guestBookEntryImage;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GUEST_BOOK_ENTRY_CAPTURED_BY", referencedColumnName = "USER_ID")
    private UserEntity userEntity;
    @Column(name = "GUEST_BOOK_ENTRY_STATUS")
    private Long guestBookEntryStatus;

    public Long getGuestBookEntryId() {
        return guestBookEntryId;
    }

    public void setGuestBookEntryId(Long guestBookEntryId) {
        this.guestBookEntryId = guestBookEntryId;
    }

    public String getGuestBookEntryText() {
        return guestBookEntryText;
    }

    public void setGuestBookEntryText(String guestBookEntryText) {
        this.guestBookEntryText = guestBookEntryText;
    }

    public byte[] getGuestBookEntryImage() {
        return guestBookEntryImage;
    }

    public void setGuestBookEntryImage(byte[] guestBookEntryImage) {
        this.guestBookEntryImage = guestBookEntryImage;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Long getGuestBookEntryStatus() {
        return guestBookEntryStatus;
    }

    public void setGuestBookEntryStatus(Long guestBookEntryStatus) {
        this.guestBookEntryStatus = guestBookEntryStatus;
    }
}
