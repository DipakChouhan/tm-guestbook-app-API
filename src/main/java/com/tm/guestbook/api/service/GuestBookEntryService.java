package com.tm.guestbook.api.service;

import com.tm.guestbook.api.model.GuestBookEntryModel;

import java.util.List;

public interface GuestBookEntryService {

    void createGuestBookEntry(GuestBookEntryModel guestBookEntryModel);

    List<GuestBookEntryModel> getAllGuestBookEntries();

    void deleteGuestBookEntries(String guestBookEntryIds);

    void approveGuestBookEntries(String guestBookEntryIds);

    List<GuestBookEntryModel> getAllGuestBookEntriesForUser(Long userId);

    void updateGuestEntryText(GuestBookEntryModel guestBookEntryModel);

    void updateGuestEntryImage(GuestBookEntryModel guestBookEntryModel);
}
