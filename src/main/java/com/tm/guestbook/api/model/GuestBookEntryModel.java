package com.tm.guestbook.api.model;

public class GuestBookEntryModel extends BaseModel {
    private Long guestBookEntryId;
    private String guestBookEntryText;
    private byte[] guestBookEntryImage;
    private String capturedBy;
    private String status;

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

    public String getCapturedBy() {
        return capturedBy;
    }

    public void setCapturedBy(String capturedBy) {
        this.capturedBy = capturedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
